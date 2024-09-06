# Something here

## Start in Docker

```shell
$ docker compose up -d
$ docker compose up --build algobro
```

### Rebuild / Restart

```shell
$ docker compose up --build algobro
```

## Run Locally

```shell
$ mvn clean package shade:shade
$ java -javaagent:./opentelemetry-javaagent.jar -Dotel.resource.attributes=service.name=algobro -Dotel.traces.exporter=otlp -Dotel.otlp.endpoint=http://localhost:4317 -Dlog4j2.configurationFile=./log4j2.xml -jar target/algobro-1.0-SNAPSHOT.jar
```
