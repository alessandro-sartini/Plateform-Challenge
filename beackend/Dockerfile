# ===== STAGE 1: BUILD =====
FROM maven:3.9.8-eclipse-temurin-21 AS build
WORKDIR /workspace

# Copia i file di progetto e risolvi le dipendenze (cache-friendly)
COPY pom.xml .
RUN mvn -q -e -DskipTests dependency:go-offline

# Copia il codice e compila
COPY src ./src
RUN mvn -q -DskipTests clean package

# ===== STAGE 2: RUNTIME =====
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copia il JAR costruito
COPY --from=build /workspace/target/*.jar /app/app.jar

# Render fornisce la porta nella variabile d'ambiente $PORT
ENV PORT=8080
ENV JAVA_TOOL_OPTIONS="-Xmx256m"

CMD ["sh", "-c", "java -Dserver.port=${PORT} -jar /app/app.jar"]
