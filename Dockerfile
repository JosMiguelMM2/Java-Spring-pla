# Usa una imagen base con Java
FROM eclipse-temurin:17

# Argumentos de construcción
#ARG url
#ARG user
#ARG pasword

# Establece variables de entorno
#ENV SPRING_DATASOURCE_URL=$url \
 #   SPRING_DATASOURCE_USERADMIN=$user \
 #   SPRING_DATASOURCE_PASWORD=$pasword


ENV SPRING_DATASOURCE_URL=jdbc:postgresql://dpg-clq9j09jvg7s73e4rbgg-a.oregon-postgres.render.com/platzi_market_vyyw \
    SPRING_DATASOURCE_USERADMIN=admin \
    SPRING_DATASOURCE_PASWORD=dDGLsOvcfZGv02TUHFdQ91zlRVSbc8jC

# Copia el archivo JAR de tu aplicación al contenedor
COPY build/libs/platzi-market-1.0.jar platzi-market-1.0.jar

# Comando para ejecutar tu aplicación
CMD ["java", "-jar", "-Dspring.profiles.active=pdn", "platzi-market-1.0.jar"]
#CMD ["java", "-jar", "platzi-market-1.0.jar"]

# Expone el puerto 8080
EXPOSE 8080


