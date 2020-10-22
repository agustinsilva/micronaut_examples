package ar.com.globallogic.cursos.micronaut.service;

import ar.com.globallogic.cursos.micronaut.dto.CursoDTO;

import java.util.List;

public interface CoursesService {

    public List<CursoDTO> findCourses();
    public List<CursoDTO> findCurrentCourses();
}
