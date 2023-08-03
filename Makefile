run: build
	java -jar plumber-cli/build/libs/plumber-cli-0.0.1-SNAPSHOT.jar --spec-file=$(spec-file)

build: clean
	./gradlew :plumber-cli:build

clean:
	./gradlew :plumber-cli:clean

format:
	./gradlew :plumber-cli:ktlintFormat
