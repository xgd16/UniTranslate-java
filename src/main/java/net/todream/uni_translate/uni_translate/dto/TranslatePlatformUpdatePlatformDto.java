package net.todream.uni_translate.uni_translate.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TranslatePlatformUpdatePlatformDto extends TranslatePlatformAddPlatformDto {
    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer id;
}
