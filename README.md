configuration
-------------
Skip if you using pre-configured zip archive
- go to project root
- build: mvn clean install
- copy from maven repository (online) to lib folder in hsqldb folder following libraries:
    activemq-client-5.11.0.jar
    aopalliance-1.0.jar
    commons-logging-1.2.jar
    geronimo-jms_1.1_spec-1.1.jar
    geronimo-spec-j2ee-management-1.0-M1.jar
    logback-classic-1.1.3.jar
    logback-core-1.1.3.jar
    slf4j-api-1.7.12.jar
    spring-aop-4.1.6.RELEASE.jar
    spring-beans-4.1.6.RELEASE.jar
    spring-context-4.1.6.RELEASE.jar
    spring-core-4.1.6.RELEASE.jar
    spring-expression-4.1.6.RELEASE.jar
    spring-jms-4.1.6.RELEASE.jar
    spring-messaging-4.1.6.RELEASE.jar
    spring-tx-4.1.6.RELEASE.jar
- copy target/*jar file to lib folder in hsqldb folder
- copy target/*jar file to lib folder in activemq folder (this will allow to see object in the activemq console but is not essential)