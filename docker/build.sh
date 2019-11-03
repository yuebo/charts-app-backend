#!/usr/bin/env bash
rm -f *.jar
mvn clean install -f ../pom.xml -Dmaven.test.skip=true
cp ../target/charts-app.jar app.jar
docker build . -t charts-app:0.1