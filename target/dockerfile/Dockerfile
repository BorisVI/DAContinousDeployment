FROM openjdk:8-jdk-alpine
RUN  apk update && apk upgrade && apk add netcat-openbsd
RUN mkdir -p /usr/local/review-service
ADD  review-service-0.1.0.jar /usr/local/review-service/
ADD run.sh run.sh
RUN chmod +x run.sh
CMD ./run.sh