/** stest.c
 * Program to demonstrate character arrays and dynamically-allocated memory.
 * @author Preet Patel
 * @main, demonstrates functions of employee pointers and random string generators.
 * @param, int argc, number of commands on command line
 * @param, const *char argv[], array of command on command line
 * @return, int indicating success or failure

 * @memory_management, frees any memory no longer needed and allocated from functions using malloc
 
 **/


#include "mystruct.h"
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main(int argc, const char* argv[]){
  srand(time(NULL));//generates seed
  char id[ARRAY_SIZE] = "Hello";
  
 
  Employee** arr_random_employees; //employee pointer to array of employee pointers
  Employee* new_employee =  make_employee(id, 2004, 2020); //makes random employee
  printf("ID of Employee1:\n"); //prints id
 get_id(new_employee);

 Employee* new_employee_two = make_employee("Preet", 200, 23000);// makes another random employee
 printf("ID of Employee2:\n");//prints id
 get_id(new_employee_two);

 Employee* new_employee_three = make_employee("Joe", 20330, 23000); //makes another random employee
 printf("ID of Employee3:\n"); //prints id
 get_id(new_employee_three);

 //frees memory no longer needed
 free(new_employee);
 free(new_employee_two);
 free(new_employee_three);

 //Creates random employees and prints them 
 printf("\n");
 printf("Prints a random employee:\n");
 Employee* random_employee = struct_rand();
 get_id(random_employee);
 printf("This employee's ID is %d\n", random_employee->birth_year);
 printf("This employee's ID is %d\n", random_employee->start_year);
 printf("\n");
 printf("Print a second random employee:\n");
 Employee* random_employee_two = struct_rand();
 get_id(random_employee_two);
 printf("This employee's ID is %d\n", random_employee_two->birth_year);
 printf("This employee's ID is %d\n", random_employee_two->start_year);

 //frees memory no longer needed
 free(random_employee);
 free(random_employee_two);

 //Creates employee pointer to array of employees and prints them
 printf("\n");
 printf("Create an array of random employees:\n ");
 arr_random_employees = array_struct(atoi(argv[1]));
 print_array_structs(arr_random_employees, atoi(argv[1]));
 printf("Address of  random employees: %p\n", arr_random_employees);

 //Creates shallow copy of arr_random_employees
 Employee** shall_copy = shallow_copy(arr_random_employees, atoi(argv[1]));
 printf("\n");
 printf("Shallow copy of the array:\n");
 print_array_structs(shall_copy, atoi(argv[1]));
printf("Address of shallow copy: %p\n", shall_copy);

 //Creates deep copy of arr_random_employees
 Employee** copy_deep = deep_copy(arr_random_employees,atoi(argv[1]));
 printf("\n");
 printf("Deep copy of the array:\n");
 print_array_structs(copy_deep, atoi(argv[1]));
 printf("Address of deep  employees: %p\n", copy_deep);

 //frees memory no longer needed
 free_array_employees(arr_random_employees, atoi(argv[1]));
 free(shall_copy);
 free_array_employees(copy_deep, atoi(argv[1]));

 return 0; //Indicate success
 
 
  
}
  
  
  

  
    
