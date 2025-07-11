package net.todream.uni_translate.uni_translate.dto;

import lombok.Data;

@Data
public class TranslateClientInDto {
    
    private String form;

    private String to;

    private String text;

    private String platform;

    private String mode = "levelDesc"; // 默认翻译模式为 levelDesc

    private Boolean isFallbackEnabled = true; // 是否启用替代配置

    public void setMode(String mode) {
        // 检查空值或空字符串
        if (mode == null || mode.trim().isEmpty()) {
            this.mode = "levelDesc"; // 重置为默认值
        }
    }

}
