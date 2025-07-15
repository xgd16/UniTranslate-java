package net.todream.uni_translate.uni_translate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor  // 显式添加无参构造函数
@AllArgsConstructor // 如果需要全参构造函数
public class TranslateRespBaiduDto
{
    private String from;

    private String to;

    @JsonProperty("trans_result")
    private List<Item> transResult;

    @Data
    public static class Item
    {
        private String src;

        private String dst;
    }
}
