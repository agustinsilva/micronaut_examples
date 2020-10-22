package ar.com.globallogic.cursos.micronaut.handlers;

import io.micronaut.context.annotation.Requires;
//import io.micronaut.core.exceptions.ExceptionHandler;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;

import javax.inject.Singleton;

//@Produces("text/plain")
//@Produces("application/json")
@Singleton
//@Requires(classes = {RuntimeException.class})
public class ErrorHandlerCustom implements ExceptionHandler<RuntimeException, HttpResponse> {

    @Override
    public HttpResponse handle(HttpRequest request, RuntimeException exception) {
        return HttpResponse.ok().body("{ " +
                    "\"status\" : \"OPEN\"" +
                "}");
    }
}
