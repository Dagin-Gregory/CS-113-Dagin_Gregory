package edu.miracosta.cs113;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * MorseCodeTreeTest : Tester class for MorseCodeTree, specifically for its translateFromMorseCode method and
 * related exceptional input cases.
 *
 * @author King
 * @version 1.0
 */
public class MorseCodeTreeTest {

    /** Every morse code value which represents a single letter from A-Z using '*' for dots and '-' for dashes. */
    private static final String[] SINGLES_CODE = {"*-", "-***", "-*-*",    // a, b, c
                                                    "-**", "*", "**-*",    // d, e, f
                                                    "--*", "****", "**",   // g, h, i
                                                    "*---", "-*-", "*-**", // j, k, l
                                                    "--", "-*", "---",     // m, n, o
                                                    "*--*", "--*-", "*-*", // p, q, r
                                                    "***", "-", "**-",     // s, t, u
                                                    "***-", "*--", "-**-", // v, w, x
                                                    "-*--", "--**"};       // y, z

    /** Solutions to SINGLES_CODE. */
    private static final String[] SINGLES_DECODED = {"a", "b", "c", "d", "e", "f",
                                                    "g", "h", "i", "j", "k", "l",
                                                    "m", "n", "o", "p", "q", "r",
                                                    "s", "t", "u", "v", "w", "x",
                                                    "y", "z"};

    /** Entries containing a series of morse code values, each representing a single letter value with dots ('*')
     *  and dashes ('-') and delimited from each other using spaces. */
    private static final String[] MULTIPLES_CODE = {"* -", "** *- -* --", "*** **- *-* *-- -** -*- --* ---",
                                                    "**** ***- **-* *-** *--* *---",
                                                    "-*** -**- -*-* -*-- --** --*-",
                                                    "**** * *-** *-** --- *-- --- *-* *-** -**",
                                                    "*-- **** * -* *-- * --* --- - - ---",
                                                    "*- ***- *-** - *-* * * ***",
                                                    "** -- *- -** * -- -*-- *-- *- -*--",
                                                    "- --- - **** * -** --- --- *-* *- -* -** *** *- ** -**",
                                                    "-*** -*-- * -* * *-* -*--",
                                                    "*-** --- *-**"};

    /** Solutions to MULTIPLES_CODE. */
    private static final String[] MULTIPLES_DECODED = {"et", "ianm", "surwdkgo", "hvflpj", "bxcyzq", "helloworld",
                                                    "whenwegotto", "avltrees", "imademyway", "tothedoorandsaid",
                                                    "byenery", "lol"};

    /** Erroneous input includes invalid characters (not '*' or '-'), or tokens that are too long to represent any
     *  letter value. */
    private static final String[] ERROR_CODE = {"***** ----- -*-*-",
                                                "*- -*** -**---",
                                                "-.-. ... -.. .--",
                                                "_*_* *** _** *__",
                                                "*- invalid characters *-",
                                                "should be handled accordingly"};

    /** Your MorseCodeTree object containing the method translateFromMorseCode(String). */
    private MorseCodeTree mct;

    /** Instantiates the MorseCodeTree object prior to every test. */
    @Before
    public void setUp() {
        mct = new MorseCodeTree();
    }

    /**
     * Helper method compares an array of morse code entries with a parallel array of its corresponding English
     * letter translation.
     *
     * @param morseCode The array of morse code values, using '*' for dots and '-' for dashes and is delimited
     *                  from each letter with a space.
     * @param decodedValue The array of English letter values expected after translating the values in
     *                     morseCode at the same indices.
     */
    private void compareDecodedValues(String[] morseCode, String[] decodedValue) {
        try {
            for (int i = 0; i < morseCode.length; i ++) {
                String actualDecodedValue = mct.translateFromMorseCode(morseCode[i]);
                assertEquals("Failure for case " + i, decodedValue[i], actualDecodedValue);
            }
        }
        catch (Exception e) { fail("Test failed - an Exception was thrown."); }
    }

    @Test
    public void singleLettersTest() {
        compareDecodedValues(SINGLES_CODE, SINGLES_DECODED);
    }

    @Test
    public void multipleLettersTest() {
        compareDecodedValues(MULTIPLES_CODE, MULTIPLES_DECODED);
    }

    @Test
    public void testErrors() {
        for (String errorCode : ERROR_CODE) {
            try {
                mct.translateFromMorseCode(errorCode);
                fail("Test failed - \"" + errorCode + "\" should prompt an Exception.");
            }
            catch (Exception e) { /* Test passed. */ }
        }
    }

} // End of class MorseCodeTreeTest