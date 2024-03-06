#!/bin/bash

LIBSPATH=/usr/lib:/usr/lib/x86_64-linux-gnu
export LD_LIBRARY_PATH=$LIBSPATH

APPDIR=/home/trifanya/Java/NICTP/crudapp/server
JAVA=java
MAINCLASS=dev.trifanya.server_app.ServerApp

cd $APPDIR

#mvn liquibase:update
exec $JAVA \
  -server \
  -Djava.library.path=$LIBSPATH \
  -Dapp.name=nictp_crud_server \
  -Xms1024m \
  -Xmx1024m \
  -XX:+UseParNewGC \
  -XX:ObjectAlignmentInBytes=16 \
  -Djava.net.preferIPv4Stack=true \
  -cp "$APPDIR/lib/*" \
  $MAINCLASS &