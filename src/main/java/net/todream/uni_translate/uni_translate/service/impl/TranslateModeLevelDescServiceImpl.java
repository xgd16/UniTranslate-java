package net.todream.uni_translate.uni_translate.service.impl;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import net.todream.uni_translate.uni_translate.dto.TranslateClientInDto;
import net.todream.uni_translate.uni_translate.dto.TranslateClientOutDto;
import net.todream.uni_translate.uni_translate.entity.TranslateConf;
import net.todream.uni_translate.uni_translate.exception.TranslateException;
import net.todream.uni_translate.uni_translate.service.TranslateModeService;
import net.todream.uni_translate.uni_translate.service.TranslateSelectService;

/**
 * 翻译模式：按照等级排序 (支持指定平台)
 */
@Service("levelDesc")
@Scope("prototype")
public class TranslateModeLevelDescServiceImpl implements TranslateModeService  {

    private List<TranslateConf> conf;

    private static final String MODE_NAME = "levelDesc";

    @Resource
    private TranslateSelectService translateSelectService;

    @Override
    public void init(List<TranslateConf> conf, TranslateClientInDto in) {
        Stream<TranslateConf> confStream = conf.stream().sorted((a, b) -> a.getLevel() - b.getLevel());
        
        if (!in.getPlatform().isEmpty() && in.getIsFallbackEnabled()) {
            // 判断平台不为空的时候将配置里面对应的平台按照level倒序排序放到最前面
            confStream = confStream.sorted((a, b) -> { 
                if (a.getPlatform().equals(in.getPlatform())) {
                    return -1;
                } else if (b.getPlatform().equals(in.getPlatform())) {
                    return 1;
                } else {
                    return 0;
                }
            });
        }
        this.conf = confStream.toList();
    }

    @Override
    public String getModeName() {
        return MODE_NAME;
    }

    @Override
    public TranslateClientOutDto translate(TranslateClientInDto in) {
        for (TranslateConf c : conf) {
            try {
                return translateSelectService.tanslate(c, in);
            } catch (Exception e) {
                throw new TranslateException("翻译模式 " + MODE_NAME + " 执行失败: " + e.getMessage() + "请求: " + in.getText(), e);
            }
        }
        throw new TranslateException("没有可用的翻译配置，无法执行翻译请求: " + in.getText());
    }

    @Override
    public Boolean transPlatform() {
        return true;
    }
    
    
}
