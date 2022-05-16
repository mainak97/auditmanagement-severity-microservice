FROM public.ecr.aws/docker/library/openjdk:11-oracle
LABEL maintainer="auditmanagement.severity"
ADD target/audit-management-severity-0.0.1-SNAPSHOT.jar audit-management-severity.jar
ENTRYPOINT ["java","-jar","audit-management-severity.jar"]