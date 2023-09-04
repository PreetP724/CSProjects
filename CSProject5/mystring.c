/** mystring.c
 * @author Preet Patel
 * Custom versions of some of the C-style string functions
*/

/**
 @mystrdup,  Duplicates a C-style string.
 @param src Pointer to string to be copied
 @return Pointer to freshly-allocated string containing a duplicate of src
         or null if no memory is available

 @mystrlen, finds length of string not including terminator
 @param, const char* s, constant char pointer
 @return size_t, bytes of string

 @mystrcpy, copies string to another char pointer
 @param char *restrict dest, pointer to char destination
 @param const char *src, pointer with string to be copied
 @return char pointer to copy

 @mystrncpy, copies string up to n bytes to another char pointer
 @param char *restrict dest, pointer to char destination
 @param const char *src, pointer with string to be copied
 @param size_t n, number of bytes to be copied
 @return char pointer to copy

 @mystrcat, appends string to another char pointer
 @param char *restrict dest, pointer to char destination
 @param const char *src, pointer with string to be appended
 @return char pointer to new string

 @mystrncat, appends string up to n bytes to another char pointer
 @param char *restrict dest, pointer to char destination
 @param const char *src, pointer with string to be appended
 @param size_t n, number of bytes to be appended
 @return char pointer to new string duplicate, or null is there is no memory

 @mystrndup, duplicates a  string up to n bytes to another char pointer
 @param const char *src, pointer with string to be duplicated
 @param size_t n, number of bytes to be duplicated
 @return char pointer
*/

#include <string.h>
#include <stdlib.h>
// Have to #include stdlib.h because it is needed to use malloc()
#include "mystring.h"

// returns bytes of string not including terminator
size_t mystrlen(const char* s){
  size_t length; // variable to hold length
  length = 0; // make length equal to zero
  while(*s != '\0'){ // loop adds 1 to length until terminator is reached
    length++;
    s++;
  }
  return length; // return the value of length 
}

//copies a string to another char pointer
char *mystrcpy(char *restrict dest, const char *src){

  int i = 0; // creates a counter variable
  while(src[i] != '\0'){ //loop goes through src and adds every element to dest pointer until src hits null terminator
   dest[i] = src[i];
    i++;
  }
  
  dest[i] = '\0'; // add null terminator to dest at the end

  return dest; // return pointer to new copied string
}

//copies a string to another char pointer up to n bytes
char *mystrncpy(char *restrict dest, const char *restrict src, size_t n){

  size_t i; //creates a counter variable

  for(i = 0; i < n && src[i] != '\0'; i++){ //loop adds up to n bytes of  elements of src or until src hits null terminator to dest 
    dest[i] = src[i];
  }
  while(i < n){ // loop pads null bytes to dest if counter is still less than bytes needed to be added
    dest[i] = '\0';
    i++;
  }
  return dest; // return pointer to new copied string
 
}

//appends string to another char pointer
char *mystrcat(char *restrict dest, const char *restrict src){

  size_t i = 0; //creates counter variable
  size_t dest_length = strlen(dest);//stores length of dest
  
  while(src[i] != '\0'){// loop adds elements of src to end of dest, starting from dests null poiner, until src hits null pointer
   dest[i+ dest_length] = src[i];
   i++;
  }
  dest[i + dest_length] = '\0'; // add null pointer to end of dest
  return dest; // return pointer to new appended string
}
  
// appends string to another char pointer up to n bytes
char *mystrncat(char *restrict dest, const char *restrict src, size_t n){
  

  size_t dest_length = strlen(dest); //stores length of dest 
  int i = 0;// stores counter variable
  while(i < n){ // loop that appends up to n bytes  or until src hits nul terminator
    if(*src != '\0'){ //checks for null terminator
	 dest[i + dest_length] = src[i];
       i++;
     }
       else{
	 break; //exits loop
       }
	   
   }
  dest[i + dest_length] = '\0'; //null terminates dest pointer
   
  return dest;// returns dest pointer to new string
}



char* mystrdup(const char* src) {
  int length; 	// Length of the source string
  char* newstr; // Pointer to memory which will hold new string

  length = strlen(src) + 1;  		// Leave space for the terminator
  newstr = (char*) malloc(length); 	// Must cast!

  // If no memory was available, return null pointer immediately
  if (newstr == 0) {
	return (char *) 0;
  }

  // Otherwise, copy the string and return pointer to new string
  strcpy(newstr, src);
  return newstr;
}

char* mystrndup(const char* src, size_t n){
  char* dup;//Pointer to memory which will hold new strong
  int length;//length of src
  int i = 0;// counter variable

  length = strlen(src) +1;  //gets length of src plus null terminator
  dup = (char*) malloc(length); //Allocates dup pointer

  if(dup == 0){ //checks if no memory was available and returns null pointer if this is the case
   return (char *) 0;
 }

  while(i<n){ // loop that adds up to n bytes to dest or until src hits null terminator
   if(src[i] != '\0'){
    dup[i] = src[i];
    i++;
   }
   else{
     break; //exits loop
  }
 }
  
  dup[i] = '\0'; // null terminates pointer

  return dup; //returns pointer to new string
 

 
}





