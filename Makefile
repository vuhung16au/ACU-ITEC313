.PHONY: build clean run

build:
	$(MAKE) -C JavaFX build

clean:
	$(MAKE) -C JavaFX clean

run:
	$(MAKE) -C JavaFX run
