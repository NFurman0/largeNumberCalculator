public class Calculator {
  
  public boolean isGreater(String a, String b) {
    if(a.contains(".") && b.contains(".")) {
      
      String aInt = a.substring(0, a.indexOf("."));
      String bInt = b.substring(0, b.indexOf("."));
      String aDec = a.substring(a.indexOf(".")+1);
      String bDec = b.substring(b.indexOf(".")+1);
      if(isGreater(aInt, bInt)) return true;
      if(isGreater(bInt, aInt)) return false;
      else {
        return isGreater(aDec, bDec);
      }
    } else if(a.contains(".")) {
      String aInt = a.substring(0, a.indexOf("."));
      if(aInt.length() == 0) aInt = "0";
      if(isGreater(b, aInt)) return false;
      else return true;
    } else if(b.contains(".")) {
      String bInt = b.substring(0, b.indexOf("."));
      if(bInt.length() == 0) bInt = "0";
      if(isGreater(a, bInt)) return true;
      else return false;
    }
    if(!a.substring(0, 1).equals("-") && !b.substring(0, 1).equals("-")) {
      if(b.length() > a.length()) return false;
      else if(a.length() > b.length()) return true;
  
      for(int i = 0; i < a.length(); i++) {
        int aDigit = Character.getNumericValue(a.charAt(i));
        int bDigit = Character.getNumericValue(b.charAt(i));
        if(aDigit > bDigit) return true;
        else if(bDigit > aDigit) return false;
      }
      return false; 
    } else {
      if(a.substring(0, 1).equals("-") && !b.substring(0, 1).equals("-")) return false; 
      if(!a.substring(0, 1).equals("-") && b.substring(0, 1).equals("-")) return true; 
      if(a.length() > b.length()) return false;
      if(b.length() > a.length()) return true;

      String absoluteA = a.substring(1);
      String absoluteB = b.substring(1);
      for(int i = 0; i < absoluteA.length(); i++) {
        int aDigit = Character.getNumericValue(absoluteA.charAt(i));
        int bDigit = Character.getNumericValue(absoluteB.charAt(i));
        if(aDigit > bDigit) return false;
        else if(bDigit > aDigit) return true;
      }
      return false; 
    }
  }
  
  public String removeZeroes(String a){
    String noZeroes = "" + a;
    int constLength = noZeroes.length()-1;
    for(int i = 0; i < constLength; i++) {
      if(Character.getNumericValue(noZeroes.charAt(0)) == 0) {
        noZeroes = noZeroes.substring(1);
      } else break;
    }
    if(noZeroes.length() == 0) return "0";
    if(!a.contains(".")) return noZeroes;
    constLength = noZeroes.length()-1;
    for(int i = constLength; i >= 0; i--) {
      if(Character.getNumericValue(noZeroes.charAt(noZeroes.length()-1)) == 0) {
        noZeroes = noZeroes.substring(0, i);
      } else break;
    }
    if(noZeroes.charAt(noZeroes.length()-1) == '.') noZeroes = noZeroes.substring(0, noZeroes.length()-1);
    if(noZeroes.length() == 0) return "0";
    else if(noZeroes.charAt(0) == '.') noZeroes = "0" + noZeroes;
    return noZeroes;
  }
  
  public String add(String a, String b) {
    if(a.charAt(0) == '-' && b.charAt(0) == '-') return removeZeroes("-" + add(a.substring(1), b.substring(1)));
    else if(a.charAt(0) == '-') return removeZeroes(subtract(b, a.substring(1)));
    else if(b.charAt(0) == '-') return removeZeroes(subtract(a, b.substring(1)));

    if(a.contains(".") && b.contains(".")) {
      String intSum = add(a.substring(0, a.indexOf(".")), b.substring(0, b.indexOf(".")));
      String aDec = a.substring(a.indexOf(".")+1);
      String bDec = b.substring(b.indexOf(".")+1);
      if(aDec.length() > bDec.length()) {
        int constant = aDec.length()-bDec.length();
        for(int i = 0; i < constant; i++) {
          bDec += "0";
        }
      } else {
        int constant = bDec.length()-aDec.length();
        for(int i = 0; i < constant; i++) {
          aDec += "0";
        }
      }
      String decSum = "." + add(aDec, bDec);
      if(decSum.length()-1 > Math.max(a.substring(a.indexOf(".")+1).length(), b.substring(b.indexOf(".")+1).length())) {
        intSum = add(intSum, "1");
        decSum = "." + decSum.substring(2);
      }
      return removeZeroes(intSum + decSum);
    } else if(a.contains(".")) return removeZeroes(add(a.substring(0, a.indexOf(".")), b) + a.substring(a.indexOf(".")));
    else if(b.contains(".")) return removeZeroes(add(a, b.substring(0, b.indexOf("."))) + b.substring(b.indexOf(".")));
    
    String sum = "";
    int tempInt = 0;
    int carry = 0;
    if(a.length() > b.length()) {
      int constant = a.length()-b.length();
      for(int i = 0; i < constant; i++) {
        b = "0" + b;
      }
    } else {
      int constant = b.length()-a.length();
      for(int i = 0; i < constant; i++) {
        a = "0" + a;
      }
    }
    
    int counter = b.length()-1;
    for(int i = a.length()-1; i >= 0; i--) {
      tempInt = Character.getNumericValue(a.charAt(i))+Character.getNumericValue(b.charAt(counter))+carry;
      counter--;
      carry = tempInt/10; 
      sum = "" + tempInt%10 + sum; 
    }
    if(carry>0) sum = "" + carry + sum;
    
    return removeZeroes(sum);
  }

  public String subtract(String a, String b) {
    if(a.contains("-") || b.contains("-")) {
      return add(a, b);
    }
    
    if(a.contains(".") && b.contains(".")) {
      String intDif = subtract(a.substring(0, a.indexOf(".")), b.substring(0, b.indexOf(".")));
      String aDec = a.substring(a.indexOf(".")+1);
      String bDec = b.substring(b.indexOf(".")+1);
      String decDif = ".";
      if(aDec.length() > bDec.length()) {
        int constant = aDec.length()-bDec.length();
        for(int i = 0; i < constant; i++) {
          bDec += "0";
        }
      } else {
        int constant = bDec.length()-aDec.length();
        for(int i = 0; i < constant; i++) {
          aDec += "0";
        }
      }
      decDif += subtract(aDec, bDec);
      for(int i = 0; i < aDec.length()-(decDif.length()-1); i++) {
        decDif = decDif.substring(0, decDif.indexOf(".")) + ".0" + decDif.substring(decDif.indexOf(".")+1);
      }
      if(decDif.contains("-")) decDif = "-" + decDif.substring(0, decDif.indexOf("-")) + decDif.substring(decDif.indexOf("-")+1);
      if(!isGreater(decDif, "0")) {
        intDif = subtract(intDif, "1");
        decDif = subtract("1", "0" + decDif.substring(decDif.indexOf(".")));
      }
      return removeZeroes(intDif + decDif.substring(decDif.indexOf(".")));
    } else if(a.contains(".")) {
      String intDif = subtract(a.substring(0, a.indexOf(".")), b);
      if(isGreater("0", intDif)) return removeZeroes(intDif + subtract("1", a.substring(a.indexOf("."))));
      else return removeZeroes(subtract(a.substring(0, a.indexOf(".")), b) + a.substring(a.indexOf(".")));
    }
    else if(b.contains(".")) {
      String intDif = subtract(a, b.substring(0, b.indexOf(".")));
      intDif = subtract(intDif, "1");
      String tenPowers = "1";
      while(isGreater(b.substring(b.indexOf(".")+1), tenPowers)) {
        tenPowers += "0";
      }
      String decDif = "." + subtract(tenPowers, b.substring(b.indexOf(".")+1));
      return removeZeroes(intDif + decDif);
    }
    
    String difference = "";
    int tempInt = 0;
    int carry = 0;
    
    if(isGreater(b, a)) return "-" + subtract(b, a); 
    int counter = b.length()-1;
    for(int i = a.length()-1; i >= 0; i--) {
      if(counter >= 0) {
        tempInt = Character.getNumericValue(a.charAt(i))-Character.getNumericValue(b.charAt(counter))-carry;
        counter--;
      } else {
        tempInt = Character.getNumericValue(a.charAt(i))-carry;
      }
      if(tempInt < 0) { 
        carry = 1; 
        tempInt+=10;
      } else {
        carry = 0;
      }
      difference = "" + tempInt%10 + difference; 
    }
    return removeZeroes(difference);
  }

  public String multDigit(String a, String b) {
    String product = "0";
    int counter = -1;
    for(int i = a.length()-1; i >= 0; i--) {
      counter++;
      String step = "" + Character.getNumericValue(a.charAt(i))*Integer.parseInt(b);
      for(int j = 0; j < counter; j++) {
        step = step + "0";
      }
      product = add(product, step);
    }
    return removeZeroes(product);
  }

  public String raise(String a, String b) {
    if(isGreater(b, "0")) {
      String result = a;
      for(int i = 1; isGreater(b, ("" + i)); i++) {
        result = multiply(result, a);
        System.out.println(result);
      }
      return result;
    } else if(b.equals("0")) {
      return "1";
    } else {
      return "1/" + raise(a, b.substring(1));
    }
  }

  public String multiply(String a, String b) {
    if(a.contains("-") && b.contains("-")) {
      return multiply(a.substring(1), b.substring(1));
    } else if(a.contains("-")) {
      return "-" + multiply(a.substring(1), b);
    } else if(b.contains("-")) {
      return "-" + multiply(a, b.substring(1));
    }
    if(a.contains(".") || b.contains(".")) return decimalMult(a, b);
    
    String product = "0";
    int counter = -1;
    for(int i = b.length()-1; i >= 0; i--) {
      counter++;
      String bDigit = b.substring(i, i+1);
      String digitTotal = multDigit(a, bDigit);
      for(int j = 0; j < counter; j++) {
        digitTotal = digitTotal + "0";
      }
      product = add(product, digitTotal);
    }
    return removeZeroes(product);
  }

  public boolean isDivisible(String a, String b) { 
    if(isGreater(b, a)) return false;
    else if(!isGreater(a, b)) return true;
    int bDigit = Integer.parseInt(b);
    //Switch statements killed my family
    if(bDigit == 0) return false;
    if(bDigit == 1) return true;
    if(bDigit == 2) {
      int aDigit = Integer.parseInt(a.substring(a.length()-1));
      if(aDigit%2 == 0) return true;
      else return false;
    }
    if(bDigit == 3) {
      String counter = "" + a;
      String counter2 = "0";
      do {
        for(int i = 0; i < counter.length(); i++) {
          counter2 = add(counter2, counter.substring(i, i+1));
        }
        counter = counter2;
        counter2 = "0";
      }while(counter.length() > 1);
      if(counter.equals("3") || counter.equals("6") || counter.equals("9")) return true;
      else return false;
    }
    if(bDigit == 4) {
      if(a.length() > 1) {
        int aDigits = Integer.parseInt(a.substring(a.length()-2));
        if(aDigits%4 == 0) return true;
        else return false;
      } else {
        int aDigits = Integer.parseInt(a);
        if(aDigits%4 == 0) return true;
        else return false;
      }
    }
    if(bDigit == 5) {
      int aDigit = Integer.parseInt(a.substring(a.length()-1));
      if(aDigit == 0 || aDigit == 5) return true;
      else return false;
    }
    if(bDigit == 6) {
      int aDigit = Integer.parseInt(a.substring(a.length()-1));
      if(aDigit%2 == 0) {
        String counter = "" + a;
        String counter2 = "0";
        do {
          for(int i = 0; i < counter.length(); i++) {
            counter2 = add(counter2, counter.substring(i, i+1));
            }
          counter = counter2;
          counter2 = "0";
        }while(counter.length() > 1);
        if(counter.equals("3") || counter.equals("6") || counter.equals("9")) return true;
        else return false;
      }
      else return false;
    }
    if(bDigit == 7) {
      String counter = "" + a;
      do {
        for(int i = 0; i < a.length(); i++) {
        counter = add(counter.substring(0, counter.length()-1), multDigit(counter.substring(counter.length()-1), "5"));
        }
      }while(counter.length() > 2);
      if(counter.equals("7") || counter.equals("14") || counter.equals("21") || counter.equals("28") || counter.equals("35") || counter.equals("42") || counter.equals("49") || counter.equals("56") || counter.equals("63") || counter.equals("70") || counter.equals("77") || counter.equals("84") || counter.equals("91") || counter.equals("98")) return true;
      else return false;
    }
    if(bDigit == 8) {
      if(a.length() > 2) {
        int aDigits = Integer.parseInt(a.substring(a.length()-3));
        if(aDigits%8 == 0) return true;
        else return false;
      } else {
        int aDigits = Integer.parseInt(a);
        if(aDigits%8 == 0) return true;
        else return false;
      }
    }
    if(bDigit == 9) {
      String counter = "" + a;
      String counter2 = "0";
      do {
        for(int i = 0; i < counter.length(); i++) {
          counter2 = add(counter2, counter.substring(i, i+1));
        }
        counter = counter2;
        counter2 = "0";
      }while(counter.length() > 1);
      if(counter.equals("9")) return true;
      else return false;
    }
    return false;
  }

  public String divide(String a, String b) {
    String counter = "0";
    while(isGreater(a, "0")) {
      counter = add(counter, "1");
      a = subtract(a, b);
    }
    if(!a.equals("0")) counter = subtract(counter, "1");
    return counter;
  }

  public void collatz(String a) {
    if(a.contains(".")) System.out.println("Cannot use hailstone function on decimal numbers.");
    boolean flag = true;
    String working = "" + a;
    while(flag) {
      if(a.equals("1") || a.equals("0")) flag = false;
      if(isDivisible(working, "2")) working = divide(working, "2");
      else working = add(multiply(working, "3"), "1");
      System.out.println("\t" + working);
      if(working.equals("1") || working.equals("0")) flag = false;
    }
  }

  public boolean isNumber(String a) {
    if(a.length() == 0) return false;
    boolean canHaveDecimalPoint = true;
    for(int i = 0; i < a.length(); i++) {
      try {
        int x = Integer.parseInt(a.substring(i, i+1));
      } catch(Exception e) {
        if(!(i == 0 && a.charAt(0) == '-')) {
          if(a.charAt(i) == '.' && canHaveDecimalPoint) canHaveDecimalPoint = false;
          else return false;
        } 
      }
    }
    return true;
  }

  public String decimalMult(String a, String b) {
    int numDecimalPlaces;
    if(!a.contains(".") && !b.contains(".")) return multiply(a, b);
    if(!a.contains(".")) {
      numDecimalPlaces = (b.length()-b.indexOf(".")-1);
      b = b.substring(0, b.indexOf(".")) + b.substring(b.indexOf(".")+1);
    } else if(!b.contains(".")) {
      numDecimalPlaces = (a.length()-a.indexOf(".")-1);
      a = a.substring(0, a.indexOf(".")) + a.substring(a.indexOf(".")+1);
    } else {
      numDecimalPlaces = (a.length()-a.indexOf(".")-1)+(b.length()-b.indexOf(".")-1);
      a = a.substring(0, a.indexOf(".")) + a.substring(a.indexOf(".")+1);
      b = b.substring(0, b.indexOf(".")) + b.substring(b.indexOf(".")+1);
    }
    
    String product = multiply(a, b);
    product = product.substring(0, product.length()-numDecimalPlaces) + "." + product.substring(product.length()-numDecimalPlaces);
    return removeZeroes(product);
  }
  
  public String sqrt(String a) {
    if(isGreater("0", a)) return sqrt(a.substring(1)) + "i";
    if(a.equals("0")) return "0";
    String counter = "" + a;
    String limit = "";
    while(isGreater(raise(counter, "2"), a)) {
      counter = divide(counter, "2");
    }
    if(a.equals(multiply(counter, counter))) return counter;
    else {
      limit = "" + counter;
      counter = multiply(counter, "2");
    }
    counter = counter.substring(0, counter.length()-1);
    while(isGreater(a, raise(counter, "2"))) {
      if(isGreater(a, raise(add(counter, limit), "2"))) counter = add(counter, limit);
      else {
        limit = divide(limit, "2");
        counter = add(counter, "1");
      }
    }
    if(a.equals(decimalMult(counter, counter))) return counter;
    else counter = subtract(counter, add(limit, "1"));
    while(isGreater(a, decimalMult(counter, counter))) {
      counter = add(counter, "0.1");
    }
    if(a.equals(decimalMult(counter, counter))) return counter;
    else counter = add(counter, "-0.1");
    while(isGreater(a, decimalMult(counter, counter))) {
      counter = add(counter, "0.01");
    }
    if(a.equals(decimalMult(counter, counter))) return counter;
    else counter = add(counter, "-0.01");
    while(isGreater(a, decimalMult(counter, counter))) {
      counter = add(counter, "0.001");
    }
    if(a.equals(decimalMult(counter, counter))) return counter;
    else counter = add(counter, "-0.001");
    while(isGreater(a, decimalMult(counter, counter))) {
      counter = add(counter, "0.0001");
    }
    if(a.equals(decimalMult(counter, counter))) return counter;
    else counter = add(counter, "-0.0001");
    String roundDigit = "0";
    while(isGreater(a, decimalMult(counter + roundDigit, counter + roundDigit))) {
      roundDigit = add(roundDigit, "1");
    }
    if(a.equals(decimalMult(counter + roundDigit, counter + roundDigit))) return counter;
    else roundDigit = subtract(roundDigit, "1");
    if(isGreater(roundDigit, "4")) counter = add(counter, "0.0001");
    return counter;
  }

  public String factorial(String a) {
    if(a.contains("-") || a.contains(".")) return "Domain Error";
    if(a.equals("0") || a.equals("1")) return "1";
    if(a.equals("2")) return "2";
    else return multiply(a, factorial(subtract(a, "1")));
  }

}
