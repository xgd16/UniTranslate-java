package net.todream.uni_translate.uni_translate.dto;

import lombok.Data;

@Data
public class TranslateClientInDto {
    
    private String form;

    private String to;

    private String text;

    private String platform;

    private String mode;

}
