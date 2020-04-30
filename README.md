# Micronaut Workshop

## Instalación de ambiente

### Setup de Micronaut CLI

1. Ingresar a [Docs Micronaut](https://micronaut.io/download.html "Docs Micronaut") y seleccionar en **DOWNLOAD MICRONAUT**, la opción **BINARY**.
2. Crear una carpeta **micronaut** en el directorio **C:** y extraer el zip descargado en el paso 1 en dicha carpeta.
3. Crear una variable de entorno de nombre **MICRONAUT_HOME** que apunte a **C:\micronaut\{nombre version micronaut}** , ejemplo: C:\micronaut\micronaut-1.3.4
4. Actualizar la variable de entorno **PATH**, agregando **%MICRONAUT_HOME%\bin**
                
### Setup de IDE

#### En intellij

Ingresar a * File > Settings > Build,Execution,Deployment > Annotation Processors * y seleccionar *Enable annotation processing* , luego seleccionar *Apply*.

#### En visual studio code

1. Ingresar a [Visual Studio Code](https://code.visualstudio.com/) para descargar el IDE.
2. Una vez instalado, instalar 2 extensiones: Java Extension Pack y Spring Boot Tools.
3. En la terminal (ctrl+ñ) , ejecutar el comando: ./gradlew eclipse
4. Al importar el proyecto, ejecutar en la terminal el comando: code .


## Ejemplos
A continuación se lista en orden los ejemplos generados en este repositorio:

1. create-service-restful
Explica como armar un servicio rest basico.