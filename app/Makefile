.DEFAULT_GOAL := build-run

clean:
	./gradlew clean

build:
	./gradlew clean build

install:
	./gradlew clean install

run-dist:
	./build/install/app/bin/app -h

run-dist-2:
	./build/install/app/bin/app -h

run-dist-json:
	./build/install/app/bin/app file1.json file2.json

run-dist-yml:
	./build/install/app/bin/app file1.yml file2.yml

run-dist-stylish:
	./build/install/app/bin/app -f stylish file1.json file2.json

run-dist-plain:
	./build/install/app/bin/app -f plain file1.json file2.json

run-dist-json-out:
	./build/install/app/bin/app -f json file1.json file2.json

run:
	./gradlew run

test:
	./gradlew test

report:
	./gradlew jacocoTestReport

lint:
	./gradlew checkstyleMain checkstyleTest

update-deps:
	./gradlew useLatestVersions

build-run: build run

.PHONY: build