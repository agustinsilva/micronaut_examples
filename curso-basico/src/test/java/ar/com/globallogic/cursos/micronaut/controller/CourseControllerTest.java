package ar.com.globallogic.cursos.micronaut.controller;

import ar.com.globallogic.cursos.micronaut.dto.CursoDTO;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class CourseControllerTest {

    @Inject
    CourseClient client;

    @Test
    void getCoursesv1(){
        final List<CursoDTO> resultado = client.getCourses(true);
        System.out.println(resultado);
        assertNotNull(resultado);
    }
}