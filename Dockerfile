FROM 	          openjdk:oracle
RUN 	          mkdir /my-app
COPY	         ./build/libs/dictionary-springboot-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT  ["java", "-jar", "app.jar"]