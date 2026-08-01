.PHONY: build clean run

build:
	cd sample && mvn compile

clean:
	-cd sample && mvn clean
	-rm -rf sample/target

run:
	cd sample && mvn javafx:run
