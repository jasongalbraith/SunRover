#include "MyLabel.h"

MyLabel::MyLabel(QWidget *parent) : QLabel(parent) {
	sock = new sSocket();
}
MyLabel::~MyLabel() {}

//when the system calls setImage, we'll set the label's pixmap
void MyLabel::setImage(QImage image) {
	QPixmap pixmap = QPixmap::fromImage(image);
	int w = this->width();
	int h = this->height();
	setPixmap(pixmap.scaled(w, h, Qt::KeepAspectRatio));
	w = 32;
	h = 28;
	char *output = new char[w * h];
	for (int i = 0; i < w; i ++) {
		for (int j = 0; j < h; j ++) {
			output[i * w + j] = image.pixel(i, j);
		}
	}
	sock->Send(output);
}