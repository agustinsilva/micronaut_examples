package ar.com.globallogic.cursos.micronaut;

import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
public class HelloClientSpec {
    @Inject
    HelloClient client;

    @Test
    public void testHelloWorldResponse() {
        assertEquals("Hello jorge. I am Service implementation.", client.hello("jorge").blockingGet());
    }
}
