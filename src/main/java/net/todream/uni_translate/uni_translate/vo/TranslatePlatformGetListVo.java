package net.todream.uni_translate.uni_translate.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class TranslatePlatformGetListVo implements Serializable {
    /**
     * id
     * @Primary Key
     */
    private Integer id;

    /**
     * name
     */
    private String name;

    /**
     * platform
     */
    private String platform;

    /**
     * 翻译等级
     */
    private Integer level;
}
