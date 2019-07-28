package com.android.marketplace;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void equivalent_byOperator_isCorrect() {
        int a = 127;
        int b = 127;
        assertEquals(true, a == b);
    }

    @Test
    public void equivalent_byOperator_isNotCorrect() {
        Integer a = 128;
        Integer b = 128;
        assertNotEquals(true, a == b);
    }

    @Test
    public void equivalent_referenceType_isCorrect() {
        Integer a = 128;
        Integer b = 128;
        assertEquals(true, a.equals(b));

        a = 127;
        b = 127;
        assertEquals(true, a.equals(b));
    }

}