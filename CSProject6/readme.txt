Name: Preet Patel

sort.c: This program contains several different functions on getting timestamps, evaluating time differences, sorting arrays, and printing arrays. This contains a header file sort.h.

timesort.c: This program has a timesort function that takes an array of integers, prints it, sorts it, prints it again, and reports how long the sorting took. This includes header file sort.h.

sorttest.c: This program is a main function that takes a set of numbers from the command line, fills them in a array and calls the timesort function.

sorttest2.c This program is a main function that takes a number from the command line and creates an array of random integers of that size. Then it calls the timesort function with either 0 or 1(second command inputted in the command line) as the third parameter. 0 calls the sort_descending function and 1 calls the alt_sort_descending.

How to build executables:

For sorttest,

./sorttest (a number) (another number) etc..

For sorttest2,

./sorttest2 (a number for size of array) (a number 0 or 1)


Test Data:

0 = sort_descending
1= alt_sort_descending

./sorttest2 10 0:

1 second spent

./sorttest2 10 1:

1 second spent

./sorttest 100 0:


52 seconds

./sorttest2 100 1:

46 seconds

./sorttest2 200 0:

215 seconds

./sorttest 200 1:

166 seconds


Conclusion:

Incrementing using pointers is faster than array indexing but not by a significant amount. The factor of how much faster it is, is inconsistent.

