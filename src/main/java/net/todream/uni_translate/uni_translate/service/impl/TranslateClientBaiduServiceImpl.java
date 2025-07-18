package net.todream.uni_translate.uni_translate.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import net.todream.uni_translate.uni_translate.dto.TranslateClientInDto;
import net.todream.uni_translate.uni_translate.dto.TranslateClientOutDto;
import net.todream.uni_translate.uni_translate.dto.TranslateConfBaiduDto;
import net.todream.uni_translate.uni_translate.dto.TranslateRespBaiduDto;
import net.todream.uni_translate.uni_translate.entity.TranslateConf;
import net.todream.uni_translate.uni_translate.exception.TranslateException;
import net.todream.uni_translate.uni_translate.factory.HttpClientFactory;
import net.todream.uni_translate.uni_translate.service.TranslateClientService;
import net.todream.uni_translate.uni_translate.utils.Common;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import java.time.Duration;
import java.util.Random;

@Service("baiduTranslateClient")
public class TranslateClientBaiduServiceImpl implements TranslateClientService {
    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private HttpClientFactory httpApiClient;

    private String getSign(
            String appid,
            String appKey,
            String text,
            String salt
    ) {
        return Common.md5(appid + text + salt + appKey);
    }

    private String getSalt() {
        Random random = new Random();
        return Integer.toString(random.nextInt(16));
    }

    @Override
    public TranslateClientOutDto translate(TranslateConf conf, TranslateClientInDto in) {
        TranslateConfBaiduDto confBaiduDto = objectMapper.convertValue(conf.getConf(), TranslateConfBaiduDto.class);

        String salt = getSalt();
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("q", in.getText());
        map.add("from", in.getFrom());
        map.add("to", in.getTo());
        map.add("appid", confBaiduDto.getAppId());
        map.add("salt", salt);
        map.add("sign", getSign(
                confBaiduDto.getAppId(),
                confBaiduDto.getAppKey(),
                in.getText(),
                salt
        ));

        TranslateRespBaiduDto resp = httpApiClient
                .httpClient(Duration.ofMillis(confBaiduDto.getTimeout()))
                .post()
                .uri(confBaiduDto.getUrl())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue(map)
                .retrieve()
                .bodyToMono(TranslateRespBaiduDto.class)
                .block();

        if (resp == null || resp.getTransResult() == null) {
            throw new TranslateException("Translation failed or no translations found");
        }

        var data = resp.getTransResult().get(0);
        TranslateClientOutDto out = new TranslateClientOutDto();
        out.setTranslatedText(data.getDst());
        out.setDetectedSourceLanguage(in.getFrom());
        out.setTargetLanguage(in.getTo());
        out.setPlatform(in.getPlatform());

        return out;
    }
}
