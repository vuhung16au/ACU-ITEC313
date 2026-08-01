SUBDIRS := $(wildcard */.)

.PHONY: all build clean run

all: build

build:
	@for dir in $(SUBDIRS); do \
		if [ -f "$$dir/Makefile" ]; then \
			$(MAKE) -C "$$dir" build || exit 1; \
		fi \
	done

clean:
	@for dir in $(SUBDIRS); do \
		if [ -f "$$dir/Makefile" ]; then \
			$(MAKE) -C "$$dir" clean || true; \
		fi \
	done

run:
	@echo "Please specify a sub-project to run, running all is not supported."
