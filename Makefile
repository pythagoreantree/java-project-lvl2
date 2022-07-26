ci:
	docker-compose -f docker-compose.yml run app make setup
	docker-compose -f docker-compose.yml up --abort-on-container-exit

compose-setup: compose-build compose-app-setup

compose-build:
	docker-compose build

compose-app-setup:
	docker-compose run --rm app make setup

compose-bash:
	docker-compose run --rm app bash

compose-lint:
	docker-compose run --rm app make lint

compose-test:
	docker-compose -f docker-compose.yml up --abort-on-container-exit

compose-down:
	docker-compose down -v --remove-orphans

setup:
	cd code && ./gradlew clean install
	gradle clean compileTest

test:
	gradle test

lint:
	gradle checkstyleTest checkCode

code-run:
	make -C code run

check-updates:
	gradle dependencyUpdates
