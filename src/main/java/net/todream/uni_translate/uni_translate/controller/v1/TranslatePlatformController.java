package net.todream.uni_translate.uni_translate.controller.v1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import net.todream.uni_translate.uni_translate.dto.TranslatePlatformAddPlatformDto;
import net.todream.uni_translate.uni_translate.dto.TranslatePlatformUpdatePlatformDto;
import net.todream.uni_translate.uni_translate.service.TranslatePlatformService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@Tag(name = "翻译/平台")
@RequestMapping("/api/v1/translate/platform")
public class TranslatePlatformController {


    @Resource
    private TranslatePlatformService tPlatformService;

    @Operation(summary = "添加配置")
    @PostMapping("/add")
    public String add(@RequestBody TranslatePlatformAddPlatformDto data) {
        tPlatformService.addPlatform(data);
        return "success";
    } 

    @Operation(summary = "修改配置")
    @PostMapping("/update")
    public String update(@RequestBody TranslatePlatformUpdatePlatformDto data) {
        tPlatformService.updateById(data);
        return "success";
    }

}
