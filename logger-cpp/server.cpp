#include <stdio.h>
#include <stdlib.h>
#include <strings.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <sys/wait.h>
#include <signal.h>
#include <string>
#include <iostream>

#include "./log-definition.pb.h"

#define PORT 8000           // the port users will be connecting to
#define MAXDATASIZE 4096    // max number of bytes we can send at once
#define BACKLOG 10          // how many pending connections queue will hold

using std::cout;
using std::endl;
using std::string;

void sigchld_handler(int s) {
    while (waitpid(-1, NULL, WNOHANG) > 0);
}

/* simple little function to write an error string and exit */
static void err(const char* s) {
    perror(s);
    exit(EXIT_FAILURE);
}

int main(int argc, char** argv) {

    cout << "Here...";

    int listenfd;
    int connectfd;
    int numbytes;
    char buf[MAXDATASIZE];
    struct sockaddr_in server;
    struct sockaddr_in client;
    socklen_t sin_size;
    struct sigaction sa;

    if ((listenfd = socket(AF_INET, SOCK_STREAM, 0)) == -1) {
        err("socket");
    }

    int opt = SO_REUSEADDR;
    if (setsockopt(listenfd, SOL_SOCKET, SO_REUSEADDR, &opt, sizeof(opt)) == -1) {
        err("setsockopt");
    }

    bzero(&server, sizeof(server));
    server.sin_family = AF_INET;                // host byte order
    server.sin_port = htons(PORT);              // short, network byte order
    server.sin_addr.s_addr = htonl(INADDR_ANY); // automatically fill with my IP

    if (bind(listenfd, (struct sockaddr *)&server, sizeof(struct sockaddr)) == -1) {
        err("bind");
    }

    if (listen(listenfd, BACKLOG) == -1) {
        err("listen");
    }

    sa.sa_handler = sigchld_handler;  // reap all dead processes
    sigemptyset(&sa.sa_mask);
    sa.sa_flags = SA_RESTART;
    if (sigaction(SIGCHLD, &sa, NULL) == -1) {
        err("sigaction");
    }


    while (1) {     // main accept() loop
        sin_size = sizeof(struct sockaddr_in);

        connectfd = accept(listenfd, (struct sockaddr *)&client, &sin_size);

        numbytes = recv(connectfd, buf, MAXDATASIZE, 0);
        buf[numbytes] = '\0';
        string a = buf;
        cout << "You got a message from " << inet_ntoa(client.sin_addr) << endl;
        // cout << "Client Message: " << a << endl;

        
        logger::Log log;
        log.ParseFromString(a);
        cout << "Timestamp:\t"    << log.timestamp() << endl;
        cout << "ID:\t"      << log.userid() << endl;
        cout << "Event:\t"   << log.event() << endl;

        close(connectfd);
    }

    close(listenfd);

    return 0;
}
