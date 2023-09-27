/*
 * convert a string to uppercase or lower case
 */
#include "hex.h"
#include <stddef.h>

int jfcconvert(char *string)
{
  int i=0;
   while (string[i]) {
     if (string[i]>=65 && string[i]<=90)
       string[i] = string[i] + 32;
     else if (string[i]>=97 && string[i]<=122)
       string[i] = string[i] - 32;
     i++;
   }
   return(i);
}

add_cb mycall = NULL;

int set_add_cb(add_cb add_callback)
{
    mycall = add_callback;
    return(0);
}

int doconvert(char *string)
{
    if (mycall == NULL) {
        return jfcconvert(string);
    } else
        return mycall(string);
}
