# Getting Started

### Config
docker-compose.yml is actually configured for first example run
Note: default topic name is exampleTopic

### Build jar
```
./gradlew clean build
```

### Build docker image
```
export IMAGE_NAME=kafka-example-listener:latest
docker build -t ${IMAGE_NAME} .
```

### Run all together
```
export IMAGE_NAME=kafka-example-listener:latest
docker-compose up
```

### Check

Run following command(you need kafka client installed)
```
kafka-console-producer --topic exampleTopic --broker-list localhost:9092
```
And then send next line to the exampleTopic
```
{"messages":[{"messageId":1111,"payload":"test1"},{"messageId":1215,"payload":"test2"},{"messageId":1316,"payload":"test3"}]}
```
