package net.todream.uni_translate.uni_translate.vo;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TranslatePlatformGetListVo implements Serializable {

    @Schema(description="ID")
    private Integer id; 

    @Schema(description="名称")
    private String name;

    @Schema(description="平台")
    private String platform;

    @Schema(description="等级")
    private Integer level;
}
