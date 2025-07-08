package net.todream.uni_translate.uni_translate.entity;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import java.util.Properties;

@Intercepts({
    @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
public class TimestampInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object parameter = invocation.getArgs()[1];
        if (parameter instanceof BaseEntity) {
            long now = System.currentTimeMillis();
            MappedStatement ms = (MappedStatement) invocation.getArgs()[0];
            String sqlId = ms.getId().toLowerCase();
            if (sqlId.contains("insert")) {
                ((BaseEntity) parameter).setCreateTime(now);
                ((BaseEntity) parameter).setUpdateTime(now);
            } else if (sqlId.contains("update")) {
                ((BaseEntity) parameter).setUpdateTime(now);
            }
        }
        return invocation.proceed();
    }
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }
    @Override
    public void setProperties(Properties properties) {}
} 