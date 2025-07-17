package net.todream.uni_translate.uni_translate.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Cache extends BaseEntity{
    
    private Long cacheId;

    private String form;

    private String to;

    private String text;

    private String platform;

    private String result;

    private String checkCode;

    private String checkCodePlatform;

}
