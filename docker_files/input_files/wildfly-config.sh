#!/bin/bash

# Thanks for Merek Goldmann for this helper
JBOSS_HOME=/opt/jboss/wildfly
JBOSS_CLI=$JBOSS_HOME/bin/jboss-cli.sh
JBOSS_MODE=${1:-"standalone"}
JBOSS_CONFIG=${2:-"$JBOSS_MODE.xml"}

function wait_for_server() {
  until `$JBOSS_CLI -c "ls /deployment" &> /dev/null`; do
    sleep 1
  done
}

echo "==> Starting WildFly server ..."
$JBOSS_HOME/bin/$JBOSS_MODE.sh -c $JBOSS_CONFIG &

echo "==> Waiting for the server to boot ..."
wait_for_server

echo "==> Executing the datasource commands ..."
touch drugi.txt
$JBOSS_CLI -c --user=admin --password=admin --file=$JBOSS_HOME/bin/install-datasource.cli


echo "==> Shutting down WildFly..."
if [ "$JBOSS_MODE" = "standalone" ]; then
  $JBOSS_CLI -c ":shutdown"
else
  $JBOSS_CLI -c "/host=*:shutdown"
fi

# Fix for Error: Could not rename /opt/jboss/wildfly/standalone/configuration/standalone_xml_history/current
# Taken from Dirk Nachbar on https://github.com/DirkNachbar/Docker/blob/master/WildFly/oracle-config.sh
rm -rf /opt/jboss/wildfly/standalone/configuration/standalone_xml_history
