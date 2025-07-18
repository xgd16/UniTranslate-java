package net.todream.uni_translate.uni_translate.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.todream.uni_translate.uni_translate.dto.TranslateClientOutDto;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Cache extends BaseEntity{
    
    private Long cacheId;

    private String from;

    private String to;

    private String text;

    private String platform;

    private TranslateClientOutDto result;

    private String checkCode;

    private String checkCodeNoPlatform;

}
