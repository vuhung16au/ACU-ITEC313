# JUnit Quick Start

## Prerequisites

- Java 25
- Maven 3.9+
- Make (optional)

```bash
java -version
mvn -version
```

## All projects (from `JUnit/`)

```bash
cd JUnit

make help    # list targets
make build   # compile every project
make test    # run all JUnit tests
make run     # run every demo app
make clean   # remove build artifacts
```

Or with Maven from the parent:

```bash
cd JUnit
mvn clean test
```

## One project

```bash
cd JUnit/00-00-JUnit-Calculator   # or any other subfolder

make build   # mvn clean compile
make test    # mvn test
make run     # mvn exec:java
make clean   # mvn clean
make help
```

Same targets work in:

- `00-00-JUnit-Calculator`
- `00-01-JUnit-Bubble-Sort`
- `00-03-JUnit-Custom-Stack`
- `10-00-Test-Driven-Development`

## Without Make

```bash
cd JUnit/00-00-JUnit-Calculator

mvn clean compile
mvn test
mvn exec:java
mvn clean
```
