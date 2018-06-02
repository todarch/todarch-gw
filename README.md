# Todarch Gateway Service

It is the gateway service for Todarch application.

## Maven

```shell
mvn clean package

mvn clean package -DskipTests=true
```

## Docker

- Build your own image

```shell
docker image build -t todarch/gw:latest -f dockerfiles/Dockerfile .
```

- Or use [the image](https://hub.docker.com/r/todarch/gw/) from public repository (when master branch is updated, it is automatically rebuilt on Docker Cloud)

```shell
docker container run -it --rm --name todarch-gw -p 8082:8080 todarch/gw
```

- reach [health check endpoint](http://localhost:8082/non-secured/up) at local

## Heroku

- master branch is automatically deployed [as Heroku app](https://todarch-gw.herokuapp.com/non-secured/up) after each successful Jenkins build.

