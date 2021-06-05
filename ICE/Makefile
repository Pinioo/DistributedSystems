GEN = ./gen/cpp
SRC = ./src/main/cpp
OUTPUT = ./out/cpp

all: compile
	$(OUTPUT)/client

$(OUTPUT)/Office.o: $(GEN)/Office.cpp
	g++ -I$(GEN) -DICE_CPP11_MAPPING -c $(GEN)/Office.cpp -o $(OUTPUT)/Office.o

$(OUTPUT)/Client.o: $(SRC)/Client.cpp
	g++ -I$(GEN) -DICE_CPP11_MAPPING -c $(SRC)/Client.cpp -o $(OUTPUT)/Client.o

compile: $(OUTPUT)/Client.o $(OUTPUT)/Office.o
	g++ -o $(OUTPUT)/client $(OUTPUT)/Client.o $(OUTPUT)/Office.o -lIce++11 -pthread