#include <stdio.h>
#include "hex.h"

int main() {
   char string[] = "Hello, World!";
   printf("%s\n", string);
   jfcconvert(string);
   printf("%s\n", string);
   return 0;
}
