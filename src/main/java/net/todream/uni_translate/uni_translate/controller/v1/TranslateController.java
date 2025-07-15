package net.todream.uni_translate.uni_translate.controller.v1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import net.todream.uni_translate.uni_translate.dto.TranslateClientInDto;
import net.todream.uni_translate.uni_translate.dto.TranslateClientOutDto;
import net.todream.uni_translate.uni_translate.service.TranslateService;
import net.todream.uni_translate.uni_translate.vo.Result;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Tag(name = "翻译/通用翻译")
@RestController
@RequestMapping("/api/v1/translate")
public class TranslateController {
    
    @Resource
    private TranslateService translateService;

    @Operation(summary = "单文本翻译")
    @PostMapping("")
    public Result<TranslateClientOutDto> translate(@RequestBody TranslateClientInDto in) {
        TranslateClientOutDto out = translateService.translate(in);
        return Result.success(out);
    }
    
}
