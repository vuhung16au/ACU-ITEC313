# ITEC313 / ITEC621 - Advanced Programming Concepts

Java programming resources and projects for **ITEC313** (Advanced Programming Concepts) and **ITEC621** (Advanced Programming Principles).

## 📁 Repository Structure

| Folder | Purpose |
|--------|---------|
| **00.Java-Basic** | Java fundamentals refresher - syntax, data types, loops, OOP basics |
| **00.Overview-Preparation** | Course prep - environment setup, JDK installation, IDE configuration |
| **01.Maven** | Introduction to Maven build tool and dependency management |
| **JavaFX** | **Main course content** - GUI programming, event handling, animations (ITEC313, ITEC621) |
| **JUnit** | Unit testing fundamentals and best practices with Java |
| **MobileApps** | Mobile app development with Java (TBC) |
| **SpringBoot** | Enrichment - collection of apps using Spring Boot framework |

## 🚀 Getting Started

### Prerequisites

- **Java Development Kit (JDK) 17+ or higher** - [Oracle](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.org/)
- **Build tools**: Maven, Gradle, or GNU Make
- **IDE**: VS Code, IntelliJ IDEA, or Eclipse (optional)

Note: This repo has been tested with JDK 25 (and a bit with JDK 26)

### Quick Start

Navigate to the desired folder and follow the project README or build instructions:

```bash
# Example: Run JavaFX project
cd JavaFX/01-01-JavaFX-HelloWorld
mvn clean javafx:run

# Example: Run Java basic exercises
cd 00.Java-Basic/HelloWorld
make run
```

## 🛠 Development Environment

**Recommended IDEs:**
- VSCode (lightweight)
- IntelliJ IDEA (professional)
- Eclipse (open-source)

**Build Tools:**
- Maven (recommended for JavaFX projects)
- Gradle (Try this if you prefer Gradle over Maven)
- Make (old but still useful)

## 📖 Code Standards

- **Classes**: PascalCase (e.g., `HelloWorld`)
- **Methods/Variables**: camelCase (e.g., `getValue()`)
- **Constants**: UPPER_SNAKE_CASE (e.g., `MAX_SIZE`)
- All public classes and methods should include Javadoc comments

## 🤝 Contributing

File GitHub issues for bugs, feature requests, or improvements. Pull requests are welcome.

## 📄 License

MIT
