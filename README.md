## Seisma Coding Exercise
A coding exercise for Seisma

## Instructions



## Docker Quickstart
Run the web app with Docker by running the following command:
```
docker run -d -p 8080:8080 liamtrnr80/seisma-spring-boot-docker:latest
```
To change the external port the server uses, change the *first* instance of the port. For example, use port 8123:
```
docker run -d -p 8123:8080 liamtrnr80/seisma-spring-boot-docker:latest
```
**Building the Docker Image**
To build your own Docker Image, do the following:
1. Clone the repo
```
git clone https://github.com/liamtrnr80/CodingExercise.git
cd CodingExercise
```
2. Run the Docker build command:
```
docker build -t liamtrnr80/seisma-spring-boot-docker:build .
```
