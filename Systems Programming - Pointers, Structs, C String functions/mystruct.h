/** mystruct.h
 * @author Preet Patel
 * My own versions of some of employee pointer and random string functions
*/

#ifndef MYSTUCT_H   //Remeber the guard
#define MYSTRUCT_H
//function prototypes and any symbolic constants
#define ARRAY_SIZE (10)
#define STRING_LENGTH (6)
typedef struct {
    char id[ARRAY_SIZE];
    int birth_year;
    int start_year;
  } Employee;
Employee* make_employee(char* id, int birth_year, int start_year);
void get_id(Employee* c);
char* string_make(int length);
char random_gen();
Employee* struct_rand();
Employee**  array_struct(int structs);
void print_array_structs(Employee** arr, int size);
Employee** shallow_copy(Employee** arr, int num_of_employees);
Employee** deep_copy(Employee** arr, int num_of_employees);
void free_array_employees(Employee** arr, int num_of_employees);

#endif
