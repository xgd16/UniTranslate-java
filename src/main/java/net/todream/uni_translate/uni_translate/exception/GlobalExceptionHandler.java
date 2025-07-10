package net.todream.uni_translate.uni_translate.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import net.todream.uni_translate.uni_translate.vo.Result;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    
    @ExceptionHandler(NullPointerException.class)
    public String handleNullPointerException(NullPointerException e) {
        return "NullPointerException: " + e.getMessage();
    }

    @ExceptionHandler(Exception.class)
    public Result<Object> exceptionHandler(Exception e) {
        return Result.error(500, e.getMessage());
    }
}
