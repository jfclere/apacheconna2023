#include <stdio.h>

void jfcconvert(char *string);
int main() {
   char string[] = "Hello, World!";
   printf("%s\n", string);
   jfcconvert(string);
   printf("%s\n", string);
   return 0;
}
