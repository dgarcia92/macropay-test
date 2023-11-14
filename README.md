
# Address Book App

Esta es una aplicación de libreta de direcciones desarrollada en Java utilizando Spring Boot.

## Requisitos previos

- [Java](https://www.oracle.com/java/technologies/javase-downloads.html) (versión 11 o superior)
- [Maven](https://maven.apache.org/download.cgi)

## Instrucciones para ejecutar

1. **Clonar el repositorio:**

   ```bash
   git clone https://github.com/dgarcia92/macropay-test.git
   ```

   ```bash
   mvn clean install
    ```

    ```bash
   mvn spring-boot:run
   
     ó
   
   java -jar .\target\address-book-0.0.1.jar

   ```


## Pruebas

curl http://localhost:8080/contacts

curl http://localhost:8080/contacts?phrase=John

curl http://localhost:8080/contacts/1

curl -X DELETE http://localhost:8080/contacts/1
