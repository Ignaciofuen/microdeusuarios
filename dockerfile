FROM eclipse-temurin:17-jdk as compile
WORkDIR /app
COPY . .
RUN ./mvnw clean package

FROM eclipse-temurin:17-jdk as prod
WORKDIR /app

COPY --from=compile /app/target/*.jar app.jar
EXPOSE 8080

CMD ["java","-jar","app.jar"]
