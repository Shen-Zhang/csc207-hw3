package edu.grinnell.csc207.zhangshe.hw3;

import static org.junit.Assert.*;

import java.io.PrintWriter;

import org.junit.Test;

/**
 * 
 * @author Shen Zhang 
 * @date Feb 5, 2014
 * csc207-hw3
 */

public class StringUtilsTest
{

  @Test
  public void
    testSplitAs ()
  {
    assertArrayEquals ("no need to be separated", new String[] { "a:b:c" },
                       StringUtils.splitAt ("a:b:c", ' '));
    assertArrayEquals (new String[] { "a", "b", "c" },
                       StringUtils.splitAt ("a:b:c", ':'));
    assertArrayEquals (new String[] { "a", "b", "c" },
                       StringUtils.splitAt ("a b c", ' '));
    assertArrayEquals (new String[] { "a:b:c" },
                       StringUtils.splitAt ("a:b:c", ' '));
    assertArrayEquals ("one field", new String[] { "a" },
                       StringUtils.splitAt ("a", ':'));
    assertArrayEquals ("empty inner field", new String[] { "a", "", "c" },
                       StringUtils.splitAt ("a::c", ':'));
    assertArrayEquals ("leading empty field", new String[] { "", "a" },
                       StringUtils.splitAt (":a", ':'));
    assertArrayEquals ("trailing empty field", new String[] { "a", "" },
                       StringUtils.splitAt ("a:", ':'));

  } // testSplitAs()

  @Test
  public void
    testSplitCSV ()
  {
    assertArrayEquals ("no quotation marks", new String[] { "a", "b", "c" },
                       StringUtils.splitCSV ("a,b,c"));
    assertArrayEquals (new String[] { "a", "a,b", "c" },
                       StringUtils.splitCSV ("a,\"a,b\",c"));
    assertArrayEquals ("comma inside the text",new String[] { "a", "a,bcc", "c,b" },
                       StringUtils.splitCSV ("a,\"a,bcc\",\"c,b\""));
    assertArrayEquals ("double quotation marks appear in sequence",new String[] { "a", "b,b\"", "c" },
                       StringUtils.splitCSV ("a,\"b,b\"\"\",c"));
    assertArrayEquals (new String[] { "a", "b,b\"", "c", "d" },
                       StringUtils.splitCSV ("a,\"b,b\"\"\",c,d"));
    assertArrayEquals (new String[] { "ab", "a,b", "c" },
                       StringUtils.splitCSV ("\"ab\",\"a,b\",c"));
    assertArrayEquals (new String[] { "a,,,b", "ab\"\"b,c", "ds,sd" },
                       StringUtils.splitCSV ("\"a,,,b\",\"ab\"\"\"\"b,c\",\"ds,sd\""));
    assertArrayEquals (new String[] { "a", "b","c","a,b,c","abc"},
                       StringUtils.splitCSV ("a,b,c,\"a,b,c\",abc"));
  } //testSplitCSV

  @Test
  public void
    testDeLeet ()
  {
    assertEquals ("e", StringUtils.deLeet ("3"));
    assertEquals ("leet", StringUtils.deLeet ("133+"));
    assertEquals ("eat banana", StringUtils.deLeet ("3@+ |3@|\\|@|\\|@"));
    assertEquals ("here", StringUtils.deLeet ("h3r3"));
    assertEquals ("not able to", StringUtils.deLeet ("|\\|0t @|313 +0"));
    assertEquals ("boat tea ton boot", StringUtils.deLeet ("|30@+ +3@ +0|\\| |300+"));
  } //testDeLeet

  @Test
  public void
    testNameGame ()
  {
    PrintWriter pen = new PrintWriter (System.out, true);
    pen.println (StringUtils.nameGame ("Samuel"));
    pen.println (StringUtils.nameGame ("William"));
    pen.println (StringUtils.nameGame ("Zack"));
    pen.println (StringUtils.nameGame ("Shen"));
  } //testNameGame

} // StringUtilsTest
