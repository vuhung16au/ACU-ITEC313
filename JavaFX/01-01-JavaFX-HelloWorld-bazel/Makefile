.PHONY: build clean run

build:
	bazel build //...

clean:
	-bazel clean --expunge
	-rm -rf bazel-*

run:
	bazel run //...
