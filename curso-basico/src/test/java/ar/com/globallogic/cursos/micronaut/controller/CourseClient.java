package ar.com.globallogic.cursos.micronaut.controller;

import ar.com.globallogic.cursos.micronaut.dto.CursoDTO;
import io.micronaut.core.version.annotation.Version;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.client.annotation.Client;

import javax.validation.constraints.NotNull;
import java.util.List;

@Version("2")
@Client("/")
public interface CourseClient {

    @Version("1")
    @Get("courses")
    public List<CursoDTO> getCourses(@QueryValue Boolean active);

}
