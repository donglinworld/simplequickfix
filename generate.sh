#!/bin/sh

#update gernerated source
rm -f quickfixj-core/src/main/java/quickfix/field/*.java
mvn clean package -Dmaven.javadoc.skip=true -DskipTests -Dmaven.test.skip=true -PskipBundlePlugin
cp -f quickfixj-core/target/generated-sources/quickfix/field/*.java quickfixj-core/src/main/java/quickfix/field/
rm -rf quickfixj-core/target
mvn clean

#build
#mvn -f simple_pom.xml clean package

