/** short program to demonstrate use of stack
 *
 * @author Preet Patel
 */

#include <stdio.h>
#include <stdlib.h>
#include "stack.h"
#include <string.h>

/** @main function to demonstrate use of stack using user input from stdin
    @param int argc, number of commands on command line
    @parm char* argv[], array of commands inputted on command line
    @return 0 or 1, indicating success or failure
    
 *
 */
int main (int argc, char *argv[]) {

   // check for user input on command line
  if(argc < 2){
    printf("Must enter a size for stack on the command line!");
    return 1;//Indicate failure
  }
  
  Stack *the_stack; // pointer to a stack 
 
  char *line = NULL; // null pointer to point to input
  size_t size = 0;
  int i =0;
  char *string; // pointer to point to input
  int k = 0;
  
  
  // outer loop to take strings from user 
  while  (getline(&line, &size, stdin) != -1 && (i < atoi(argv[1]))){
    string = line;//point to address of line
    int max_cells = strlen(line);// max elements allowed in stack
    the_stack = create(max_cells); // Create a stack to hold our test data
    //pushes each character of string into stack
    while(k < strlen(line)-1){
      push(the_stack, string);
      *string++;
      k++;
    }      


    char* reverse; // pointer to store reversed string
    reverse = (char*) malloc(strlen(string));//allocate sufficient memory
    char* newptr = reverse; //point to address of reverse
    //loop to pop each element in stack
    while (!isempty(the_stack)) {
      char *element = (char *) pop(the_stack);
      *reverse =  *element; //store popped element in reverse pointer
      *reverse++;
    }
    // print reverse string to stdout
    printf("%s\n", newptr);
    //free any memory no longer needed
    destroy(the_stack);
         free(line);
     free(newptr);
    line = NULL;
    i++;
    k = 0; // set k back to zero
  }
  
  return 0;// indicate success
}

  
  
