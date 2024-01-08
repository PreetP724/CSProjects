// string.h covers the C-style string functions.
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "mystring.h"

/** ctest.c
 * Program to demonstrate character arrays and dynamically-allocated memory.
 * @author Preet Patel

 * @main, demonstrates self-made string functions
 * @return, int indicating success or failure

 * @memory_management frees all memory no longer needed allocated from malloc
 */

const int MAX_CHARS = 20;       // Maximum number of characters in array

int main()
{
  char a1[MAX_CHARS + 1]; 	// Character array--initially empty
  char a2[] = "Hello"; 		// Character array--unsized but initialized
  char a3[MAX_CHARS + 1]; 	// Character array--we will underfill it
  const char *p1 = "Hello";     // Pointer to constant string
  char *p2;           		// Will be a pointer to dynamically-allocated string
  int copy_limit;               // Maximum characters to copy.



  /* Print the pointers.
     Note: this example prints
     the values of the pointers themselves, not the targets.
  */
  printf("Arrays:   a1: %p, a2: %p, a3: %p\n", a1, a2, a3);
  printf("Pointers: p1: %p, p2: %p\n", p1, p2);

 mystrcpy(a1, "Hi"); 		// Initialize character array a1 with some text
 mystrcpy(a3, "Hello, also");    // Initialize uinderfilled character array

 printf("Arrays:   a1: %p, a2: %p, a3: %p\n", a1, a2, a3);
  // Print the values of the C-style strings
  printf("\n");
  printf("C-string values:\n");
  printf("a1: %s\n", a1);
  printf("a2: %s\n", a2);
  printf("a3: %s\n", a3);

  /* Concatenate two character arrays, then print.
  *  Terminator character in the last element of the array as well as after "Hi"
  */
  a1[MAX_CHARS] = '\0';		
  mystrcat(a1, a2);
  printf("\n");
  printf("After concatenating a2 to the end of a1\n");
  printf("a1: %s\n", a1);

  // Concatenate two character arrays safely, then print.
  copy_limit = MAX_CHARS - mystrlen(a1); 	// How much space is left?
  
  printf("\n");
  printf("Concatenating a2 to a1, with copy_limit = %d\n", copy_limit);
  if (copy_limit > 0) {
	mystrncat(a1, a2, copy_limit);
  }
  printf("a1: %s\n", a1);

  // Concatenate two character arrays safely, then print.
  copy_limit = MAX_CHARS - mystrlen(a1); 	// How much space is left?
  printf("\n");
  printf("Concatenating a3 to a1, with copy_limit = %d\n", copy_limit);
  if (copy_limit > 0) {
	mystrncat(a1, a3, copy_limit);
  }
  printf("a1: %s\n", a1);

  // Duplicate a string, using my function, then print
  printf("\n");
  printf("Before dup, array a2 = %p, contents = %s\n", a2, a2);
  p2 = mystrdup(a2);
  printf("After dup, pointer p2 =  %p, contents = %s\n", p2, p2);
  

  char test_string[20]; //Intiall empty char array

  //Verifies strncpy function with all boundary cases specified in printf statements
  printf("\n");
  printf("Verifying strncpy:\n");
  
  mystrcpy(test_string, "ABCDEFGHIJ"); //fills first 10 elements
  printf("String a = %s\n", test_string);
  
  mystrncpy(test_string, "TUVWXYZ", 25);
   printf("String a = %s,  while src length is less than n\n", test_string);
   
   mystrcpy(test_string, "ABCDEFGHIJ");
    mystrncpy(test_string, "TUVWXYZ", 5);
   printf("String a = %s,  while src length is more than n\n", test_string);
   
    mystrcpy(test_string, "ABCDEFGHIJ");
    mystrncpy(test_string, "TUVWXYZ", 7);
   printf("String a = %s,  while src length is equal to  n\n", test_string);
   
   
   //Verifies strcat function with boundary cases in printf statements
   printf("\n");
   printf("Verfiying strcat:\n");
   
   mystrcpy(test_string, "ABCDEFGHIJ");
   mystrcat(test_string, "Hello");
   printf("String a = %s, buffer is big enough\n", test_string);
   
    mystrcat(test_string, "P");
   printf("String a = %s, buffer is  big enough\n", test_string);


   //Verifies strncat function with boundary cases specified in printf statements
   printf("\n");
   printf("Verifying strncat:\n");
   
   mystrcpy(test_string, "ABCDEFGHIJ");
   mystrncat(test_string, "Hello", 4);
   printf("String a = %s, src length is larger than n\n", test_string);
   
   mystrcpy(test_string, "ABCDEFGHIJ");
   mystrncat(test_string, "Hello", 5);
 printf("String a = %s, src length is equal n\n", test_string);
 
   mystrcpy(test_string, "ABCDEFGHIJ");
   mystrncat(test_string, "Hello", 7);
printf("String a = %s, src length is smaller than  n\n", test_string);
 
   mystrcpy(test_string, "ABCDEFGHIJ");
  mystrncat(test_string, "Hellonnnnnnnnnnnnnnnn", 12);
printf("String a = %s, buffer is not large enough\n", test_string);
 
 mystrcpy(test_string, "ABCDEFGHIJ");

 //Verfies strndup with boundary cases specified in printf statments
 printf("\n");
 printf("Verifying strndup:\n");
    char* duplicate =  mystrndup(a2, 5);
   printf("Duplicate = %s, n is equal to length of src\n ", duplicate);

   char* duplicate_two = mystrndup(a2, 3);
   printf("Duplicate = %s, n is smaller than length of src\n", duplicate_two);

    char* duplicate_three = mystrndup(a2, 7);
    printf("Duplicate = %s, n is larger than length of src\n", duplicate_three);

  // Free the memory allocated on our behalf by mystrdup()
  free(p2);
  free(duplicate);
  free(duplicate_two);
  

  return 0; //Indicates success
}
