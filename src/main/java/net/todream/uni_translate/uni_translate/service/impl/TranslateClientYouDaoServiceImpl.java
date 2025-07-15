package net.todream.uni_translate.uni_translate.service.impl;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.Resource;
import net.todream.uni_translate.uni_translate.dto.TranslateClientInDto;
import net.todream.uni_translate.uni_translate.dto.TranslateClientOutDto;
import net.todream.uni_translate.uni_translate.dto.TranslateConfYouDaoDto;
import net.todream.uni_translate.uni_translate.dto.TranslateRespYouDaoDto;
import net.todream.uni_translate.uni_translate.entity.TranslateConf;
import net.todream.uni_translate.uni_translate.exception.TranslateException;
import net.todream.uni_translate.uni_translate.factory.HttpClientFactory;
import net.todream.uni_translate.uni_translate.service.TranslateClientService;
import net.todream.uni_translate.uni_translate.utils.Common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.util.UUID;


@Service("youdaoTranslateClient")
public class TranslateClientYouDaoServiceImpl implements TranslateClientService {


    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private HttpClientFactory httpApiClient;

    @Override
    public TranslateClientOutDto translate(TranslateConf conf, TranslateClientInDto in) {
        TranslateConfYouDaoDto youdaoConf = objectMapper.convertValue(conf.getConf(), TranslateConfYouDaoDto.class);
        // 生成必须参数
        String salt = UUID.randomUUID().toString();
        String curtime = String.valueOf(System.currentTimeMillis() / 1000);
        String sign = calculateSign(youdaoConf.getAppId(), youdaoConf.getAppKey(), in.getText(), salt, curtime);
        // 调用请求
        MultiValueMap<String, String> bodyValue = new LinkedMultiValueMap<>();
        bodyValue.add("appKey", youdaoConf.getAppId());
        bodyValue.add("salt", salt);
        bodyValue.add("from", in.getForm());
        bodyValue.add("to", in.getTo());
        bodyValue.add("signType", "v3");
        bodyValue.add("sign", sign);
        bodyValue.add("curtime", curtime);
        bodyValue.add("q", in.getText());

        TranslateRespYouDaoDto resp = httpApiClient.httpClient(Duration.ofMillis(youdaoConf.getTimeout()))
            .post()
            .uri(youdaoConf.getUrl())
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .body(BodyInserters.fromFormData(bodyValue))
            .retrieve()
            .bodyToMono(TranslateRespYouDaoDto.class).block();

        TranslateClientOutDto out = new TranslateClientOutDto();

        out.setTranslatedText(resp.getTranslation().get(0));
        out.setDetectedSourceLanguage(Common.extractSourceLanguage(resp.getL()));
        out.setTargetLanguage(in.getTo());
        out.setPlatform(in.getPlatform());

        return out;
    }

      /**
     * 计算鉴权签名
     *
     * @param appKey    您的应用ID
     * @param appSecret 您的应用密钥
     * @param salt      随机值
     * @param curtime   当前时间戳(秒)
     * @return 鉴权签名sign
     */
    private static String calculateSign(String appId, String appKey, String text, String salt, String curtime) {
        String input = getInputParam(text);
        try {
            String strSrc = appId + input + salt + curtime + appKey;
            return encrypt(strSrc);
        } catch (Exception e) {
            throw new TranslateException("计算签名失败");
        }
    }

    private static String getInputParam(String text) {
        int textLen = text.length();
        if (textLen <= 20) {
            return text;
        }
        StringBuilder inputBuilder = new StringBuilder();
        inputBuilder.append(text.substring(0, 10));
        inputBuilder.append(textLen);
        inputBuilder.append(text.substring(textLen - 10));
        return inputBuilder.toString();
    }

    private static String encrypt(String strSrc) throws NoSuchAlgorithmException {
        byte[] bt = strSrc.getBytes();
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(bt);
        byte[] bts = md.digest();
        StringBuilder des = new StringBuilder();
        for (byte b : bts) {
            String tmp = (Integer.toHexString(b & 0xFF));
            if (tmp.length() == 1) {
                des.append("0");
            }
            des.append(tmp);
        }
        return des.toString();
    }

}
