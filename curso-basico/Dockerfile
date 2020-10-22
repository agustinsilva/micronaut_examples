FROM openjdk:14-alpine
COPY build/libs/hello-work-*-all.jar hello-work.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "hello-work.jar"]