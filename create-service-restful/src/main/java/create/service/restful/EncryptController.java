package create.service.restful;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/")
public class EncryptController {

    private final EncryptService encryptService;

    public EncryptController(final EncryptService encryptService) {
        this.encryptService = encryptService;
    }

    @Get("encrypt/{text}")
    public EncryptDTO encrypt(final String text){
        return encryptService.encrypt(text);
    }
}
