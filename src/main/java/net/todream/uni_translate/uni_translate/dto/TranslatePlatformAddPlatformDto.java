package net.todream.uni_translate.uni_translate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TranslatePlatformAddPlatformDto {

    @Schema(description="名称")
    private String name;

    @Schema(description="配置")
    private Object conf;

    @Schema(description="平台")
    private String platform;

    @Schema(description="等级")
    private Integer level;
}
