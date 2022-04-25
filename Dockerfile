FROM openjdk:11
LABEL maintainer="auditmanagement.severity"
ADD target/audit-management-severity-0.0.1-SNAPSHOT.jar audit-management-severity.jar
ENTRYPOINT ["java","-jar","audit-management-severity.jar"]