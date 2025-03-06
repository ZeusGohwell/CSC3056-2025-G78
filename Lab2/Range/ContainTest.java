package org.jfree.data.test;

import static org.junit.Assert.*;
import org.jfree.data.Range;
import org.junit.*;

public class ContainTest {

    private Range rangeObjectUnderTest;

    @Before
    public void setUp() throws Exception {
        rangeObjectUnderTest = new Range(-5, 5);
    }

    @After
    public void tearDown() throws Exception {
        rangeObjectUnderTest = null;
    }

    @Test
    public void testValueInsideRange() {
        assertTrue("0 should be inside the range (-5, 5)", rangeObjectUnderTest.contains(0));
    }

    @Test
    public void testValueBelowRange() {
        assertFalse("-10 should be outside the range (-5, 5)", rangeObjectUnderTest.contains(-10));
    }

    @Test
    public void testValueAboveRange() {
        assertFalse("10 should be outside the range (-5, 5)", rangeObjectUnderTest.contains(10));
    }

    @Test
    public void testValueAtLowerBound() {
        assertTrue("-5 should be inside the range (-5, 5)", rangeObjectUnderTest.contains(-5));
    }

    @Test
    public void testValueAtUpperBound() {
        assertTrue("5 should be inside the range (-5, 5)", rangeObjectUnderTest.contains(5));
    }

    @Test
    public void testValueJustBelowLowerBound() {
        assertFalse("-5.001 should be outside the range (-5, 5)", rangeObjectUnderTest.contains(-5.001));
    }

    @Test
    public void testValueJustAboveUpperBound() {
        assertFalse("5.001 should be outside the range (-5, 5)", rangeObjectUnderTest.contains(5.001));
    }

    @Test
    public void testEqualRange() {
        rangeObjectUnderTest = new Range(5, 5);
        assertTrue("5 should be inside the range (5, 5)", rangeObjectUnderTest.contains(5));
    }

    @Test
    public void  testOutsideEqualRange() {
        rangeObjectUnderTest = new Range(5, 5);
        assertFalse("6 should be outside the range (5, 5)", rangeObjectUnderTest.contains(6));
    }
}
