package net.todream.uni_translate.uni_translate.controller.v1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import net.todream.uni_translate.uni_translate.dto.Result;
import net.todream.uni_translate.uni_translate.dto.TranslatePlatformAddPlatformDto;
import net.todream.uni_translate.uni_translate.dto.TranslatePlatformUpdatePlatformDto;
import net.todream.uni_translate.uni_translate.entity.TranslateConf;
import net.todream.uni_translate.uni_translate.service.TranslatePlatformService;
import net.todream.uni_translate.uni_translate.vo.TranslatePlatformGetListVo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@Tag(name = "翻译/平台")
@RequestMapping("/api/v1/translate/platform")
public class TranslatePlatformController {


    @Resource
    private TranslatePlatformService tPlatformService;

    @Operation(summary = "添加配置")
    @PostMapping("/add")
    public Result<Object> add(@RequestBody TranslatePlatformAddPlatformDto data) {
        tPlatformService.addPlatform(data);
        return Result.success();
    } 

    @Operation(summary = "修改配置")
    @PostMapping("/update")
    public Result<Object> update(@RequestBody TranslatePlatformUpdatePlatformDto data) {
        tPlatformService.updateById(data);
        return Result.success();
    }
    
    @Operation(summary = "获取配置列表")
    @GetMapping("/getList")
    public Result<List<TranslatePlatformGetListVo>> getList() {
        List<TranslateConf> confList = tPlatformService.getList();
        List<TranslatePlatformGetListVo> voList = new ArrayList<TranslatePlatformGetListVo>();
        for (TranslateConf translateConf : confList) {
            voList.add(new TranslatePlatformGetListVo() {{
                setId(translateConf.getId());
                setName(translateConf.getName());
                setPlatform(translateConf.getPlatform());
                setLevel(translateConf.getLevel());
            }});
        }
        return Result.success(voList);
    }

}
