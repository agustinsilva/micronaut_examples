package ar.com.globallogic;

import io.micronaut.aop.MethodInterceptor;
import io.micronaut.aop.MethodInvocationContext;
import io.micronaut.core.type.MutableArgumentValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.util.*;

@Singleton
public class LoggerInterceptor implements MethodInterceptor<Object, Object> {

    private static final Logger LOG = LoggerFactory.getLogger(LoggerInterceptor.class);

    @Override
    public Object intercept(MethodInvocationContext<Object, Object> context) {
        LOG.info("Ejecutado logger en: " + context.getDeclaringType() + " y metodo: " + context.getName());
        return context.proceed();
        }
}

