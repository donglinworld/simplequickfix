#build
mvn -f simple_pom.xml clean package -Dmaven.javadoc.skip=true -DskipTests -Dmaven.test.skip=true
