To generate proto buf files you need their compiler. After proto bug compiler installed run :

protoc --cpp_out=. log-definition.proto 
After compile:

g++ -g -std=c++11 -pthread -Wall server.cpp log-definition.pb.cc -o server -lprotobuf
Run:
./server


Important log-definition.proto must be identical with the one in the java project.
