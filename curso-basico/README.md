# Curso básico de Micronaut 

__Basic__:

* [ ] Config enviroment
* [x] Create Proyect
* [x] Develop a simple Rest Service.
* [x] Configure and expose a OpenAPI Definition.
* [x] Configure API Versioning. 
* [ ] Configure an API Security (HTTPS, CORS, etc)
* [ ] Develop unit tests with asserts.
* [x] Develop an error handler.
* [x] Implements fields validation.

## Config enviroment

---
---
---
## Create Project 

Tenemos al menos 2 formas de iniciar un proyecto con micronaut, a través del micronaut cli y mediante la web de micronaut lunch.

La forma más básica de crear una aplicación con el cliente de micronaut es ejecutando el siguiente comando.

> mn create-app hello-world

Si se quiere utilizar la opción web tienen que acceder a [lunch](https://micronaut.io/launch/)

---
---
---

## Create service restful

Para exponer nuestro primer servicio restful, lo primero que tenemos que hacer es crear nuestro punto de acceso y para esto tendremos 
que realizar los siguientes pasos:
* Crear nuestra clase controller.
* Agregar a nuestra clase controller la anotación @Controller.
* Crear los métodos que queremos exponer.
* Configurar los verbos http a los métodos creados.

Dejamos un ejemplo de cómo quedaría un controller que exponer el método greetings en a través del verbo Get

    import io.micronaut.http.MediaType;
    import io.micronaut.http.annotation.Controller;
    import io.micronaut.http.annotation.Get;
    
    @Controller("/")
    public class HelloController {
    
        @Get(uri="/greetings/{name}", produces = MediaType.TEXT_PLAIN)
        public String greetings(String name ) {
            return "Hello " + name;
        }
    }
    
### Routing Annotations

**Annotation** | **HTTP Method**
---------------|----------------
@Detele        | DELETE
@Get           | GET
@Head          | HEAD
@Options       | OPTIONS
@Path          | PATH
@Put           | PUT
@Post          | POST
@Trace         | TRACE
@CustomHttpMethod | NON-STANDARD HTTP METHOD   

Ejemplo del uso de la annotation @CustomHttpMethod 

    @CustomHttpMethod(method = "LOCK", value = "/{name}")
    String lock(String name){....}

### Binding Annotations

**Annotation** | **Description**
---------------|----------------
@Body | Binds from the body of the request
@CookieValue | Binds a parameter from a cookie
@Header | Binds a parameter from an HTTP header
@QueryValue | Binds from a request query parameter
@Part | Binds from a part of a multipart request
@RequestAttribute | Binds from an attribute of the request. Attributes are typically created in filters
@PathVariable | Binds from the path of the request
@RequestBean | Binds any Bindable value to single Bean object

---
---
---

## Configure and expose a OpenAPI Definition.

Documentación oficial: [Link](https://micronaut-projects.github.io/micronaut-openapi/latest/guide/index.html)

### Dependencias

#### Gradle

    annotationProcessor("io.micronaut.configuration:micronaut-openapi:1.5.2")
    implementation("io.swagger.core.v3:swagger-annotations")

#### Maven
    <annotationProcessorPaths>
        <path>
            <groupId>io.micronaut.configuration</groupId>
            <artifactId>micronaut-openapi</artifactId>
            <version>1.5.2</version>
        </path>
    </annotationProcessorPaths>
    
    <dependency>
        <groupId>io.swagger.core.v3</groupId>
        <artifactId>swagger-annotations</artifactId>
    </dependency>

### Documentación

#### Información general del MS

Veamos cuales son las anotaciones que tenemos que utilizar para generar el siguiente swagger.

    openapi: 3.0.1
    info:
      title: the title
      description: My API
      contact:
        name: Fred
        url: https://gigantic-server.com
        email: Fred@gigagantic-server.com
      license:
        name: Apache 2.0
        url: https://foo.bar
      version: "0.0"

En nuestra clase Application, tendremos que agregar el siguiente código, a la altura de la declaración de la clase.

    @OpenAPIDefinition(
            info = @Info(
                    title = "Hello World",
                    version = "0.0",
                    description = "My API Hello World",
                    license = @License(name="Apache 2.0", url = "https://boo.bar"),
                    contact = @Contact(url = "https://gigantic-server.com", name = "Fred", email = "Fred@gigagantic-server.com")
            )
    )

Si ahora compilamos nuestro proyecto, notaremos que se nos generará un archivo .yml dentro de la carpeta

> build/classes/java/main/META-INF/swagger/hello-world-0.0.yml    

El cual contendrá la siguiente información

    openapi: 3.0.1
    info:
      title: Hello World
      description: My API Hello World
      contact:
        name: Fred
        url: https://gigantic-server.com
        email: Fred@gigagantic-server.com
      license:
        name: Apache 2.0
        url: https://boo.bar
      version: "0.0"
    paths:
      /hello:
        get:
          operationId: index
          parameters: []
          responses:
            default:
              description: index default response
              content:
                text/plain:
                  schema:
                    type: string

### Exponemos nuestro swagger

Para poder exponer nuestro swagger tendremos que agregar las siguientes lineas en nuestro application.yml

    micronaut:
      router:
        static-resources:
          swagger:
            paths: classpath:META-INF/swagger
            mapping: /swagger/**

Para poder ver nuestro ejemplo, tendremos que acceder a la siguiente url

> http://localhost:8080/swagger/hello-world-0.0.yml

Por defecto en micronaut viene deshabilitado los visores de swagger. Si los queremos habilitar tenemos varias opciones, para nuestro ejemplo se habilitó por medio de nuestro archivo de build.gradle. 
Para esto lo que lo único que se hizo fue agregar las siguientes 2 lineas dentro de nuestro tasks.withType(JavaCompile) 

    options.fork = true
    options.forkOptions.jvmArgs << '-Dmicronaut.openapi.views.spec=rapidoc.enabled=true,swagger-ui.enabled=true,swagger-ui.theme=flattop'

Ahora podremos ver nuestra especificación de API mucho mejor si entramos a

> http://localhost:8080/swagger/views/swagger-ui/index.html#/default/index

### Controlle
 -- echo, falta agregar la documentacion de un schema

---
---
---

## Configure API Versioning.
A Partir de la versión 1.1 Micronaut soporta el versionado de API a través de la anotación **@Version**.

_Ejemplo_

    import io.micronaut.core.version.annotation.Version;
    import io.micronaut.http.annotation.Controller;
    import io.micronaut.http.annotation.Get;

    @Controller("/versioned")
    class VersionedController {
    
        @Version("1") 
        @Get("/hello")
        String helloV1() {
            return "helloV1";
        }
    
        @Version("2") 
        @Get("/hello")
        String helloV2() {
            return "helloV2";
        }
     }

Luego, debe habilitar el control de versiones configurando micronaut.router.versioning.enabled en true en application.yml:

    micronaut:
        router:
            versioning:
                enabled: true

> Además de habilitar el versionado, tenemos que habilitar la forma en que lo queremos hacer de lo contrario micronaut no realizará el 
> ruteo

De manera predeterminada, Micronaut tiene 2 estrategias listas para usar para resolver la 
versión que se basan en un encabezado HTTP llamado **X-API-VERSION** o un parámetro de 
solicitud llamado **api-version**, sin embargo, esto es configurable. A continuación se puede ver un ejemplo de configuración completo:

    micronaut:
        router:
            versioning:
                enabled: true //Habilita el control de versiones
                parameter:
                    enabled: false // Habilita o deshabilita el control de versiones basado en parámetros
                    names: 'v,api-version' //Especifique los nombres de los parámetros como una lista separada por comas
                header:
                    enabled: true // Habilita o deshabilita el control de versiones basado en encabezados
                    names:  //Especifique los nombres del encabezado como una lista YAML
                        - 'X-API-VERSION'
                        - 'Accept-Version'
                        
> 

### Default Version
Es posible suministrar una versión predeterminada a través de la configuración.

    micronaut:
        router:
            versioning:
                enabled: true
                default-version: 3

> Se puede encontrar un ejemplo en la clase CourseController.java


### Client
El cliente HTTP declarativo de Micronaut también admite el control de versiones automático de las solicitudes salientes a través de la anotación **@Version**.

De forma predeterminada, si anota una interfaz de cliente con @Version, el valor proporcionado a la anotación se incluirá mediante el encabezado **X-API-VERSION**.

_Ejemplo_

    import io.micronaut.core.version.annotation.Version;
    import io.micronaut.http.annotation.Get;
    import io.micronaut.http.client.annotation.Client;
    import io.reactivex.Single;
    
    @Client("/hello")
    @Version("1") 
    public interface HelloClient {
    
        @Get("/greeting/{name}")
        String sayHello(String name);
    
        @Version("2")
        @Get("/greeting/{name}")
        Single<String> sayHelloTwo(String name); 
    }
> Se puede encontrar un ejemplo funcionando en el test de la clase CourseController.java

---
---
---

## Configure an API Security (HTTPS, CORS, etc)

### HTTPS --> [Link](https://docs.micronaut.io/latest/guide/index.html#https)
Micronaut admite HTTPS listo para usar. De forma predeterminada, HTTPS está desactivado y todas las solicitudes se 
atienden mediante HTTP. Para habilitar la compatibilidad con HTTPS, solo tendremos que modificar nuestro application.yml. 

  ssl:
    enabled: true
    buildSelfSigned: true # Micronaut creará un certificado autofirmado.

> De forma predeterminada, Micronaut con soporte HTTPS comienza en el puerto, 8443pero puede cambiar la propiedad del 
> puerto micronaut.ssl.port


### CORS --> [Link](https://docs.micronaut.io/latest/guide/index.html#cors)
De forma predeterminada micronaut rechaza las solicitudes CORS. Para habilitar el procesamiento
de solicitudes CORS, se tiene que modificar la configuración en el application.yml.

    micronaut:
        server:
            cors:
                enabled: true

Solo habilitando el procesamiento CORS, se adoptará una estrategia **abierta** que permitirá solicitudes de 
cualquier origen.

Para cambiar la configuración para todos los orígenes o un origen específico, tendremos que manipular la 
sección de configurations dentro de cors. Al proporcionar cualquier configuración, la configuración 
predeterminada **completamente abierta** no está configurada.

    micronaut:
        server:
            cors:
                enabled: true
                configurations:
                    all:
                        ...
                    web:
                        ...
                    mobile:
                        ...

En el ejemplo anterior, se proporcionan tres configuraciones. Sus nombres ( all, web, mobile) no son 
importantes y no tienen significado dentro Micronaut. Están ahí únicamente para poder reconocer fácilmente 
al usuario previsto de la configuración.

### Allowed origins (Orígenes permitidos)
Para permitir cualquier origen para una configuración determinada, simplemente no incluya la **allowedOrigins** clave 
en su configuración.

Para especificar una lista de orígenes válidos, establezca la **allowedOrigins** clave de la configuración en una lista de 
cadenas. Cada valor puede ser un valor estático ( http://www.foo.com) o una expresión regular 
(^http(|s)://www\.google\.com$).

    micronaut:
        server:
            cors:
                enabled: true
                configurations:
                    web:
                        allowedOrigins:
                            - http://foo.com
                            - ^http(|s):\/\/www\.google\.com$

### Allowed Methods (Métodos permitidos)

Para permitir cualquier método de solicitud para una configuración determinada, simplemente no incluya la 
**allowedMethods** clave en su configuración.

Para especificar una lista de métodos permitidos, establezca la **allowedMethods** clave de la configuración en una 
lista de cadenas.

    micronaut:
        server:
            cors:
                enabled: true
                configurations:
                    web:
                        allowedMethods:
                            - POST
                            - PUT
                        
### Allowed Headers (Encabezados permitidos)

Para permitir cualquier encabezado de solicitud para una configuración determinada, simplemente no incluya la 
**allowedHeaders** clave en su configuración.

Para especificar una lista de encabezados permitidos, establezca la **allowedHeaders** clave de la configuración en una 
lista de cadenas.

    micronaut:
        server:
            cors:
                enabled: true
                configurations:
                    web:
                        allowedHeaders:
                            - Content-Type
                            - Authorization
                        
### Exposed Headers (Encabezados expuestos)
Para configurar la lista de encabezados que se envían en respuesta a una solicitud CORS a través del 
**Access-Control-Expose-Headersencabezado**, incluya una lista de cadenas para la **exposedHeaders** clave en su 
configuración. De forma predeterminada, no se exponen encabezados.

    micronaut:
        server:
            cors:
                enabled: true
                configurations:
                    web:
                        exposedHeaders:
                            - Content-Type
                            - Authorization

### Allow Credentials (Permitir credenciales)
Las credenciales están permitidas de forma predeterminada para las solicitudes CORS. Para no permitir las credenciales, 
simplemente configure la **allowCredentials** opción en **false**.

    micronaut:
        server:
            cors:
                enabled: true
                configurations:
                    web:
                        allowCredentials: false
### Max Age (Edad máxima)
La edad máxima predeterminada en la que las solicitudes de verificación previa se pueden almacenar en caché es de 30 
minutos. Para cambiar ese comportamiento, especifique un valor en segundos.

    micronaut:
        server:
            cors:
                enabled: true
                configurations:
                    web:
                        maxAge: 3600 # 1 hour

### Multiple Header Values (Varios valores de encabezados)
De forma predeterminada, cuando un encabezado tiene varios valores, se enviarán varios encabezados, cada uno con un 
solo valor. Es posible cambiar el comportamiento para enviar un solo encabezado con la lista de valores separados por 
comas estableciendo una opción de configuración.

    micronaut:
        server:
            cors:
                single-header: true

---
---
---

## Develop unit tests with asserts.

## Develop an error handler --> [Link](https://docs.micronaut.io/latest/guide/index.html#errorHandling)

Tenemos 2 formas de manejar los errores, de forma **local** y de forma **global**.

### Forma local

Manejar los errores de forma local significa declarar y manejar solo los errores que se produzcan dentro del controller. 
Para esto dispondremos de 2 alternativas, especificando el tipo de exception o el tipo de http status

_Ejemplo especificando el tipo de exception_

    @Error
    public HttpResponse<JsonError> jsonError(HttpRequest request, JsonParseException jsonParseException) {
        JsonError error = new JsonError("Invalid JSON: " + jsonParseException.getMessage())
            .link(Link.SELF, Link.of(request.getUri()));

        return HttpResponse.<JsonError>status(HttpStatus.BAD_REQUEST, "Fix your json").body(error);
    }

_Ejemplo especificando el tipo de exception o el tipo de http status_

    @Error(status = HttpStatus.NOT_FOUND)
    public HttpResponse<JsonError> notFound(HttpRequest request) {
       JsonError error = new JsonError("Person Not Found").link(Link.SELF, Link.of(request.getUri()));

       return HttpResponse.<JsonError>notFound().body(error);
    }

### Forma global
Para manejar los errores que se produzcan fuera del controller solo tenemos que settear en **true** el atributo **global** 
de la anotación **@Error**

_Ejemplo_
    
    @Error(global = true, status = HttpStatus.NOT_FOUND)
    public HttpResponse<JsonError> error(HttpRequest request) {
        JsonError error = new JsonError("Bad Things Happened: ")
                .link(Link.SELF, Link.of(request.getUri()));

        return HttpResponse.<JsonError>notFound()
                .body(error);
    }

> Se puede encontrar los ejemplos funcionales sobre el manejo de errores dentro del controller CoursesController.

### Exception Handler
Adicionalmente, podremos implementar un ExceptionHandler; un componente genérico para manejar excepciones que ocurren durante la 
ejecución de una solicitud HTTP.

Realizar esta implementación es bastante simple, solo tenemos que crear una clase la cual implemente la interfaz ExceptionHandler e implementar el método handler.

_Ejemplo_

    @Singleton
    public class ErrorHandlerCustom implements ExceptionHandler<RuntimeException, HttpResponse> {
    
        @Override
        public HttpResponse handle(HttpRequest request, RuntimeException exception) {
            return HttpResponse.ok();
        }
    }
    
> Se puede encontrar los ejemplos funcionales sobre la implementación del error handler en la claseErrorHandlerCustom.

---
---
---
## Implements fields validation.
Lo importante a tener a recordar cuando trabajamos con las validaciones, es que Micronaut es un framawork que trata de evitar la reflection lo más que puede. Por tal motivo en algunas ocasiones tendremos que tener algunas consideraciones especiales.

En particular las validaciones que nos importan en este momento son las que podemos incluir en nuestros **endponit**.

Para intentar simplificar el tema, se separará esta sección en dos partes. En la primer parte se mostrará cómo validar los parámetros de un método Get y se listara algunas de las notaciones que nos brinda Micronaut. En la parte dos se vera como validar los datos de un método Post.

### Valid Get Method

El manejo de las validaciones en los métodos **Get**, es bastante simple, lo único que tenemos que hacer es agregar el annotations que necesitamos en el parámetro que necesitemos validar.

Por ejemplo si tenemos un método GET el cual recibe como entrada dos filtros, **description** y **status**, y retorna sus valores

    @Get("/")
    public String findByDescription(final String description, final String status) {
        return "Los valores de los parametros de entrada son: Description = " + description +
                " ; status = " + status;
    }
    
y queremos que el campo descripción no pueda contener datos vacíos y por otro lado nuestro campo status queremos que sea opcional. Esto lo podremos configurar agregando las annotation @NotBlank y @Nullable
    
    @Get("/")
    public String findByDescription(@NotBlank(message = "El campo description no puede contener datos en limpio") final String description, @Nullable final String status) {
        return "Los valores de los parametros de entrada son: Description = " + description +
                " ; status = " + status;
    }
    
### Listado de annotations

**Annotations** | **Description**
----------------|----------------
@Min            | Nos permite indicar el valor mínimo permitido
@Max            | Nos permite indicar el valor máximo permitido
@Nullable       | Nos permite indicar el atributo puede ser nulo
@NotBlank       | Nos permite indicar el atributo no puede ser nulo
@NotNull        | Nos permite indicar el atributo no puede tener un valor vacío
@Valid          | Nos permite indicar que queremos validar el body

------------
------------
------------
agregar en las ppt
Este es un enfoque similar adoptado por herramientas como Dagger de Google , que está diseñado principalmente con Android en mente. Micronaut, por otro lado, está diseñado para construir microservicios del lado del servidor y proporciona muchas de las mismas herramientas y utilidades que Spring pero sin usar reflexión o almacenar en caché cantidades excesivas de metadatos de reflexión.

Los objetivos del contenedor Micronaut IoC se resumen como:
* Utilice la reflexión como último recurso
* Evite los proxies
* Optimice el tiempo de inicio
* Reducir la huella de memoria
* Proporcionar un manejo de errores claro y comprensible

* The goals of the Micronaut IoC container are summarized as:
* Use reflection as a last resort
* Avoid proxies
* Optimize start-up time
* Reduce memory footprint
* Provide clear, understandable error handling