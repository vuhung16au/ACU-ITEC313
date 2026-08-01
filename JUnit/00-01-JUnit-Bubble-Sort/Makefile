# JUnit Bubble Sort - Makefile
# Course: ITEC313 - Object-Oriented Programming

.PHONY: test build run clean help

test: ## Run JUnit tests
	mvn test

build: ## Compile the project
	mvn clean compile

run: ## Run the bubble sort demo
	mvn exec:java

clean: ## Remove build artifacts
	mvn clean

help: ## Show this help message
	@echo "JUnit Bubble Sort - Available targets:"
	@echo "  make test  - Run JUnit 6 tests"
	@echo "  make build - Clean and compile the project"
	@echo "  make run   - Run the bubble sort demo (com.acu.bubblesort.BubbleSort)"
	@echo "  make clean - Remove target/ and build artifacts"
	@echo "  make help  - Show this help message"
