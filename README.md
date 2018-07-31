In this repository are 2 applications:
 - first one is a spring boot app that expose an enpoint at locahost:8080/event POST and sent this event by socket the second app
 - second app that is written in c++ is a server socket that receive this log event and print it.

This apps comunicate by using Google Proto Buffers.
