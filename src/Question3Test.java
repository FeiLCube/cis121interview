import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Question3Test {
    
    @Test
    public void testGivenCase() {
        int[][] city = {
            {1, 6, 2},
            {1, 6, 4},
            {1, 9, 2}
        };
        
        int result = Question3.getMinimumNumberOfHops(city);
        
        assertEquals(2, result);
    }
    
    @Test
    public void testEmptyCity() {
        int[][] city = new int[0][0];
        
        int result = Question3.getMinimumNumberOfHops(city);
        
        assertEquals(-1, result);
        
        city = new int[2][0];
        result = Question3.getMinimumNumberOfHops(city);
        assertEquals(-1, result);
    }
    
    @Test
    public void testOneBlockCity() {
        int[][] city = {
            {1}
        };
        
        int result = Question3.getMinimumNumberOfHops(city);
        
        assertEquals(0, result);
    }
    
    @Test
    public void testNoPath() {
        int[][] city = {
            {1, 2, 3},
            {2, 1, 4},
            {1, 1, 1}
        };
        
        int result = Question3.getMinimumNumberOfHops(city);
        
        assertEquals(-1, result);
    }
}
