#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>
#include "sort.h"

/**
   @author Preet Patel
   
   Program which takes an array of integers, prints it,
 * sorts it, prints it again, and reports how long the sorting took.
 * @param a Array of integers.
 * @param count Number of integers in the array.
 * @param either 0 or 1 to call sort_descending or alt_sort_descending respectively
 * @return void
 */


void timesort (int a[], int count, int function) {

  struct timeval start_time; // time stamps for start and end of sorting
  struct timeval end_time;
  struct timeval sort_time; // Elapsed time while sorting

  printf("Unsorted array:\n");
  print_int_array(a, count);

  gettimeofday(&start_time, NULL); // Get timestamp
  //checks for zero or 1 to decide what function to call
  if(function == 0){
  sort_descending(a, count);
  }
  else if(function == 1){
    alt_sort_descending(a, count);
  }
  gettimeofday(&end_time, NULL); // Get timestamp
  // prints sorted array
  printf("Sorted array (descending order):\n");
  print_int_array(a, count);
  //prints timestamps and time spent to sort
  printf("Timestamp before sorting: ");
  print_timeval(start_time); 
  printf("Timestamp after sorting: ");
  print_timeval(end_time);
  sort_time = timediff(start_time, end_time);
  printf("Time spent sorting: ");
  print_timeval(sort_time); 

  return; // nothing to return
  
}
