package org.jfree.data.test;

import static org.junit.Assert.*;

import org.jfree.data.DataUtilities;
import org.jfree.data.Range;
import org.junit.*;

public class LengthTest {

    private Range rangeObjectUnderTest;

    @Before
    public void setUp() throws Exception {
        rangeObjectUnderTest = new Range(5, 15);
    }

    @After
    public void tearDown() throws Exception {
        rangeObjectUnderTest = null;
    }

    @Test
    public void testLengthPositiveRange() {
        assertEquals("Length should be 10", 10, rangeObjectUnderTest.getLength(), 0.000000001d);
    }

    @Test
    public void testLengthNegativeRange() {
        rangeObjectUnderTest = new Range(-15, -5);
        assertEquals("Length should be 10", 10, rangeObjectUnderTest.getLength(), 0.000000001d);
    }

    @Test
    public void testLengthZero() {
        rangeObjectUnderTest = new Range(5, 5);
        assertEquals("Length should be 0", 0, rangeObjectUnderTest.getLength(), 0.000000001d);
    }

    @Test
    public void testLengthFractionalValues() {
        rangeObjectUnderTest = new Range(1.5, 3);
        assertEquals("Length should be 1.5", 1.5, rangeObjectUnderTest.getLength(), 0.000000001d);
    }

    @Test
    public void testLengthnegativepositive() {
        rangeObjectUnderTest = new Range(-1, 1);
        assertEquals("Length should be 2", 2, rangeObjectUnderTest.getLength(), 0.000000001d);
    }

    @Test
    public void testInvalidRange() {
        try {
            rangeObjectUnderTest = new Range(5, -10);
            fail("No exception thrown. The expected outcome was: a thrown exception of type: IllegalArgumentException");
        } catch (Exception e) {
            assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
        }
    }

}
