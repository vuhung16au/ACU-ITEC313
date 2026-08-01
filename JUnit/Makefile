# Root Makefile for JUnit Projects
# Runs make targets in each project subdirectory that has a Makefile

SUBDIRS := $(sort $(patsubst %/Makefile,%,$(wildcard [0-9][0-9]-*/Makefile)))

.PHONY: all test build run clean help

all: test

test: ## Run tests in all projects
	@for dir in $(SUBDIRS); do \
	  echo "\n===== Testing project: $$dir ====="; \
	  $(MAKE) -C $$dir test || exit 1; \
	done

build: ## Build all projects
	@for dir in $(SUBDIRS); do \
	  echo "\n===== Building project: $$dir ====="; \
	  $(MAKE) -C $$dir build || exit 1; \
	done

run: ## Run all projects
	@for dir in $(SUBDIRS); do \
	  echo "\n===== Running project: $$dir ====="; \
	  $(MAKE) -C $$dir run || exit 1; \
	done

clean: ## Clean all projects
	@for dir in $(SUBDIRS); do \
	  echo "\n===== Cleaning project: $$dir ====="; \
	  $(MAKE) -C $$dir clean || exit 1; \
	done

help: ## Show this help message
	@echo "JUnit Projects - Root Makefile"
	@echo "Available targets:"
	@echo "  make test  - Run tests in all projects (recursively)"
	@echo "  make build - Build all projects (recursively)"
	@echo "  make run   - Run all projects (recursively)"
	@echo "  make clean - Clean all projects (recursively)"
	@echo "  make help  - Show this help message"
	@echo ""
	@echo "Projects:"
	@for dir in $(SUBDIRS); do echo "  - $$dir"; done
