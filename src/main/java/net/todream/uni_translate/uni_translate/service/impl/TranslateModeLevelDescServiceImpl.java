package net.todream.uni_translate.uni_translate.service.impl;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import net.todream.uni_translate.uni_translate.dto.TranslateClientInDto;
import net.todream.uni_translate.uni_translate.dto.TranslateClientOutDto;
import net.todream.uni_translate.uni_translate.entity.TranslateConf;
import net.todream.uni_translate.uni_translate.exception.TranslateException;
import net.todream.uni_translate.uni_translate.service.TranslateModeService;
import net.todream.uni_translate.uni_translate.service.TranslateSelectService;

@Service("levelDesc")
@Scope("prototype")
public class TranslateModeLevelDescServiceImpl implements TranslateModeService  {

    private List<TranslateConf> conf;

    private static final String MODE_NAME = "levelDesc";

    @Resource
    private TranslateSelectService translateSelectService;

    @Override
    public void init(List<TranslateConf> conf) {
        // Sort the configurations by level in descending order
        this.conf = conf.stream().sorted((a, b) -> a.getLevel() - b.getLevel()).toList();
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
    
    
}
