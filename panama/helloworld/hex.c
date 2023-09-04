/*
 * convert a string to uppercase or lower case
 */
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
