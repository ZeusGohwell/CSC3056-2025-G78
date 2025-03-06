package org.jfree.data.test;

import static org.junit.Assert.*;
import org.jfree.data.Range;
import org.junit.*;

public class ConstrainTest {

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
        assertEquals("0 should return 0 (inside the range)", 0, rangeObjectUnderTest.constrain(0), 0.000000001d);
    }

    @Test
    public void testValueBelowRange() {
        assertEquals("-10 should return -5 (below than the lower bound)", -5, rangeObjectUnderTest.constrain(-10), 0.000000001d);
    }

    @Test
    public void testValueAboveRange() {
        assertEquals("10 should return 5 (above the upper bound)", 5, rangeObjectUnderTest.constrain(10), 0.000000001d);
    }

    @Test
    public void testValueAtLowerBound() {
        assertEquals("-5 should return -5 (same as lower bound)", -5, rangeObjectUnderTest.constrain(-5), 0.000000001d);
    }

    @Test
    public void testValueAtUpperBound() {
        assertEquals("5 should return 5 (same as upper bound)", 5, rangeObjectUnderTest.constrain(5), 0.000000001d);
    }

    @Test
    public void testValueJustBelowLowerBound() {
        assertEquals("-5.01 should return -5 (just below lower bound)", -5, rangeObjectUnderTest.constrain(-5.001), 0.000000001d);
    }

    @Test
    public void testValueJustAboveUpperBound() {
        assertEquals("5.01 should return 5 (just above upper bound)", 5, rangeObjectUnderTest.constrain(5.001), 0.000000001d);
    }

    @Test
    public void testEqualRange() {
        rangeObjectUnderTest = new Range(5, 5);
        assertEquals("5 should return 5 (equal range)", 5, rangeObjectUnderTest.constrain(5), 0.000000001d);
    }
    
    @Test
    public void testOutsideEqualRange() {
        rangeObjectUnderTest = new Range(5, 5);
        assertEquals("6 should return 5 (outside equal range)", 5, rangeObjectUnderTest.constrain(6), 0.000000001d);
    }
}
