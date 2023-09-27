#include "hex.h"
#include <stdio.h>
#include <string.h>

static int jfcconvert(char *string)
{
  int i=0;
   while (string[i]) {
     if (string[i]>=65 && string[i]<=90)
       string[i] = string[i] + 32;
     i++;
   }
   return(i);
}


int main(int argc, char *argv[]) {
   set_add_cb(jfcconvert);
   char * string = "main in C";
   char mystring[16];
   strcpy(mystring, string);
   
   doconvert(mystring);
   printf("main says: %s\n", mystring); 
}
