FROM jboss/wildfly:13.0.0.Final

# Set Postgresql env variables
ENV DB_HOST postgres
ENV DB_PORT 5432
ENV DB_NAME assignmentdb
ENV DB_USER docker
ENV DB_PASS docker

ENV DS_NAME AssignmentPool
ENV JNDI_NAME java:/AssignmentDS

USER root

ADD https://jdbc.postgresql.org/download/postgresql-42.2.5.jar /tmp/postgresql-42.2.5.jar

WORKDIR $JBOSS_HOME/bin/
COPY input_files/wildfly-config.sh ./
COPY input_files/install-datasource.cli ./

# Create Wildfly admin user
RUN $JBOSS_HOME/bin/add-user.sh admin admin --silent

RUN chmod +x ./wildfly-config.sh
RUN ./wildfly-config.sh

COPY Assignment-WEB.war $JBOSS_HOME/standalone/deployments

# Set the default command to run on boot
# This will boot WildFly in the standalone mode and bind to all interface
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
