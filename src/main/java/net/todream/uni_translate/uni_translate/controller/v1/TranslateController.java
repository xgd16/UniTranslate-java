package net.todream.uni_translate.uni_translate.controller.v1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import net.todream.uni_translate.uni_translate.dto.Result;
import net.todream.uni_translate.uni_translate.dto.TranslateClientInDto;
import net.todream.uni_translate.uni_translate.dto.TranslateClientOutDto;
import net.todream.uni_translate.uni_translate.service.TranslateClientService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/translate")
public class TranslateController {
    
    @Resource
    private TranslateClientService translateClientService;

    @PostMapping("/")
    public Result<TranslateClientOutDto> postMethodName(@RequestBody TranslateClientInDto in) {

        translateClientService.translate(null, in);

        return null;
    }
    

}
