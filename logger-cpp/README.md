To generate proto buf files you need their compiler, install from https://github.com/google/protobuf/releases. After protbuf compiler installed generate files:

protoc --cpp_out=. log-definition.proto 

Compile:

g++ -g -std=c++11 -pthread -Wall server.cpp log-definition.pb.cc -o server -lprotobuf

Run:
./server


Important log-definition.proto must be identical with the one in the java project.
