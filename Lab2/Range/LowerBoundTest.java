package org.jfree.data.test;

import static org.junit.Assert.assertEquals;
import org.jfree.data.Range;
import org.junit.*;

public class LowerBoundtest {

    private Range rangeObjectUnderTest;

    @Before
    public void setUp() throws Exception {
        rangeObjectUnderTest = new Range(-100, 100);
    }

    @After
    public void tearDown() throws Exception {
    	rangeObjectUnderTest = null;
    }

    @Test
    public void testLowerBoundNegativeValue() {
        assertEquals("Lower bound should be -100", -100, rangeObjectUnderTest.getLowerBound(), 0.000000001d);
    }

    @Test
    public void testLowerBoundZeroValue() {
        rangeObjectUnderTest = new Range(0, 10);
        assertEquals("Lower bound should be 0", 0, rangeObjectUnderTest.getLowerBound(), 0.000000001d);
    }

    @Test
    public void testLowerBoundPositiveValue() {
        rangeObjectUnderTest = new Range(5, 10);
        assertEquals("Lower bound should be 5", 5, rangeObjectUnderTest.getLowerBound(), 0.000000001d);
    }


    @Test
    public void testLowerBoundFractionalValue() {
        rangeObjectUnderTest = new Range(-3.5, 2.75);
        assertEquals("Lower bound should be -3.5", -3.5, rangeObjectUnderTest.getLowerBound(), 0.000000001d);
    }

}
