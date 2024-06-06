# Use the official WildFly image from Docker Hub
FROM jboss/wildfly:latest

# Copy your application (WAR file) to the deployments directory
COPY target/TP12-WEB-EJB-PC-E-0.0.1-SNAPSHOT.war /opt/jboss/wildfly/standalone/deployments/

# Expose the default WildFly port
EXPOSE 8080

# Run WildFly
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]