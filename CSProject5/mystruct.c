/** mystruct.c
    @author Preet Patel
    Functions with struct pointers and arrays of struct pointers.

    @make_employee, makes an employee with inputted data
    @param char *id, id of employee
    @param int birth_year, holds birth year of employee
    @param int start_year, holds start year of employee
    @return employee pointer to newly created employee

    @get_id, prints id of an employee
    @param Employee* c, an employee struct
    @returns void,  print statement to console of id of inputted employeee

    @random_gen, generates a random character in range (A-Z, a-z, 0-9)
    @return random character generated

    @string_make, makes a string of random characters of specified length
    @param int length, length of string to be made, set with symbolic constant
    @return char pointer to random string made

    @struct_rand, makes employee with random data
    @return Employee pointer to newly made random employee

    @array_struct, creates an array of random struct pointers
    @param int employees, number of employees to be put in array
    @return employee pointer that points to an array of employee pointers

    @print_array_structs, prints employees in array
    @param Employee** arr, takes an employee pointer pointing to an array of employee pointers
    @param int size, number of employee pointers in array
    @return prints employees to console

    @shallow_copy, creates a shallow copy
    @param Employee** arr, takes an employee pointer pointing to an array of employee pointers
    @param int num_of_employees, number of employee pointers in array
    @return new shallow copy

    @deep_copy, creates a deep copy
    @param Employee** arr, takes an employee pointer pointing to an array of employee pointers
    @param int num_of_employees, number of employee pointers in array
    @return new deep copy 

    @free_array_employees, frees memory allocated to array of employee pointers
    @param Employee** arr, takes an employee pointer pointing to an array of employee pointers
    @param int num_of_employees, number of employee pointers in array
    @return void

    
**/

#include "mystruct.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

Employee* make_employee(char* id, int birth_year, int start_year){
  Employee* new; //creates a pointer to an employee
  
  new = (Employee*) malloc(ARRAY_SIZE + sizeof(int) + sizeof(int)); //allocates sufficient memory to employee
  strcpy(new->id, id); //copies id param to employee field
  new->birth_year = birth_year; //sets employee field from param input
  new->start_year = start_year; //sets employee field from param input
  return new; //returns pointer to employee
}

void get_id(Employee* c){
  printf("This employee's ID is %s\n", c->id); // prints employee id
}

char random_gen(){
  int ascii_value = rand() % 62; //gets random integer value from 0-61
  if(ascii_value <= 9){ //checks if value is from 0-9 range
    return '0'+ ascii_value ;
  }
  else if (ascii_value <= 35){//checks if value is in A-Z range
    return 'A'+ (35-ascii_value);
  }
  else if(ascii_value <=61){//checks if value is in a-z range
    return 'a'+ (61-ascii_value) ;
  }
}

char* string_make(int length){
  char* new_string; //holds pointer to string
  char* string_ptr; //hold pointer to string
  int i = 0; //counter variable
  new_string = (char*) malloc(length + 1); //allocates memory to pointer for length plus null terminator
  string_ptr = new_string;//sets pointer equal to other pointer to save memory address
  while(i < length){//loop to add rando characters to char pointer until hits length requirement
    *new_string = random_gen();
    new_string++;
    i++;
  }
  *new_string = '\0'; //adds null terminator
  return string_ptr; //returns pointer that holds memory address of pointer to newy made string
}


Employee* struct_rand(){
  char* a = string_make(STRING_LENGTH); //creates a char pointer to random string
  int birth_year = rand(); //holds random int
  int start_year = rand(); //hold random int
  
  Employee* new =  make_employee(a, birth_year, start_year); //makes random employee 
  return new;// returns pointer to random employee
}

Employee**  array_struct(int structs){
  Employee **arr;//creates employee pointer to point to array of employee pointers
  arr =  malloc(structs * sizeof(Employee*)); //allocates memory to pointer
 for(int i = 0; i < structs; i++){
   arr[i] = (struct_rand((STRING_LENGTH)));//adds random employees to employee pointer until number of employees is met
 }
 
 return arr; //returns employee pointer to employee pointers
}

void print_array_structs(Employee** arr, int size){
  for(int i = 0; i < size; i++){//prints out fields of employees pointed to by the employee pointers 
    printf("Employee  %d:\n", i+1);
    printf("This employee's ID is %s\n", arr[i]->id);
    printf("This employee's birth year is %d\n", arr[i]->birth_year);
    printf("This employee's start year is %d\n", arr[i]->start_year);
    printf("Address of this employee is: %p\n", arr[i]);
  }
  
}

Employee** shallow_copy(Employee** arr, int num_of_employees){
  Employee** copy;//creates an employee pointer pointing to an array of employee pointers
  copy = malloc(num_of_employees * sizeof(Employee*));//allocate memory
  for(int i = 0; i < num_of_employees; i++){// loop that copies employee pointers to copy
    copy[i] = arr[i];
  }

  return copy;// returns the shallow copy 
  
}

Employee** deep_copy(Employee** arr, int num_of_employees){
  Employee** copy;//creates an employee pointer pointing to an array of employee pointers
  copy = malloc(num_of_employees * sizeof(Employee*));//allocates memory
  
  for(int i = 0; i < num_of_employees; i++){// loop that makes new employee pointers with same data as original and copies those employee pointers into copy
    copy[i] = make_employee(arr[i]->id, arr[i]->birth_year, arr[i]->start_year);
  }

  return copy;//returns the deep copy

}
void free_array_employees(Employee** arr, int num_of_employees){
  for(int i = 0; i < num_of_employees; i++){
    free(arr[i]); //frees memory of every employee pointer
  }
  free(arr); //frees employee pointer pointing to all employee pointers
}


  



  



  



  
