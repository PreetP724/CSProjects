#include <stdio.h>
#include <stdlib.h>
#include "sort.h"

/* Program which reads numbers from the command line,
 * fills an array with random integers, sorts them in descending order, and outputs the sorted list.
 *
 * Usage:
 * The second number is either a 0 or 1 to call sort_descending or alt_sortdescending respectively.
 *         ./sorttest2 some_number another_number
 * Example:
 *         ./sorttest2 100 1
 *
 */

/**
   @author Preet Patel
   Main program
 * @param argc Number of words on the command line
 * @param argv[] Array of character strings, the words from the command line.
 * @return 0 if success, 1 if error (wrong number of arguments)
 */

int main (int argc, const char* argv[]) {

  int *nums; // space to store numbers entered from the command line.

  int next_num; // Integer read from the command line goes here.
  int i; // array index 
  int num_nums; // How many numbers actually entered on the command line.
  int function;
  // Check that there are at least three entries on command line
  num_nums = argc;
  if (num_nums < 3) { 
    printf("Must enter one number and a 0 for sort_descending or 1 for alt_sort_descending to be called. Exiting.\n");
    return 1; // Indicate failure
  }
  //checks if third entry is not 0 or 1
  if((atoi(argv[2]) != 0) && (atoi(argv[2]) != 1)){
    printf("Second argument on command line must be a 0 for sort_descending or 1 for alt_sort_descending to be called. Exiting. \n");
    return 1;// Indicate failure
  }
  
  num_nums = atoi(argv[1]);//stores first entry on command line for number of random ints to sort
  function = atoi(argv[2]);// stores second entry on command line for what function to call
  // Allocate an array big enough to hold the numbers
  nums = (int*) calloc(num_nums, sizeof(int));
  if (nums == NULL) {
    fprintf(stderr, "Unable to allocate enougn memory to hold the numbers.\n");
  }

  // Fill nums with random integers
  for (i = 0; i < num_nums; i++) {
    next_num = rand(); // Get random integer
    nums[i] = next_num; // store into the array
  }

  // Now print, sort, and print the array, and time how long the sorting took.
  timesort(nums, num_nums, function);

  return 0; // Indicate success!
}
