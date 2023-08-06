run-cli: build
	java -jar plumber-cli/build/libs/plumber-cli-0.0.1-SNAPSHOT.jar --spec-file=$(spec-file)

build-cli: clean
	./gradlew :plumber-cli:build

clean:
	./gradlew clean

format:
	./gradlew ktlintFormat

coverage: test
	./gradlew jacocoRootReport

test:
	./gradlew test
