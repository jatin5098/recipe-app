#Pull Docker Image
FROM eclipse-temurin:21-jdk

#Working Directory
RUN mkdir -p /recipe-app
WORKDIR /recipe-app

#Port
EXPOSE 8080

#Copy mvn and POM to the container image
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

#Resolves all projects dependency
RUN ./mvnw dependency:go-offline

#Copy java source code and pre-environment configuration files
COPY src ./src

#The Spring Boot Maven plugin includes a run goal
CMD ["./mvnw", "spring-boot:run"]

#Build
### docker build --platform linux/amd64 -t recipe-app .

#Run
### docker run -p 8080:8080 -e "SPRING_PROFILES_ACTIVE=local" recipe-app:latest
