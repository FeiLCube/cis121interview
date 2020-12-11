import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Question1Test {
	
	@Test
	public void testGivenCase() {
		int numFlights = 4;
		int[][] conditions = {
		    {3, 2},
		    {1, 2},
		    {0, 3},
		    {0, 1}
		};
		
		Object[] expected = {2, 3, 1, 0};
		
		Object[] result = Question1.getFlightDepartureSchedule(numFlights, conditions).toArray();
		
		assertArrayEquals(expected, result);
	}
	
	@Test
	public void testEmptyList() {
	    Object[] result = Question1.getFlightDepartureSchedule(0, new int[0][0]).toArray();
	    
	    assertTrue(result.length == 0);
	}
	
	@Test
	public void testNoSchedulePossible() {
	    int[][] conditions = {
	        {0, 1},
	        {1, 2},
	        {2, 3},
	        {3, 0}
	    };
	    
	    Object[] result = Question1.getFlightDepartureSchedule(4, conditions).toArray();
	    
	    assertTrue(result.length == 0);
	}
	
	@Test
	public void testNoSchedulePossibleStronglyConnectedComponent() {
	    int[][] conditions = {
            {0, 1},
	        {1, 2},
	        {2, 3},
	        {3, 0}
	    };
	        
	    Object[] result = Question1.getFlightDepartureSchedule(6, conditions).toArray();
        assertTrue(result.length == 0);
	}
	
	@Test
	public void testOneFlight() {
	    Object[] result = Question1.getFlightDepartureSchedule(1, new int[0][0]).toArray();
        assertTrue(result.length == 1);
        assertEquals(0, result[0]);
	}
	
	@Test
	public void testCertainCase() {
	    int[][] conditions = {
	        {1, 0},
	        {2, 1},
	        {3, 2},
	        {0, 5},
	        {4, 5}
	    };
	    
	    Object[] result = Question1.getFlightDepartureSchedule(7, conditions).toArray();
	    for (int i = 0; i < result.length; i++) {
	        System.out.println(result[i]);
	    }
	}
}