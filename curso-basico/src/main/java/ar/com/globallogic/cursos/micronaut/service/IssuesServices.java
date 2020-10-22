package ar.com.globallogic.cursos.micronaut.service;

import ar.com.globallogic.cursos.micronaut.dto.Issue;

import java.util.Optional;

public interface IssuesServices {
    public Optional<Issue> findById(final Integer id);

    public void add(final Issue issue);
}
