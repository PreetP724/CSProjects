/** mystruct.h
 * @author Preet Patel
   Different time functions and array functions.
*/

#ifndef SORT_H // Remember the guards
#define SORT_H

// Function prototypes:
void timesort (int a[], int count, int function);
void print_int_array(int nums[], int count);
void sort_descending(int nums[], int count);
void alt_sort_descending(int nums[], int count);
void print_timeval(struct timeval t);
struct timeval timediff(struct timeval time1, struct timeval time2);

#endif
