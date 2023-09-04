Name: Preet Patel

mystring.c: This program contains several C string functions that I created such as strlen, strcpy, strncpy, strcat, strncat, and strndup. Some of the code in the functions take inspiration from the man page descriptions of them. Includes mystring.h header file.

ctest.c: This program is a main function that demonstrates the C string functions I made by calling them and testing each one's output to make sure it is aligned with their respective actual C functions. No command line arguments needed here. Includes mystring.h header file. Frees any memory no longer needed as well.

mystruct.c: This program contains several functions construct employee structs, random employee structs, and an employee pointer to an array of employee pointers. Many of the functions are used within each other and build off each other. Includes mystruct.h header file. One command line argument.

stest.c: This program demomstrates the functions made in mystruct.c by calling them and printing out the employees made to ensure it everything is working correctly. Frees any memory no longer needed as well.  Includes mystruct.h header file.


How to generate and run each executable:

For ctest, put make ctest. Then do ./ctest. There are no command line arguments.

For stest, put make stest. Them do ./stest (insert a number for size of array of random employee pointers). For example, ./stest 5  
