package net.todream.uni_translate.uni_translate.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TranslateConfBaiduDto extends TranslateConfBaseDto
{
    private String appId;

    private String appKey;
}
