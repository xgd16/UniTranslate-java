package net.todream.uni_translate.uni_translate.service.impl;

import java.util.HashMap;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import net.todream.uni_translate.uni_translate.dto.TranslateClientInDto;
import net.todream.uni_translate.uni_translate.dto.TranslateClientOutDto;
import net.todream.uni_translate.uni_translate.dto.TranslateConfGoogleDto;
import net.todream.uni_translate.uni_translate.dto.TranslateRespGoogleDto;
import net.todream.uni_translate.uni_translate.entity.TranslateConf;
import net.todream.uni_translate.uni_translate.service.TranslateClientService;

@Service
public class GoogleTranslateClientServiceImpl implements TranslateClientService {

    @Override
    public TranslateClientOutDto translate(TranslateConf conf,TranslateClientInDto in) {
        TranslateConfGoogleDto googleConf = (TranslateConfGoogleDto) conf.getConf();
        // Create a WebClient instance with the base URL from the configuration

        HashMap<String, String> bodyValue = new HashMap<String, String>();
        bodyValue.put("key", googleConf.getKey());
        bodyValue.put("target", in.getTo());
        bodyValue.put("source", in.getForm());
        bodyValue.put("q", in.getText());

        TranslateRespGoogleDto resp = WebClient.create(googleConf.getUrl())
            .post()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(bodyValue)
            .retrieve()
            .bodyToMono(TranslateRespGoogleDto.class).block();

        if (resp == null || resp.getData() == null || resp.getData().getTranslations() == null || resp.getData().getTranslations().length == 0) {
            throw new RuntimeException("Translation failed or no translations found");
        }

        TranslateClientOutDto out = new TranslateClientOutDto();

        var data = resp.getData().getTranslations()[0];

        out.setTranslatedText(data.getTranslatedText());
        out.setDetectedSourceLanguage(data.getDetectedSourceLanguage());
        out.setTargetLanguage(in.getTo());

        return out;
    }
    
}
