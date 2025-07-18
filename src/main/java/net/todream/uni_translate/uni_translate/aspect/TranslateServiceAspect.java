package net.todream.uni_translate.uni_translate.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import net.todream.uni_translate.uni_translate.dto.TranslateClientInDto;
import net.todream.uni_translate.uni_translate.dto.TranslateClientOutDto;
import net.todream.uni_translate.uni_translate.dto.TranslateWithReturnInDto;
import net.todream.uni_translate.uni_translate.enums.TranslateTopicEnum;
import net.todream.uni_translate.uni_translate.service.KafkaProducerService;

@Component
@Aspect
public class TranslateServiceAspect {

    @Resource
    private KafkaProducerService kafkaProducerService;

    // 定义切点，拦截TranslateService及其实现类所有public方法
    @org.aspectj.lang.annotation.Pointcut("execution(public * net.todream.uni_translate.uni_translate.service.impl.TranslateServiceImpl.translate(..))")
    public void translateServiceMethods() {}

    @AfterThrowing(
        pointcut = "translateServiceMethods()",
        throwing = "exception"
    )
    public void afterThrowingTranslate(Exception exception) {
        System.err.println("[TranslateServiceAspect] 方法抛出异常: " + exception.getMessage());
    }

    @Around("translateServiceMethods()")
    public Object aroundTranslate(ProceedingJoinPoint joinPoint) throws Throwable {
        TranslateClientInDto argsIn = (TranslateClientInDto) joinPoint.getArgs()[0];
        TranslateClientOutDto out = (TranslateClientOutDto) joinPoint.proceed();

        TranslateWithReturnInDto in = new TranslateWithReturnInDto();

        in.setIn(argsIn);
        in.setOut(out);
        kafkaProducerService.send(TranslateTopicEnum.TRANSLATE_RETURN.getTopic(), in);
        return out;
    }
}
