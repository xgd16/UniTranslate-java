package net.todream.uni_translate.uni_translate.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum TranslateTopicEnum {

    TRANSLATE_RETURN("translate_return"),

    TRANSLATE_EXCEPTION("translate_excption");

    private final String topic;

}
