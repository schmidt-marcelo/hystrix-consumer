FROM schmidt/tomcat

COPY tomcat-users.xml $CATALINA_HOME/conf/
COPY ./build/libs/hystrix-consumer-1.0.war $CATALINA_HOME/webapps/