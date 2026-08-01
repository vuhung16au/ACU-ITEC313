.PHONY: build clean run

build:
	@echo "Building 01-01-JavaFX-HelloWorld"
	-cd 01-01-JavaFX-HelloWorld && mvn compile
	@echo "Building 01-01-JavaFX-HelloWorld-Gradle"
	-cd 01-01-JavaFX-HelloWorld-Gradle/hellofx && ./gradlew build
	@echo "Building 01-01-JavaFX-HelloWorld-ant"
	-cd 01-01-JavaFX-HelloWorld-ant && ant compile
	@echo "Building 01-01-JavaFX-HelloWorld-bazel"
	-cd 01-01-JavaFX-HelloWorld-bazel && bazel build //...
	@echo "Building 01-01-JavaFX-HelloWorld-javadoc"
	-cd 01-01-JavaFX-HelloWorld-javadoc && mvn compile
	@echo "Building 01-01-JavaFX-HelloWorld-sbt"
	-cd 01-01-JavaFX-HelloWorld-sbt && sbt compile

clean:
	@echo "Cleaning 01-01-JavaFX-HelloWorld"
	-cd 01-01-JavaFX-HelloWorld && mvn clean
	-rm -rf 01-01-JavaFX-HelloWorld/target
	@echo "Cleaning 01-01-JavaFX-HelloWorld-Gradle"
	-cd 01-01-JavaFX-HelloWorld-Gradle/hellofx && ./gradlew clean
	-rm -rf 01-01-JavaFX-HelloWorld-Gradle/hellofx/build 01-01-JavaFX-HelloWorld-Gradle/hellofx/.gradle
	@echo "Cleaning 01-01-JavaFX-HelloWorld-ant"
	-cd 01-01-JavaFX-HelloWorld-ant && ant clean
	-rm -rf 01-01-JavaFX-HelloWorld-ant/build 01-01-JavaFX-HelloWorld-ant/dist
	@echo "Cleaning 01-01-JavaFX-HelloWorld-bazel"
	-cd 01-01-JavaFX-HelloWorld-bazel && bazel clean --expunge
	-rm -rf 01-01-JavaFX-HelloWorld-bazel/bazel-*
	@echo "Cleaning 01-01-JavaFX-HelloWorld-javadoc"
	-cd 01-01-JavaFX-HelloWorld-javadoc && mvn clean
	-rm -rf 01-01-JavaFX-HelloWorld-javadoc/target
	@echo "Cleaning 01-01-JavaFX-HelloWorld-sbt"
	-cd 01-01-JavaFX-HelloWorld-sbt && sbt clean
	-rm -rf 01-01-JavaFX-HelloWorld-sbt/target 01-01-JavaFX-HelloWorld-sbt/project/target 01-01-JavaFX-HelloWorld-sbt/project/project 01-01-JavaFX-HelloWorld-sbt/hellofx/target

run:
	@echo "Running 01-01-JavaFX-HelloWorld"
	-cd 01-01-JavaFX-HelloWorld && mvn javafx:run
	@echo "Running 01-01-JavaFX-HelloWorld-Gradle"
	-cd 01-01-JavaFX-HelloWorld-Gradle/hellofx && ./gradlew run
	@echo "Running 01-01-JavaFX-HelloWorld-ant"
	-cd 01-01-JavaFX-HelloWorld-ant && ant run
	@echo "Running 01-01-JavaFX-HelloWorld-bazel"
	-cd 01-01-JavaFX-HelloWorld-bazel && bazel run //...
	@echo "Running 01-01-JavaFX-HelloWorld-javadoc"
	-cd 01-01-JavaFX-HelloWorld-javadoc && mvn javafx:run
	@echo "Running 01-01-JavaFX-HelloWorld-sbt"
	-cd 01-01-JavaFX-HelloWorld-sbt && sbt run
