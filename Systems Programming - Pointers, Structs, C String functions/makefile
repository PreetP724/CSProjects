ctest: ctest.o mystring.o
	gcc ctest.o mystring.o -o ctest

ctest.o: ctest.c mystring.h
	gcc -c ctest.c

mystring.o: mystring.c mystring.h
	gcc -c mystring.c

stest: stest.o mystruct.o
	gcc stest.o mystruct.o -o stest

stest.o: stest.c mystruct.h
	gcc -c stest.c

mystruct.o: mystruct.c mystruct.h
	gcc -c mystruct.c

docs:
	doxygen
	cp -r -p /home/preetpatel/cs2303_hws/cs2303_hw03/html/. /home/preetpatel/public_html/cs2303_hw03/


clean: 
	rm -f mystring.o ctest.o ctest mystruct.o stest.o stest

