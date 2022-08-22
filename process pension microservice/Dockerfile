FROM openjdk:17
EXPOSE 9081
ADD "target/process-pension-microservice.jar" "process-pension-microservice.jar"
ENTRYPOINT [ "java", "-jar", "/process-pension-microservice.jar" ]