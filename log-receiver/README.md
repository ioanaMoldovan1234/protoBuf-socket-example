#Protoc file file generation: This requires protoc compiler installed
protoc --java_out=. log-definition.proto 

Copy generated .java classes in com.logger package. This operation must be done every time proto file is updated

#Important log-definition.proto should present in the cpp app also.

#Run
java -jar log-receiver-0.0.1-SNAPSHOT.jar

Usage:
make a post call at `localhost:8080/event`

Request body


`{
"timestamp":1518609008,
"userId":1,
"event":"downtime"
}`
