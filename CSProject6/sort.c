#include <stdio.h>
#include <sys/time.h>
#include "sort.h"
/**
   @author Preet Patel
   
**/   

/** Prints an array of integers, one per line.
 * @param nums Array to print
 * @param count Number of elements to print
 */

void print_int_array(int nums[], int count) {
  int i; // Loop counter
  for (i = 0; i < count; i++) {
    printf("%d\n", nums[i]); // Print each array element in turn
  }
}

/** Sorts an array of integers into descending order.
 * Uses the bubble sort algorithm.
 *
 * @param nums Array to print
 * @param count Number of elements to print
 */

void sort_descending(int nums[], int count) {
  int round;     // How many times left to go through the outer loop.
  int i;         // Loop counter for the inner loop
  int inorder;   // Used as a boolean (logical). 1 = array is in correct order.
  int temp;      // Temporary variable used while swapping array elements

  inorder = 0;   // Assume not sorted at the beginning

  // Repeat outer loop, one time less than the size of the array.
  // Terminate early if array is in correct order.
  for(round = count - 1; (round > 0) && (!inorder); round--) {
    inorder = 1;  // Assume in correct order, until found otherwise.

    // Repeat inner loop, testing array elements 0 through round
    // Compare two adjacent elements of the array and swap if wrong order
    for (i = 0; i < round; i++) {
      if (nums[i] < nums[i+1]) {
	inorder = 0;      // At least one pair had to be swapped
	temp = nums[i];
	nums[i] = nums[i+1];
	nums[i+1] = temp;
      }
    }
  }
}


/** Sorts an array of integers into descending order.
 * Uses the bubble sort algorithm and pointers.
 *
 * @param nums Array to print
 * @param count Number of elements to print
 */
void alt_sort_descending(int nums[], int count) {
  int round;     // How many times left to go through the outer loop.
  int i;         // Loop counter for the inner loop
  int inorder;   // Used as a boolean (logical). 1 = array is in correct order.
  int temp;      // Temporary variable used while swapping array elements
  int* rand_ints; // pointer to int 
 
  int* rand_ints_plus; //pointer to int
  
  inorder = 0;   // Assume not sorted at the beginning

  // Repeat outer loop, one time less than the size of the array.
  // Terminate early if array is in correct order.
  for(round = count - 1; (round > 0) && (!inorder); round--) {
    inorder = 1;  // Assume in correct order, until found otherwise.
  
    
    //sets pointer to point at beginning of nums array
    rand_ints = nums;
    //sets pointer to point at second index of nums array
    rand_ints_plus = nums+1;
    
    // Repeat inner loop, testing array elements 0 through round
    // Compare two adjacent elements of the array and swap if wrong order
    for (i = 0; i < round; i++){
      if (*rand_ints < *rand_ints_plus) {
	inorder = 0;      // At least one pair had to be swapped
	temp = *rand_ints;
	*rand_ints = *rand_ints_plus ;
	*rand_ints_plus = temp;
	
      }
      rand_ints++; //increments rand_ints to point to next index
      rand_ints_plus++;// increments rand_ints_plus to point to next index
    }
    
  

  }

 
  
}

/** Prints the contents of a timeval struct.
 * @param t Struct to print.
 */

void print_timeval(struct timeval t) {
  //prints each field of the timeval param
    printf("Seconds: %ld, Microseconds: %ld\n", t.tv_sec, t.tv_usec);
}

/** Calculates difference between two timestamps. 
 * Note that this takes structs as parameters and returns a struct;
 * it does not use pointers.
 * @param time1 A time stamp
 * @param time2 another timestamp (assumed same as or later than time 1)
 * @return A timeval struct representing time2 - time1
 */

struct timeval timediff(struct timeval time1, struct timeval time2) {
  struct timeval diff; // defines a timeval struct  
  //set both fields to zero
  diff.tv_sec = 0L; 
  diff.tv_usec = 0L;

  //set fields equal to difference of respective fields from time2 and time1
  diff.tv_sec = time2.tv_sec - time1.tv_sec;
  diff.tv_usec = time2.tv_usec - time1.tv_usec;
  //checks if difference in microseconds is negative
  if(diff.tv_usec < 0){
    diff.tv_sec--; //decreases the second difference by 1
    diff.tv_usec = diff.tv_usec +  1000000;//adds a million to microsecond difference
  }

  return diff; //returns timeval struct representing time difference
 
}

