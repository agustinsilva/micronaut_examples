package ar.com.globallogic.cursos.micronaut.controller;

import ar.com.globallogic.cursos.micronaut.service.HelloService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.validation.constraints.NotBlank;

@Controller("/")
public class HelloController {

    private HelloService helloService;

    public HelloController(final HelloService helloService){
        this.helloService = helloService;
    }

    @Get(uri="/greetings/{name}", produces = MediaType.TEXT_PLAIN)
    @Operation(summary = "Greets a person",
            description = "A friendly greeting is returned"
    )
    @ApiResponse(
            content = @Content(mediaType = "text/plain",
                    schema = @Schema(type="string"))
    )
    @ApiResponse(responseCode = "400", description = "Invalid Name Supplied")
    @ApiResponse(responseCode = "404", description = "Person not found")
    @Tag(name = "greeting")
    public String greetings(@Parameter(description="The name of the person") @NotBlank String name ) {
        return this.helloService.getMessage(name);
    }
}
