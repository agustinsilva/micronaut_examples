package ar.com.globallogic.cursos.micronaut.service;

import ar.com.globallogic.cursos.micronaut.dto.CursoDTO;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class CoursesServiceImpl implements CoursesService {

    private List<CursoDTO> courses;

    public CoursesServiceImpl() {
        this.courses = new ArrayList<>();
        this.courses.add(CursoDTO.builder().name("Java").isActive(Boolean.TRUE).build());
        this.courses.add(CursoDTO.builder().name(".Net").isActive(Boolean.TRUE).build());
        this.courses.add(CursoDTO.builder().name("MySql").isActive(Boolean.TRUE).build());
        this.courses.add(CursoDTO.builder().name("MongoDB").isActive(Boolean.FALSE).build());
    }

    @Override
    public List<CursoDTO> findCourses() {
        return this.courses;
    }

    @Override
    public List<CursoDTO> findCurrentCourses() {
        return this.courses.stream().filter(c -> c.getIsActive()).collect(Collectors.toList());
    }
}
