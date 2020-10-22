package ar.com.globallogic.cursos.micronaut;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.Single;

@Client("/")
public interface HelloClient {
    @Get(uri="/greetings/{name}", consumes = MediaType.TEXT_PLAIN)
    Single<String> hello(String name);
}
