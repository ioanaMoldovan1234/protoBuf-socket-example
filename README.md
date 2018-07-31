In this repository are 2 applications:
 - first one is a spring boot app that expose an enpoint at locahost:8080/event POST and sent this event by socket the second app. This app acts as server over http, but like a client for c++ app. 
 - second app that is written in c++ is a server socket that receive this log event and prints it.

This apps comunicate by using Google Proto Buffers.

Run steps:
- install protobuf compiler
- generate proto files for java and c++
- move generated java classes 
- compile apps
- run apps
