package ar.com.globallogic.cursos.micronaut.controller;

import ar.com.globallogic.cursos.micronaut.dto.CursoDTO;
import ar.com.globallogic.cursos.micronaut.service.CoursesService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import io.micronaut.core.version.annotation.Version;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Error;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.hateoas.JsonError;
import io.micronaut.http.hateoas.Link;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@Controller("/")
public class CourseController {
    private CoursesService coursesService;

    public CourseController(final CoursesService coursesService) {
        this.coursesService = coursesService;
    }

    @Version("1")
    @Get("courses")
    public List<CursoDTO> getCoursesv1() {
        return this.coursesService.findCourses();
    }

    @Version("2")
    @Get("courses")
    public List<CursoDTO> getCoursesv2(@QueryValue @NotNull Boolean active) {
        if (active.booleanValue()) {
            return this.coursesService.findCurrentCourses();
        }
        return this.coursesService.findCourses();
    }


    @Version("3")
    @Get("courses")
    public HttpResponse<List<CursoDTO>> getCoursesV3(final String casoDeUso) throws JsonParseException {
        if (casoDeUso.equals("1")) {
            throw new JsonParseException(null, "");
        }
        if (casoDeUso.equals("2")) {
            return HttpResponse.<List<CursoDTO>>serverError();
        }
        if(casoDeUso.equals("3")){
            throw new RuntimeException("This is my custom error");
        }

        return HttpResponse.<List<CursoDTO>>ok().body(getCoursesv1());
    }

    /**
     * Ejemplo de manejo de Error Local de forma declarativa:
     * Este método capturara todos los errores del tipo JsonParseException
     *
     * @param request
     * @param jsonParseException
     * @return
     */
    @Error
    public HttpResponse<JsonError> jsonError(HttpRequest request, JsonParseException jsonParseException) {
        JsonError error = new JsonError("Invalid JSON: " + jsonParseException.getMessage())
                .link(Link.SELF, Link.of(request.getUri()));

        return HttpResponse.<JsonError>status(HttpStatus.BAD_REQUEST, "Fix your json").body(error);
    }


    @Error(status = HttpStatus.INTERNAL_SERVER_ERROR)
    public HttpResponse<JsonError> notFound(HttpRequest request) {
        JsonError error = new JsonError("Person Not Found").link(Link.SELF, Link.of(request.getUri()));

        return HttpResponse.<JsonError>notFound().body(error);
    }

    @Error(global = true, status = HttpStatus.NOT_FOUND)
    public HttpResponse<JsonError> error(HttpRequest request) {
        JsonError error = new JsonError("Bad Things Happened: ")
                .link(Link.SELF, Link.of(request.getUri()));

        return HttpResponse.<JsonError>notFound()
                .body(error);
    }
}
