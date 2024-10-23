FROM openjdk:17-jdk-slim
RUN addgroup --system spring && adduser --system spring -ingroup spring
COPY target/<WALLET_FOLDER>/* /<WALLET_FOLDER>/
RUN chown -R spring:spring /<WALLET_FOLDER>
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
ENV PORT 8080
ENV HOME=/
EXPOSE 8080