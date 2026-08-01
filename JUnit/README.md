# JUnit

Maven-based examples for **JUnit 6** unit testing in Java (ITEC313).

Each subfolder is a small Maven module. Shared JDK and JUnit versions live in the parent [`pom.xml`](pom.xml) (single source of truth).

## Projects

| Folder | Topic |
|--------|--------|
| `00-00-JUnit-Calculator` | Calculator with JUnit 6 (nested tests, parameterized tests, exceptions) |
| `00-01-JUnit-Bubble-Sort` | Bubble sort with focused unit tests |
| `00-03-JUnit-Custom-Stack` | Custom stack data structure + JUnit tests |
| `10-00-Test-Driven-Development` | TDD workflow (red → green → refactor) with bubble sort |

## Prerequisites

- Java 25
- Maven 3.9+
- Make (optional, for convenience targets)

## Shared versions (`JUnit/pom.xml`)

| Setting | Value |
|---------|--------|
| Java / compiler release | 25 |
| JUnit Jupiter | 6.1.2 |

Change those properties in the parent POM once; all four modules inherit them.

## Quick start

See [QUICKSTART.md](QUICKSTART.md).
