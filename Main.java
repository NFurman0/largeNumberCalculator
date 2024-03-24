/*
Performs mathematical operations on numbers stored in strings, including those greater than the integer limit. Does not work with strings with a length greater than the integer limit. Only integer division is supported, meaning remainders are ignored.
*/

import java.util.Scanner;
class Main {
  public static void main(String[] args) {
    
    Scanner s = new Scanner(System.in);
    Calculator c = new Calculator();
    while(true) {
      System.out.print("x: ");
      String num1 = s.nextLine();
      if(!c.isNumber(num1)) continue;
      System.out.print("y: ");
      String num2 = s.nextLine();
      if(!c.isNumber(num2)) continue;
      System.out.println("Select operation:\n1. x > y\n2. x + y\n3. x - y\n4. x * y\n5. x / y\n6. x^y (warning: slow for large numbers)\n7. sqrt(x)\n8. Hailstone(x)\n9. x! (warning: slow for large numbers)");
      String x = s.nextLine();
      if(x.equals("1")) {
        System.out.println(c.isGreater(num1, num2));
      } else if(x.equals("2")) {
        System.out.println(c.add(num1, num2));
      } else if(x.equals("3")) {
        System.out.println(c.subtract(num1, num2));
      } else if(x.equals("4")) {
        System.out.println(c.multiply(num1, num2));
      } else if(x.equals("5")) {
        System.out.println(c.divide(num1, num2));
      } else if(x.equals("6")) {
        System.out.println(c.raise(num1, num2));
      } else if(x.equals("7")) {
        System.out.println(c.sqrt(num1));
      } else if(x.equals("8")) {
        c.collatz(num1);
      } else if(x.equals("9")) {
        System.out.println(c.factorial(num1));
      } else break;
    }
    System.out.println("You entered a number that was not an option, or chose to end the program.");
    s.close();
  }
}
