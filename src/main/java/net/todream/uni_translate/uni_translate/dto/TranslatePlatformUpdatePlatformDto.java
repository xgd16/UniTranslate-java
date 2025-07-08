package net.todream.uni_translate.uni_translate.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TranslatePlatformUpdatePlatformDto extends TranslatePlatformAddPlatformDto {
    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer id;
}
