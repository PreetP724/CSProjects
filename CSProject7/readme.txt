Name: Preet Patel


stacktest.c: This is a program that takes strings and prints them back in reverse order by using stack structure. It takes one command line argument which is the size of the stack. Includes header file stack.h

stacktest2.c: This is a program that takes a string or multiple strings and print the string characters back in reverse order. It takes one command line argument which is the number of strings you want to reverse characters of. Includes header file stack.h

stack.c: This programs holds several stack functions such as create, destroy, pop, push, etc. Includes header file stack.h

How to build and run:

make all will build all executables.

To run stacktest,

./stacktest (a number representing size of stack/how many strings you want to put into stack)

Then type strings into stdin and control-d when done.

Example: 
./stacktest 5

Builds a stack with 5 elements


To run stacktest2,

./stacktest2 (a number representing how many strings you want to input and reverse the characters of)

Then type strings into stdin and control-d when done.

For example:

./stacktest 5

Allows you to input 5 strings


To run them both together:

./stacktest (number) | ./stacktest2 (number)

This will print back the strings you input in reverse order and with charcaters reversed.

Example:

./stacktest 5 | ./stacktest2 5


To run with my data text file,

cat input.txt | ./stacktest (number of strings you want to input from text file already made) | ./stacktest2 (number of strings you want to input from text file already made)

Example:

cat input.txt | ./stacktest 5 | ./stacktest2 5
