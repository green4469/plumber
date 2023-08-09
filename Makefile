run-bot-cli: build-bot-cli
	java -jar plumber-bot/plumber-bot-cli/build/libs/plumber-bot-cli-0.0.1-SNAPSHOT.jar --spec-file=$(spec-file)

build-bot-cli: clean
	./gradlew :plumber-bot:plumber-bot-cli:build

clean:
	./gradlew clean

format:
	./gradlew ktlintFormat

test:
	./gradlew test
