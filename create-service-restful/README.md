# Create service restful

## Creación del proyecto
Lo primero que hacemos es crear el proyecto con el mn utilizando el siguiente comando

    create-app create-service-restful
    
Al terminar la ejecución del comando obtendremos un proyecto gradel con todas las dependencias que necesitaremos.

Nuestro objetivo al finalizar la siguiente lista de acciones es obtener un servicio que exponga un método GET el cual nos encripte un texto

## Crear DTO de respuesta
Creamos el siguiente DTO

    public class EncryptDTO {
        private String menssage;
    
        public EncryptDTO(String menssage) {
            this.menssage = menssage;
        }
    
        public String getMenssage() {
            return menssage;
        }
    
        public void setMenssage(String menssage) {
            this.menssage = menssage;
        }
    }

## Crear nuestro Service
Dentro de nuestra capa de servicio implementaremos la lógica de encriptación. Siguiendo la estructura habitual utilizada en los proyectos, crearemos una interfaz y su implementación.

<b>Interface</b>

    public interface EncryptService {
    
        public EncryptDTO encrypt(String text);
    }

<b>Class</b>
    
    import javax.inject.Singleton;
    
    @Singleton
    public class EncryptServiceImpl implements EncryptService {
    
        @Override
        public EncryptDTO encrypt(final String text){
            return new EncryptDTO(new StringBuilder(text).reverse().toString());
        }
    }

## Crear nuestro Controller
Por ultimo crearemos el controller que expondrá nuestro método de encriptación

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
 