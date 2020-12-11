import java.util.Set;

import org.junit.Test;

public class Question2Test {
    
    @Test
    public void testGivenCase() {
        String[][] test = {
            {"Steven", "Will"},
            {"Helen", "Caroline"},
            {"Caroline", "Monal"},
            {"Will", "Steven"}
        };
        Set<Pair<String, String>> result = Question2.findMatches(test);
        
        for (Pair<String, String> pair : result) {
            System.out.println(pair.key + " " + pair.value);
        }
    }
    
    @Test
    public void testNoPairs() {
        String[][] test = {
            {"Steven", "Will"},
            {"Will", "Caroline"},
            {"Caroline", "Monal"},
            {"Monal", "Steven"}
        };
        Set<Pair<String, String>> result = Question2.findMatches(test);
        
        for (Pair<String, String> pair : result) {
            System.out.println(pair.key + " " + pair.value);
        }
    }
    
    @Test
    public void testPairMultiplePairs() {
        String[][] test = {
            {"Steven", "Will"},
            {"Monal", "Caroline"},
            {"Caroline", "Monal"},
            {"Will", "Steven"}
        };
        Set<Pair<String, String>> result = Question2.findMatches(test);
        
        for (Pair<String, String> pair : result) {
            System.out.println(pair.key + " " + pair.value);
        }
    }
    
    @Test
    public void testOnePair() {
        String[][] test = {
            {"Steven", "Will"},
            {"Will", "Steven"}
        };
        Set<Pair<String, String>> result = Question2.findMatches(test);
            
        for (Pair<String, String> pair : result) {
            System.out.println(pair.key + " " + pair.value);
        }
    }
    
    @Test
    public void testOneRequestOnly() {
        String[][] test = {
            {"Steven", "Will"}
        };
        Set<Pair<String, String>> result = Question2.findMatches(test);
            
        for (Pair<String, String> pair : result) {
            System.out.println(pair.key + " " + pair.value);
        }
    }
}
