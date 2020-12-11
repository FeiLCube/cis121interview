import static org.junit.Assert.assertNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class Question4Test {
    
    @Test
    public void testGivenCase() {
        String code1 = "MIA";
        String code2 = "BLE";
        Set<String> codes = new HashSet<>();
        codes.add(code1);
        codes.add(code2);
        codes.add("JFK");
        codes.add("MLE");
        codes.add("MLA");
        
        List<String> result = Question4.getSmallestChain(code1, code2, codes);
        for (String s : result) {
            System.out.println(s);
        }
    }
    
    @Test
    public void testSameCode() {
        String code1 = "MIA";
        String code2 = "MIA";
        Set<String> codes = new HashSet<>();
        codes.add(code1);
        codes.add(code2);
        codes.add("JFK");
        codes.add("MLE");
        codes.add("MLA");
        
        List<String> result = Question4.getSmallestChain(code1, code2, codes);
        for (String s : result) {
            System.out.println(s);
        }
    }
    
    @Test
    public void testNoSoln() {
        String code1 = "MIA";
        String code2 = "JFK";
        Set<String> codes = new HashSet<>();
        codes.add(code1);
        codes.add(code2);
        codes.add("JFK");
        codes.add("MLE");
        codes.add("MLA");
        
        List<String> result = Question4.getSmallestChain(code1, code2, codes);
        assertNull(result);
    }
    
    @Test
    public void testMultipleSolns() {
        String code1 = "MIA";
        String code2 = "BLE";
        Set<String> codes = new HashSet<>();
        codes.add(code1);
        codes.add(code2);
        codes.add("JFK");
        codes.add("MLE");
        codes.add("MLA");
        codes.add("MIM");
        codes.add("MUM");
        codes.add("MUE");
        
        List<String> result = Question4.getSmallestChain(code1, code2, codes);
        
        for (String s : result) {
            System.out.println(s);
        }
    }
}
