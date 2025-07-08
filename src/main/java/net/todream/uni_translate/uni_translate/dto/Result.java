package net.todream.uni_translate.uni_translate.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Result<T> {
    private String code;
    private String message;
    private T data;
    private Long timestamp;
    
    public static <T> Result<Object> success() {
        return success(new ArrayList<>());
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode("200");
        result.setMessage("Success");
        result.setData(data);
        result.setTimestamp(System.currentTimeMillis());
        return result;
    }
    
    public static <T> Result<T> error(String code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        result.setTimestamp(System.currentTimeMillis());
        return result;
    }
}