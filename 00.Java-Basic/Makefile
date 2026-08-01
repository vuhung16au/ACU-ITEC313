# Root Makefile for Java Basic Projects
# This Makefile will run make commands in each project subdirectory

SUBDIRS := $(sort $(patsubst %/Makefile,%,$(wildcard [0-9][0-9].*/Makefile)))

.PHONY: all clean build run help

all: build

build:   ## Build all projects
	@for dir in $(SUBDIRS); do \
	  echo "\n===== Building project: $$dir ====="; \
	  $(MAKE) -C $$dir compile || exit 1; \
	done

run:     ## Run all projects
	@for dir in $(SUBDIRS); do \
	  echo "\n===== Running project: $$dir ====="; \
	  $(MAKE) -C $$dir run || exit 1; \
	done

clean:   ## Clean all projects
	@for dir in $(SUBDIRS); do \
	  echo "\n===== Cleaning project: $$dir ====="; \
	  $(MAKE) -C $$dir clean || exit 1; \
	done

help:    ## Show this help message
	@echo "Java Basic Projects - Root Makefile"
	@echo "Available targets:"
	@echo "  make build  - Build all projects (recursively)"
	@echo "  make run    - Run all projects (recursively)"
	@echo "  make clean  - Clean all projects (recursively)"
	@echo "  make help   - Show this help message"
 