all: compile
	./client

Office.o: Office.cpp
	g++ -I. -DICE_CPP11_MAPPING -c Office.cpp

Client.o: Client.cpp
	g++ -I. -DICE_CPP11_MAPPING -c Client.cpp

compile: Client.o Office.o
	g++ -o client Client.o Office.o -lIce++11 -pthread