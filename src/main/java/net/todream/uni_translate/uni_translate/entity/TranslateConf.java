package net.todream.uni_translate.uni_translate.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * TranslateConf entity
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TranslateConf<T> extends BaseEntity {
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
     * conf
     */
    private T conf;

    /**
     * platform
     */
    private String platform;

    /**
     * 翻译等级
     */
    private Integer level;

}
