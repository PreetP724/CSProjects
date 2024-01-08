/** mystring.h
 * @author Mike Ciaraldi, Blake Nelson
 * My own versions of some of the C-style string functions
*/
#ifndef MYSTRING_H		// Remember guard
#define MYSTRING_H

// Function prototype(s):
char* mystrdup(const char* src);
char* mystrndup(const char* src, size_t n);
size_t mystrlen(const char* s);
char *mystrcpy(char *restrict dest, const char *src);
char *mystrncpy(char *restrict dest, const char *restrict src, size_t n);
char *mystrcat(char *restrict dest, const char *restrict src);
char *mystrncat(char *restrict dest, const  char *restrict src, size_t n);
#endif
