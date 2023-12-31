# Quarkus-Java-Rest-Mongo 

A Quarkus Java bootstrap application using Rest and MongoDB.

## Running the application in dev mode

Follow the steps below to run the app in dev mode:

1. Update the MongoDB connection string to match your connection in `application.properties` . 
2. Create a database named `testmongo` or change the name `testmongo` used in the method `FruitsService.getCollection()`
3. Run the app with live coding using:

```shell script
./mvnw compile quarkus:dev
```

* Quarkus ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/
* Swagger is available at http://localhost:8080/q/swagger-ui/

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```bash
./mvnw package -Dquarkus.package.type=uber-jar
# In powershell, execute the following
./mvnw package "-Dquarkus.package.type=uber-jar"
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```bash
./mvnw package -Pnative -Dquarkus.native.container-build=true
# In powershell, execute the following
./mvnw package -Pnative "-Dquarkus.native.container-build=true"
```

You can then execute your native executable with: `./target/app-quarkus-1.0.0-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

More samples available at Quarkus Github [here](https://github.com/quarkusio/quarkus-quickstarts). 

Enjoy! :heart:
