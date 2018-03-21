#ifndef SOCKET_H
#define SOCKET_H

#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <stdlib.h>
#include <netinet/in.h>
#include <netdb.h>
#include <arpa/inet.h>
#include <string.h>
#define PORT 3636

class sSocket {
	public:
		int sock;
		sSocket();
		~sSocket();
		
		void Send(char* str);
};
#endif
