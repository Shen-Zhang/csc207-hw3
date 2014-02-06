package edu.grinnell.csc207.zhangshe.hw3;

import java.math.BigInteger;

/**
 * 
 * @author Shen Zhang 
 * @date Feb 5, 2014
 * csc207-hw3
 */

public class Calculator
{

  /**
   * 
   * @param str
   *          a string which only contains integers, +, /, *, - and ^
   * @return a BigInteger, whose value is computed based on the given string
   */
  public static BigInteger
    eval0 (String str)
  {
    BigInteger soFar = new BigInteger ("0");
    BigInteger newVal = new BigInteger ("0");
    int space = str.indexOf (' ');
    String copy = str;

    if (space < 0)
      return soFar = new BigInteger (str); // return the value of the string if
                                           // there is no space

    if (space >= 0)
      {
        soFar = soFar.add (new BigInteger (copy.substring (0, space)));
        // soFar gets the value of the first integer
        copy = copy.substring (space);
        space = copy.indexOf (' ');
        char command = copy.charAt (space + 1); // get the command
        int newSpace = copy.indexOf (' ', space + 3);
        while (newSpace >= 0)
          {
            newVal = newVal.add (new BigInteger (copy.substring (space + 3,
                                                                 newSpace))); // get the value 
                                                                              // of the second integer
                                                                              
            soFar = compute (soFar, newVal, command); // call compute
            newVal = new BigInteger ("0"); // set the second integer back to 0
            copy = copy.substring (newSpace);
            space = 0; // set space to 0;
            command = copy.charAt (space + 1);
            newSpace = copy.indexOf (' ', space + 3);
          } // while
        newVal = newVal.add (new BigInteger (copy.substring (space + 3))); // the last integer
                                                                           
        soFar = compute (soFar, newVal, command);
        System.out.println (soFar);
      } // if space >= 0
    return soFar;
  } // eval0

  /**
   * 
   * @param soFar
   *          a BigInteger
   * @param newVal
   *          a BigInteger
   * @param command
   *          a character
   * @return a BigInteger
   * 
   * @precondition command can only be +, -, *, / or ^
   * @postcondition BigInteger.divide will truncate any number after the decimal
   *                points
   */
  public static BigInteger
    compute (BigInteger soFar, BigInteger newVal, char command)
  {
    switch (command)
      {
        case '+': // add
          soFar = soFar.add (newVal);
          break;
        case '-': // subtract
          soFar = soFar.subtract (newVal);
          break;
        case '*': // multiply
          soFar = soFar.multiply (newVal);
          break;
        case '/': // divide
          soFar = soFar.divide (newVal);
          break;
        case '^': // exponential
          soFar = soFar.pow (newVal.intValue ());
          break;
      } // switch
    return soFar;
  } // compute (BigInteger soFar, BigInteger newVal, char command)

} // class Calculator
