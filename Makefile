run-bot-cli: build
	java -jar plumber-bot/plumber-cli/build/libs/plumber-cli-0.0.1-SNAPSHOT.jar --spec-file=$(spec-file)

build-bot-cli: clean
	./gradlew :plumber-bot:plumber-cli:build

clean:
	./gradlew clean

format:
	./gradlew ktlintFormat

test:
	./gradlew test
