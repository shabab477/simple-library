# Simple Library

[![Java CI with Gradle](https://github.com/shabab477/simple-library/actions/workflows/gradle.yml/badge.svg)](https://github.com/shabab477/simple-library/actions/workflows/gradle.yml)


### Tasks checklist:

- :heavy_check_mark: Use migration tool for DB migration on app startup.  **Note: Used Flyway**
- :heavy_check_mark: Use localization for exception messages with support for English(default) and Japanese. ***Note: Present in config/LocalizationConfig.java. Japanese resources prefixed with *JA* to signify Japanese. Use ***Accept-Language*** header as ja to change language***
- :heavy_check_mark: A Book will only have a BookMeta(1-1).
- :heavy_check_mark: Send an email notification to user when a book is issued(Mock the email sending part). ***Note: Mocked in a Kafka consumer***
- :heavy_check_mark: In API requests/responses send dates as DD-MM-YYYY and save as YYYY-MM-DD format in the database. Request/Response body will be in snake_case and in Java side use camelCase. ***Note: Config in application.properties***
- :heavy_check_mark: Handle common exceptions(400, 404) cases using global configuration. ***Note: Config in config/GlobalExceptionHandlerConfig.java***
- :heavy_check_mark: Configure Tomcat to have 300 threads simultaneously.  ***Note: Config in application.properties***
- :heavy_check_mark: Dockerize the application.
- :heavy_check_mark: Use Github actions to build the project and run tests(don’t need extensive test coverage, just writing a few for this purpose is okay).

### Extra:
- Created docker-compose file for ease of deployment

### Back-end Design decisions

- API-s are designed based on resources.
- Preffering *Pessimistic Locking* over *Optimistic Locking* when updating resources.
- Book borrow and submit are intentionally kept accessible by any authenticated user, even though the spec says only USER role can access GET requests.

### Proposed Improvements

- Add Swagger documentation
- Redis cache
- Add load tests and concurrent update checks
- At this moment only bean validation uses localization messages. 404, 500 etc errors don't show localized messages

### How to run
- clone repo
- `./gradlew clean build`
- docker build -t java-app .
- docker-compose up

