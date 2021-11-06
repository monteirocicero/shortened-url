# shortened-url
A simple implementation of shortened url in Java, Spring Boot and Postgres.

## Use
After cloning the project go to the root of the project and run:

```bash
./gradlew bootRun
```

The gradle will downloaded the dependencies and start the application.

## API
To generate a short url we should use the following instructions.

Endpoint:

`POST /shortened-url/url`

Request:
```json
{
    "originalUrl":"http://my-long-url",
    "alias": "my-alias",
    "timeToExpiration": 1636213524
}
```

The fields alias and timeToExpiration are opcional.

Response:
201

```json
{
    "domainApp": "http://localhost:8080/shortened-url",
    "alias": "65066c97-d9e5-485f-b772-0f02e2a22171",
    "shortUrl": "http://localhost:8080/shortened-url/65066c97-d9e5-485f-b772-0f02e2a22171"
}
```
