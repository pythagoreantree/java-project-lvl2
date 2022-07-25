.DEFAULT_GOAL := build-run

clean:
	./gradlew clean

build:
	./gradlew clean build

install:
	./gradlew clean install

run-dist:
	./build/install/java-project-lvl2/bin/java-project-lvl2

run-dist-2:
	./build/install/java-project-lvl2/bin/java-project-lvl2 -h

run-dist-json:
	./build/install/java-project-lvl2/bin/java-project-lvl2 file1.json file2.json

run-dist-yml:
	./build/install/java-project-lvl2/bin/java-project-lvl2 file1.yml file2.yml

run-dist-stylish:
	./build/install/java-project-lvl2/bin/java-project-lvl2 -f stylish file1.json file2.json

run-dist-plain:
	./build/install/java-project-lvl2/bin/java-project-lvl2 -f plain file1.json file2.json

run-dist-json-out:
	./build/install/java-project-lvl2/bin/java-project-lvl2 -f json file1.json file2.json

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