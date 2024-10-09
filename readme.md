

# Unitconverter 

This is a simple Spring Boot application that uses Gradle as the build tool. This README provides the instructions for setting up, building, and running the project.

## Prerequisites

Ensure you have the following installed:

- **Java Development Kit (JDK)** version 21 or higher.
- **Gradle** (Although the project comes with a Gradle wrapper, so you don't need to install it globally).

## Project Structure

The project follows the standard structure of a Spring Boot application:
```
├── src
│   ├── main
│   │   ├── java        # Java source files
│   │   └── resources   # Configuration files (e.g., application.properties)
│   └── test
│       └── java        # Unit tests
├── build.gradle        # Gradle build script
├── settings.gradle     # Gradle settings
└── README.md           # Project README
```

## Getting Started

### Clone the repository

If you haven't cloned the repository yet, do so with:

```bash
git clone https://github.com/AnnaGoralewicz/Unitconverter.git
cd your-repo
```

### Build the project

To build the project, use the following Gradle command:

```bash
./gradlew build 
```

This will compile the project, run the tests, and package the application into an executable JAR file located in the `build/libs/` directory.

### Running the application

After building the project, you can run the Spring Boot application using:

```bash
./gradlew bootRun
```

Alternatively, if you have already built the project and want to run the JAR directly:

```bash
java -jar build/libs/your-app-name-version.jar
```

### Running tests

To run all the unit tests, use:

```bash
./gradlew test
```

## Configuration

You can configure application settings in the `src/main/resources/application.properties` file.

....

```
server.port=8080
```

## Useful Gradle Commands

- `./gradlew build` – Builds the project and runs tests.
- `./gradlew bootRun` – Runs the application directly from the source.
- `./gradlew clean` – Cleans the build directory.
- `./gradlew test` – Runs the tests.

## Contributing

1. Fork the repository.
2. Create a feature branch (`git checkout -b feature-branch`).
3. Commit your changes (`git commit -am 'Add new feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Create a new Pull Request.