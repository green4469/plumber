
build: clean
	./gradlew :plumber-cli:build

clean:
	./gradlew :plumber-cli:clean

format:
	./gradlew :plumber-cli:ktlintFormat
