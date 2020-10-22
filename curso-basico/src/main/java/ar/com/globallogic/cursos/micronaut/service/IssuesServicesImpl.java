package ar.com.globallogic.cursos.micronaut.service;

import ar.com.globallogic.cursos.micronaut.dto.Issue;

import javax.inject.Singleton;
import java.time.LocalDateTime;
import java.util.*;

@Singleton
public class IssuesServicesImpl implements IssuesServices{
    private Set<Issue> issues;

    public IssuesServicesImpl(){
        this.issues = new TreeSet<>();
    }

    public Optional<Issue> findById(final Integer id){
        return this.issues.stream()
                .filter(issue -> issue.getId().equals(id))
                .findAny();
    }

    public void add(final Issue issue) {
        this.issues.add(issue);
    }
}
