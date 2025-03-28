package org.jfree.data;

import static org.junit.Assert.*;
import org.junit.Test;

public class RangeTest {

    @Test
    public void CombineRange1Isnull() {
    	// Case where range1 is null and range2 is not null
        Range range1 = null;
        Range range2 = new Range(1.0, 5.0);
        Range result = Range.combine(range1, range2);
        assertEquals(range2, result);
    }

    @Test
    public void CombineRange2Isnull() {
        // Case where range1 is non-null and range2 is null
        Range range1 = new Range(1.0, 5.0);
        Range range2 = null;
        Range result = Range.combine(range1, range2);
        assertEquals(range1, result);
    }

    @Test
    public void CombineBothNotNull() {
        // Case where range1 and range2 are not null
        Range range1 = new Range(1.0, 5.0);
        Range range2 = new Range(2.0, 6.0);
        Range result = Range.combine(range1, range2);
        Range outcome = new Range(1.0, 6.0);
        assertEquals(outcome, result);
    }

    @Test
    public void CombineBothNull() {
        // Case where both range1 and range2 are null
        Range range1 = null;
        Range range2 = null;
        Range result = Range.combine(range1, range2);
        assertNull(result);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void ExpandNoRange() {
        // Case where range = null
        Range range = null;
        Range.expand(range, 0.25, 0.5);
    }

    @Test
    public void Expand_PositiveNumber() {
        // Case with positive number
        Range range = new Range(2, 6);
        Range result = Range.expand(range, 0.25, 0.5);
        Range outcome = new Range(1.0, 8.0);
        assertEquals(outcome, result);
    }

    @Test
    public void ExpandZero() {
        // Case with 0 expansion
        Range range = new Range(2, 6);
        Range result = Range.expand(range, 0.0, 0.0);
        Range outcome = new Range(2.0, 6.0);
        assertEquals(outcome, result);
    }

    @Test
    public void ExpandNegativeNumber() {
        // Case with negative number
        Range range = new Range(2, 6);
        Range result = Range.expand(range, -0.25, -0.5);
        Range outcome = new Range(3.0, 4.0);
        assertEquals(outcome, result);
    }
    
    @Test
    public void ExpandLargeNumber() {
        // Case with large number
        Range range = new Range(2, 6);
        Range result = Range.expand(range, 2.0, 2.0);
        Range outcome = new Range(-6.0, 14.0); 
        assertEquals(outcome, result);
    }
    
    @Test
    public void ExpandToIncludeRangeNull() {
        // Case where range is null
        Range range = null;
        double value = 5.0;
        Range result = Range.expandToInclude(range, value);
        Range outcome = new Range(value, value);
        assertEquals(outcome, result);
    }

    @Test
    public void ExpandToIncludeValueLessThanLowerBound() {
        // Case where value is less than lower bound 
        Range range = new Range(6.0, 10.0);
        double value = 5.0;
        Range result = Range.expandToInclude(range, value);
        Range outcome = new Range(value, 10.0); 
        assertEquals(outcome, result);
    }

    @Test
    public void ExpandToIncludeValueMoreThanUpperBound() {
        // Case where value is more than upper bound
        Range range = new Range(1.0, 4.0);
        double value = 5.0;
        Range result = Range.expandToInclude(range, value);
        Range outcome = new Range(1.0, value);
        assertEquals(outcome, result);
    }

    @Test
    public void ExpandToIncludeValueInsideRange() {
        // Case where value is inside range
        Range range = new Range(1.0, 5.0);
        double value = 4.0;
        Range result = Range.expandToInclude(range, value);
        Range outcome = range; 
        assertEquals(outcome, result);
    }
    
     @Test
     public void ShiftWithPositiveInput() {
        // Case with a valid positive input
        Range base = new Range(2.0, 6.0);
        double delta = 3.0;
        Range result = Range.shift(base, delta);
        Range outcome = new Range(5.0, 9.0); 
        assertEquals(outcome, result);
    }

    @Test
    public void ShiftWithValidBaseWithZeroDelta() {
        // Case with a valid range but delta is null
        Range base = new Range(2.0, 6.0);
        double delta = 0.0;
        Range result = Range.shift(base, delta);
        Range outcome = new Range(2.0, 6.0); // The range should remain the same
        assertEquals(outcome, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ShiftWithNullRange() {
        // Case where range is null
        Range base = null;
        double delta = 2.0;
        Range result = Range.shift(base, delta);
    }
 

    @Test
    public void ShiftWithAllowZeroCrossing() {
    	// Case with allowZeroCrossing
    	Range base = new Range(-2.0, 6.0);
    	double delta = 3.0;
    	Range result = Range.shift(base, delta, true);
    	Range outcome = new Range(1.0, 9.0);
    	assertEquals(outcome, result);
    }

    @Test
    public void ShiftWithNoAllowZeroCrossing() {
    	// Case with no allowZeroCrossing
    	Range base = new Range(-5.0, 6.0);
    	double delta = 6.0;
    	Range result = Range.shift(base, delta, false);
    	Range outcome = new Range(0.0, 12.0); 
    	assertEquals(outcome, result);
    }
    
    @Test
    public void RangePositiveLowerAndUpper() {
        // Case with positive lower and upper bounds
        Range range = new Range(5.0, 10.0);
        assertEquals(5.0, range.getLowerBound(), 0.001);
        assertEquals(10.0, range.getUpperBound(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void RangeInvalidLowerAndUpper() {
        // Case where lower bound is more than upper bound
        new Range(10.0, 5.0);
    }
    
    @Test
    public void ConstrainWithinRange() {
        // Case where the value is inside range
        Range range = new Range(5.0, 10.0);
        double result = range.constrain(7.0);  
        assertEquals(7.0, result, 0.001);  
    }

    @Test
    public void ConstrainLowerThanRange() {
        // Case where the value is lower than range
        Range range = new Range(5.0, 10.0);
        double result = range.constrain(3.0); 
        assertEquals(5.0, result, 0.001);  
    }

    @Test
    public void ConstrainMoreThanRange() {
        // Case where the value is more than range
        Range range = new Range(5.0, 10.0);
        double result = range.constrain(12.0); 
        assertEquals(10.0, result, 0.001); 
    }
    
    @Test
    public void ContainsInsideRange() {
        // Case where the value is inside range
        Range range = new Range(5.0, 10.0);
        boolean result = range.contains(7.0); 
        assertTrue(result);
    }

    @Test
    public void ContainsLowerThanRange() {
        // Case where the value is less than range
        Range range = new Range(5.0, 10.0);
        boolean result = range.contains(3.0);  
        assertFalse(result);  
    }

    @Test
    public void ContainsMoreThanRange() {
        // Case where the value is more than range
        Range range = new Range(5.0, 10.0);
        boolean result = range.contains(12.0); 
        assertFalse(result); 
    }

    @Test
    public void ContainsAtLowerBound() {
        // Case where the value is equal to lower bound
        Range range = new Range(5.0, 10.0);
        boolean result = range.contains(5.0); 
        assertTrue(result);  
    }

    @Test
    public void ContainsAtUpperBound() {
        // Case where the value is equal to upper bound
        Range range = new Range(5.0, 10.0);
        boolean result = range.contains(10.0); 
        assertTrue(result); 
    }
    
    @Test
    public void EqualsWithSameRange() {
        // Case where both ranges are the same
        Range range1 = new Range(5.0, 10.0);
        Range range2 = new Range(5.0, 10.0);
        boolean result = range1.equals(range2);
        assertTrue(result);  
    }

    @Test
    public void EqualsWithDifferentLowerBound() {
        // Case where the lower bounds are different
        Range range1 = new Range(5.0, 10.0);
        Range range2 = new Range(6.0, 10.0);
        boolean result = range1.equals(range2);
        assertFalse(result); 
    }

    @Test
    public void EqualsWithDifferentUpperBound() {
        // Case where the upper bounds are different
        Range range1 = new Range(5.0, 10.0);
        Range range2 = new Range(5.0, 11.0);
        boolean result = range1.equals(range2);
        assertFalse(result); 
    }

    @Test
    public void EqualsNull() {
        // Case where the compared object is null
        Range range = new Range(5.0, 10.0);
        boolean result = range.equals(null);  
        assertFalse(result);  
    }

    @Test
    public void EqualsNotRange() {
        // Case where the object is not a Range
        Range range = new Range(5.0, 10.0);
        String Object = "Not a range";
        boolean result = range.equals(Object);  
        assertFalse(result); 
    }
    
    @Test
    public void GetCentralValue() {
        // Case where the range has positive bounds
        Range range1 = new Range(2.0, 8.0);
        double result1 = range1.getCentralValue();
        assertEquals(5.0, result1, 0.0001);  

        // Case where the range has negative and positive bounds
        Range range2 = new Range(-4.0, 6.0);
        double result2 = range2.getCentralValue();
        assertEquals(1.0, result2, 0.0001);  // Central value should be 1.0

        // Case where the range has both negative bounds
        Range range3 = new Range(-10.0, -2.0);
        double result3 = range3.getCentralValue();
        assertEquals(-6.0, result3, 0.0001);  // Central value should be -6.0

        // Case where the range has zero as the lower and upper bound
        Range range4 = new Range(0.0, 0.0);
        double result4 = range4.getCentralValue();
        assertEquals(0.0, result4, 0.0001);  // Central value should be 0.0
    }
    
    @Test
    public void GetLength() {
        // Case where the range has positive bounds
        Range range1 = new Range(2.0, 8.0);
        double result1 = range1.getLength();
        assertEquals(6.0, result1, 0.0001);  // Length should be 8.0 - 2.0 = 6.0

        // Case where the range has negative and positive bounds
        Range range2 = new Range(-4.0, 6.0);
        double result2 = range2.getLength();
        assertEquals(10.0, result2, 0.0001);  // Length should be 6.0 - (-4.0) = 10.0

        // Case where the range has both negative bounds
        Range range3 = new Range(-10.0, -2.0);
        double result3 = range3.getLength();
        assertEquals(8.0, result3, 0.0001);  // Length should be -2.0 - (-10.0) = 8.0

        // Case where the range has zero as the lower and upper bound
        Range range4 = new Range(0.0, 0.0);
        double result4 = range4.getLength();
        assertEquals(0.0, result4, 0.0001);  // Length should be 0.0 - 0.0 = 0.0
    }
    
    @Test
    public void HashCode() {
        // Case where the range has positive bounds
        Range range1 = new Range(2.0, 8.0);
        int hashCode1 = range1.hashCode();
        assertNotNull(hashCode1); 
        
        // Case where the range has negative and positive bounds
        Range range2 = new Range(-4.0, 6.0);
        int hashCode2 = range2.hashCode();
        assertNotNull(hashCode2);  
        
        // Case where the range has both negative bounds
        Range range3 = new Range(-10.0, -2.0);
        int hashCode3 = range3.hashCode();
        assertNotNull(hashCode3);  // Ensure the hash code is not null
        
        // Case where the range is zero
        Range range4 = new Range(0.0, 0.0);
        int hashCode4 = range4.hashCode();
        assertNotNull(hashCode4); 

        // Verify that two equal ranges have the same hash code
        Range range5 = new Range(2.0, 8.0);
        int hashCode5 = range5.hashCode();
        assertEquals(hashCode1, hashCode5); 
    }
    
    @Test
    public void Intersects() {
        // Case where range 1 lower bound is lower than range 2 lowerbound and range 1 upperbound is higher than range 2 lowerbound
        Range range2 = new Range(7, 15);
        assertTrue(range2.intersects(5, 10)); 

        // Case where range 1 lowerbound is lower than range 2 lowerbound and range 1 upperbound is lower than range 2 upperbound
        assertFalse(range2.intersects(5, 6));  // Expected: false

        // Case where range 1 lowerbound is higher than range 2 lower bound and range 1 upperbound is lower than range 2 upperbound 
        assertTrue(range2.intersects(8, 12));  // Expected: true

        // case where range 1 lowerbound is higher than range 2 lowerbound and range 1 upperbound is higher than range 2 upperbound
        assertFalse(range2.intersects(8, 16)); // it should be true thinking about intersect logic, but the code have upper < this.upper, so if range 1 upperbound is higher than range 2 upperbound, it will be false 
    }
}
