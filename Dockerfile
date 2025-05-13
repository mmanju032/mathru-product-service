FROM openjdk:11
EXPOSE 8001
ADD target/mathru-product-service.jar mathru-product-service.jar
ENTRYPOINT ["java", "-jar" , "/mathru-product-service.jar"]