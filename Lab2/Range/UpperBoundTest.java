package org.jfree.data.test;

import static org.junit.Assert.assertEquals;
import org.jfree.data.Range;
import org.junit.*;

public class UpperBoundTest {

    private Range rangeObjectUnderTest;

    @Before
    public void setUp() throws Exception {
        rangeObjectUnderTest = new Range(-10, -5);
    }

    @After
    public void tearDown() throws Exception {
    	 rangeObjectUnderTest = null;
    }

    @Test
    public void testUpperBoundNegativeValue() {
        assertEquals("Upper bound should be -5", -5, rangeObjectUnderTest.getUpperBound(), 0.000000001d);
    }

    @Test
    public void testUpperBoundZeroValue() {
        rangeObjectUnderTest = new Range(-10, 0);
        assertEquals("Upper bound should be 0", 0, rangeObjectUnderTest.getUpperBound(), 0.000000001d);
    }

    @Test
    public void testUpperBoundPositiveValue() {
        rangeObjectUnderTest = new Range(-5, 10);
        assertEquals("Upper bound should be 10", 10, rangeObjectUnderTest.getUpperBound(), 0.000000001d);
    }

    @Test
    public void testUpperBoundFractionalValue() {
        rangeObjectUnderTest = new Range(-3.5, 2.75);
        assertEquals("Upper bound should be 2.75", 2.75, rangeObjectUnderTest.getUpperBound(), 0.000000001d);
    }

}

