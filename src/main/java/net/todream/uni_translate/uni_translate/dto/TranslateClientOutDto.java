package net.todream.uni_translate.uni_translate.dto;

import lombok.Data;

@Data
public class TranslateClientOutDto {
    // 翻译结果
    private String translatedText;
    // 源语言
    private String detectedSourceLanguage;
    // 目标语言
    private String targetLanguage;
}
