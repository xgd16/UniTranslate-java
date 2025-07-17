package net.todream.uni_translate.uni_translate.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import net.todream.uni_translate.uni_translate.enums.TranslateTopicEnum;
import net.todream.uni_translate.uni_translate.service.KafkaProducerService;

@Component
@Aspect
public class TranslateServiceAspect {

    @Resource
    private KafkaProducerService kafkaProducerService;

    // 定义切点，拦截TranslateService及其实现类所有public方法
    @org.aspectj.lang.annotation.Pointcut("execution(public * net.todream.uni_translate.uni_translate.service.TranslateService.*(..)) || execution(public * net.todream.uni_translate.uni_translate.service.impl.TranslateServiceImpl.*(..))")
    public void translateServiceMethods() {}

    @AfterThrowing(
        pointcut = "translateServiceMethods()",
        throwing = "exception"
    )
    public void afterThrowingTranslate(Exception exception) {
        System.err.println("[TranslateServiceAspect] 方法抛出异常: " + exception.getMessage());
    }

    @AfterReturning(
        pointcut = "translateServiceMethods()",
        returning = "returnValue"
    )
    public void afterReturningTranslate(Object returnValue) {
        System.out.println("[TranslateServiceAspect] 方法返回: " + returnValue);
        kafkaProducerService.send(TranslateTopicEnum.TRANSLATE_RETURN.getTopic(), returnValue);
    }
}
