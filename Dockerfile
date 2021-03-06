FROM openjdk:11
ADD target/Genderdetector-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
CMD java -jar Genderdetector-0.0.1-SNAPSHOT.jar