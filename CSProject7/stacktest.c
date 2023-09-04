/** short program to demonstrate use of stack
 *
 * @author Preet Patel
 */

#include <stdio.h>
#include <stdlib.h>
#include "stack.h"

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
  
  char *line = NULL; //pointer to hold line input
  size_t size = 0;
  int i =0;
  
  
  // Create a stack to hold our test data
  int max_cells = atoi(argv[1]);
  the_stack = create(max_cells);

  //loop that pushes string input into stack
  while  (getline(&line, &size, stdin) != -1 && (i < max_cells)){
    push(the_stack, line);
    line = NULL;//point line to nothing
    i++;
  }
  
  
  // loop that pops each string in the stack
  while (!isempty(the_stack)) {
    char *element = (char *) pop(the_stack);
    printf("%s", element);//print popped string to stdout
    free(element);//free memory no longer needed
  }
  

  return 0; // Indicate success
}

  
  
