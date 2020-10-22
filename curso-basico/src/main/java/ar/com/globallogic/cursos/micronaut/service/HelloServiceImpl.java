package ar.com.globallogic.cursos.micronaut.service;

import io.micronaut.context.annotation.Property;
import io.micronaut.context.annotation.Value;

import javax.inject.Singleton;

@Singleton
public class HelloServiceImpl implements HelloService {

    @Property(name = "hello.message.greetings.default")
    private String greetings;

    @Value("${hello.message.greetings.message:. I am Service implementation.}")
    private String message;

    public String getMessage(final String name) {
        return greetings + " " + name + message;
    }

}
