#include "sSocket.h"

sSocket::sSocket() {
	struct sockaddr_in address;
	sock = 0;
	struct sockaddr_in serv_addr;
	if ((sock = socket(AF_INET, SOCK_STREAM, 0)) < 0) {
		printf("\n Socket creation error \n");
		return;
	}
	memset(& serv_addr, '0', sizeof(serv_addr));
	serv_addr.sin_family = AF_INET;
	serv_addr.sin_port = htons(PORT);
	if(inet_pton(AF_INET,"172.30.87.106",&serv_addr.sin_addr)<=0){
	printf("\nConnection Failed \n");
	return;
	}
	if (connect(sock, (struct sockaddr*) &serv_addr, sizeof(serv_addr)) < 0) {
		printf("\nConnection Failed \n");
		return;
	}
	return;
}
sSocket::~sSocket(){}
void sSocket::Send(char* str) {
 	send(sock, str, strlen(str), 0);
}
