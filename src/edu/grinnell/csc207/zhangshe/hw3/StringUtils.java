
package edu.grinnell.csc207.zhangshe.hw3;

import java.util.ArrayList;
import java.util.Arrays;
/**
 * 
 * @author Shen Zhang 
 * @date Feb 5, 2014
 * csc207-hw3
 */

public class StringUtils
{
  /**
   * 
   * @param str
   *          a string
   * @param ch
   *          a character
   * @return a string array 
   * 
   * @precondition the string should be valid
   * @postcondition the string array should be separated by given separator
   */
  public static String[]
    splitAt (String str, char ch)
  {
    ArrayList<String> split = new ArrayList<String> ();
    ; // create an ArrayList
    int index = str.indexOf (ch); // get index of ch;
    String copy = str;
    while (index >= 0)
      {
        split.add (copy.substring (0, index));
        if ((index + 1) <= str.length ())
          copy = copy.substring (index + 1);
        index = copy.indexOf (ch);
      } // while index < 0
    split.add (copy); // add the last piece of substring into the ArrayList
    return split.toArray (new String[split.size ()]); // convert the ArrayList
                                                      // into a string array
  }// splitAt (String str, char ch)

  /**
   * @param str
   *          A string
   * @return An array of separated substrings
   * @precondition string should be valid and followed the CSV policies
   * @postcondition 
   * 
   */
  public static String[]
    splitCSV (String str)
  {
    ArrayList<String> split = new ArrayList<String> ();
    String copy = str;
    copy = str.replace ("\"\"", "|"); // replace all quotations with
                                      // vertical bar

    int sepIndex = copy.indexOf (',');
    int quoIndex = copy.indexOf ('\"');

    while (sepIndex >= 0) // while there is at least one comma inside the string
      {
        if (quoIndex < 0) // if there is no quotation mark
          {
            copy = copy.replace ('|', '\"'); // change the vertical bar to a
                                             // double quotation
            String[] arr = splitAt (copy, ','); // call splitAt to split
            for (int i = 0; i < arr.length; i++)
              {
                split.add (arr[i]);
              } // i is greater than or equal to the arr.length
            System.out.println ("original string: " + str
                                + " after separated: " + split);
            return split.toArray (new String[split.size ()]); // convert split
                                                              // to an array and
                                                              // return
          } // if quoIndex > 0

        if (sepIndex < quoIndex) // if the first comma is in front of the first
                                 // double quotation,
                                 // we can split the thing before the first
                                 // comma
          {
            split.add (copy.substring (0, sepIndex).replace ('|', '\"'));
            copy = copy.substring (sepIndex + 1);
            sepIndex = copy.indexOf (',');
          } // if sepIndex > quoIndex

        else if (sepIndex > quoIndex) // if the first comma is after the first
                                      // quotation
        // the comma might be inside a string, or right after a string
          {
            int quoIndex2nd = copy.indexOf ('\"', quoIndex + 1); // get the
                                                                 // index of the
                                                                 // second
                                                                 // quotation
                                                                 // mark

            if (quoIndex2nd >= 0)
              {
                if (quoIndex2nd < sepIndex) // which means the first comma is
                                            // right after the 2nd quotation
                                            // mark
                  {
                    split.add (copy.substring (quoIndex + 1, quoIndex2nd)
                                   .replace ('|', '\"'));
                    // replace all vertical bars to quotation marks and
                    // add the string between the first quotation mark and the
                    // second one
                    copy = copy.substring (sepIndex + 1);
                  } // if 2nd > sepIndex*/
                if (quoIndex2nd > sepIndex)
                // which means we need to take whole thing before the first
                // commas as a string
                  {

                    split.add (copy.substring (quoIndex + 1, quoIndex2nd)
                                   .replace ('|', '\"'));

                    if (quoIndex2nd + 2 > copy.length ())
                      {

                        System.out.println ("original string: " + str
                                            + " after separated: " + split);
                        return split.toArray (new String[split.size ()]);
                      }
                    if (quoIndex2nd + 2 < copy.length ())
                      copy = copy.substring (quoIndex2nd + 2);
                  } // if sepIndex < quoIndex

              } // quoIndex2nd < 0
          } // else if

        sepIndex = copy.indexOf (',');
        quoIndex = copy.indexOf ('\"');

      } // while

    if (quoIndex >= 0)
      {
        copy = copy.substring (quoIndex + 1, copy.lastIndexOf ('\"'));
      }
    split.add (copy.replace ('|', '\"'));
    System.out.println ("original string: " + str + " after separated: "
                        + split);
    return split.toArray (new String[split.size ()]);
  }// splitCSV

  /**
   * 
   * @param str
   *          A valid string
   * @return A standard form string
   * @precondition none
   * @postcondition those special characters would be translated into standard form
   */
  public static String
    deLeet (String str)
  {
    String newStr = str;
    //find and replace all following characters into standard form
    if (str.indexOf ("|\\|") >= 0)
      newStr = newStr.replace ("|\\|", "n");
    if (str.indexOf ("|3") >= 0)
      newStr = newStr.replace ("|3", "b");
    if (str.indexOf ('3') >= 0)
      newStr = newStr.replace ("3", "e");
    if (str.indexOf ('1') >= 0)
      newStr = newStr.replace ("1", "l");
    if (str.indexOf ('@') >= 0)
      newStr = newStr.replace ("@", "a");
    if (str.indexOf ('+') >= 0)
      newStr = newStr.replaceAll ("\\+", "t");
    if (str.indexOf ('0') >= 0)
      newStr = newStr.replace ("0", "o");

    System.out.println ("old: " + str + " new: " + newStr);
    return newStr;
  } // deLeet (String str)

  /**
   * 
   * @param name
   *          A valid string
   * @return A string 
   * @precondition name should be begin with consonants
   * @postcondition the string is rhymed by the name
   */
  public static String
    nameGame (String name)
  {
    Character[] vowels = new Character[] { 'a', 'e', 'i', 'o', 'u' }; // all vowels
                                                                      
    int index = name.length () - 1;
    for (int i = 0; i < 5; i++)
      {
        if ((name.indexOf (vowels[i]) >= 0)
            && (name.indexOf (vowels[i]) < index))
          index = name.indexOf (vowels[i]); // get the index of the first vowel
      } // for

    String verse = name + "!\n" + name + ", " + name + " bo B"
                   + name.substring (index) + " Bonana fanna fo F"
                   + name.substring (index) + "\nFee fy mo M"
                   + name.substring (index) + ", " + name + "!";
    return verse;
  } // nameGame (String name)

}// StringUtils
