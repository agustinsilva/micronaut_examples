package ar.com.globallogic.cursos.micronaut.controller;

import ar.com.globallogic.cursos.micronaut.dto.Issue;
import ar.com.globallogic.cursos.micronaut.service.IssuesServices;
import io.micronaut.context.annotation.*;
import io.micronaut.http.annotation.*;
import io.micronaut.validation.Validated;
import javafx.beans.NamedArg;

import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Validated
@Controller("/issues")
public class IssuesController {
    private IssuesServices issuesServices;

    public IssuesController(final IssuesServices issuesServices){
        this.issuesServices = issuesServices;
    }

    @Get("/{number}")
    public String issue(@PathVariable Integer number){
        return "Issue # " + this.issuesServices.findById(number);
    }

    @Post("/")
    public void add(@Body @Valid Issue issue, @QueryValue @NotBlank String name){
        System.out.println(name);

        this.issuesServices.add(issue);
    }

    @Get("/")
    public String findByDescription(@NotBlank(message = "El campo description no puede contener datos en limpio") final String description, @Nullable final String status) {
        return "Los valores de los parametros de entrada son: Description = " + description +
                " ; status = " + status;
    }
}
