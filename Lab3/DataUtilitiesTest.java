package org.jfree.data;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Assert;

public class DataUtilitiesTest {

    private DefaultKeyedValues2D table;

    @Before
    public void resetTable() {
        table = new DefaultKeyedValues2D(); // reset table for each test
    }
    
    //calculate column correcvtly
    @Test
    public void calcColCorrectly() {
        table.addValue(2.0, 0, 0);
        table.addValue(3.0, 1, 0);
        table.addValue(5.0, 2, 0);

        // Should add to 10
        assertEquals("Total should be 10", 10.0, DataUtilities.calculateColumnTotal(table, 0), 0.001);
    }

    private void assertEquals(String string, double d, double calculateColumnTotal, double e) {
		// TODO Auto-generated method stub
		
	}
    
    // verifies correctly skips null values
	@Test
    public void columnTotal_skipsNulls() {
        table.addValue(null, 0, 0);
        table.addValue(4.0, 1, 0);
        assertEquals(4.0, DataUtilities.calculateColumnTotal(table, 0), 0.001); // just the 4.0
    }

    
	//calculate row correctly 
    @Test
    public void rowTotal_acrossTwoColumns() {
        table.addValue(1.0, 0, 0);
        table.addValue(4.0, 0, 1);

        
        assertEquals(5.0, DataUtilities.calculateRowTotal(table, 0), 0.001);
    }

    @Test
    public void rowTotal_ignoresNulls() {
        table.addValue(null, 0, 0);
        table.addValue(7.0, 0, 1);

        assertEquals(7.0, DataUtilities.calculateRowTotal(table, 0), 0.001);
    }
    // converts an array of primitive double values
    @Test
    public void createNumberArray_convertsCorrectly() {
        double[] sample = {1.1, 2.2, 3.3};
        Number[] result = DataUtilities.createNumberArray(sample);

        Assert.assertEquals(3, result.length);
        assertEquals(3.3, result[2], 0.0009); // slightly different delta
    }
    
    
    private void assertEquals(double d, Number number, double e) {
		// TODO Auto-generated method stub
		
	}
    //check throws an illegalargumentexception
	@Test
    public void createNumberArray_throwsWhenNull() {
        try {
            DataUtilities.createNumberArray(null);
            fail("Expected IllegalArgumentException when passing null to createNumberArray");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }
	
	//test conversion of nested double array to be nester number array
    @Test
    public void createNumberArray2D_handlesNestedArray() {
        double[][] data = {
            {1.0, 2.0},
            {3.0, 4.0}
        };
        double unused = 99.9; 

        Number[][] result = DataUtilities.createNumberArray2D(data);

        assertEquals(Double.valueOf(4.0), result[1][1]); // bottom right value
    }

    private void assertEquals(Double valueOf, Number number) {
		
		
	}
    //check throws an illegalargumentexception when null 2d array
	@Test
    public void createNumberArray2D_throwsOnNull() {
        try {
            DataUtilities.createNumberArray2D(null);
            fail("Expected exception on null input for 2D array");
        } catch (IllegalArgumentException e) {
           
        }
    }
	// verify test correctly calculate the cumulative percentages
	 @Test
	    public void testCumulativePercentages() {
	        DefaultKeyedValues values = new DefaultKeyedValues();
	        values.addValue("A", 2.0);
	        values.addValue("B", 3.0);
	        values.addValue("C", 5.0);

	        KeyedValues result = DataUtilities.getCumulativePercentages(values);
	        assertEquals("First value should be 20%", 0.2, result.getValue("A").doubleValue(), 0.01);
	        assertEquals("Second value should be 50%", 0.5, result.getValue("B").doubleValue(), 0.01);
	        assertEquals("Third value should be 100%", 1.0, result.getValue("C").doubleValue(), 0.01);
	    }

	    //test to verify behavior when all values are zero
	    @Test
	    public void testCumulativePercentagesAllZeros() {
	        DefaultKeyedValues values = new DefaultKeyedValues();
	        values.addValue("A", 0.0);
	        values.addValue("B", 0.0);
	        values.addValue("C", 0.0);

	        KeyedValues result = DataUtilities.getCumulativePercentages(values);
	        assertEquals("Should handle zero without exception", 0.0, result.getValue("A").doubleValue(), 0.01);
	        assertEquals("Should handle zero without exception", 0.0, result.getValue("B").doubleValue(), 0.01);
	        assertEquals("Should handle zero without exception", 0.0, result.getValue("C").doubleValue(), 0.01);
	    }

	    //  Test to verify correct handling of null input
	    @Test(expected = IllegalArgumentException.class)
	    public void testCumulativePercentagesWithNullInput() {
	        DataUtilities.getCumulativePercentages(null);
	    }

	    //test to verify behavior when the input contains null values
	    @Test
	    public void testCumulativePercentagesWithNullValues() {
	        DefaultKeyedValues values = new DefaultKeyedValues();
	        values.addValue("A", null);
	        values.addValue("B", 3.0);
	        values.addValue("C", 2.0);

	        KeyedValues result = DataUtilities.getCumulativePercentages(values);
	        assertEquals("Should treat null as zero", 0.0, result.getValue("A").doubleValue(), 0.01);
	        assertEquals("Should correctly compute cumulative percentage", 0.6, result.getValue("B").doubleValue(), 0.01);
	        assertEquals("Should correctly compute cumulative percentage", 1.0, result.getValue("C").doubleValue(), 0.01);
	    }

}

//zeus
