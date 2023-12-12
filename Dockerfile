# Usa una imagen base con Java
FROM eclipse-temurin:17

# Argumentos de construcción
ARG url
ARG user
ARG pasword

# Establece variables de entorno
ENV SPRING_DATASOURCE_URL=$url \
    SPRING_DATASOURCE_USERADMIN=$user \
    SPRING_DATASOURCE_PASWORD=$pasword

# Copia el archivo JAR de tu aplicación al contenedor
COPY build/libs/platzi-market-1.0.jar platzi-market-1.0.jar

# Comando para ejecutar tu aplicación
CMD ["java", "-jar", "-Dspring.profiles.active=pdn", "platzi-market-1.0.jar"]
#CMD ["java", "-jar", "platzi-market-1.0.jar"]

# Expone el puerto 8080
EXPOSE 8080


