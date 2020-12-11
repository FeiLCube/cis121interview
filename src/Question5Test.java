import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class Question5Test {
    
    @Test
    public void testGivenCase() {
        List<Integer> node0 = new ArrayList<>();
        List<Integer> node1 = new ArrayList<>();
        List<Integer> node2 = new ArrayList<>();
        List<Integer> node3 = new ArrayList<>();
        List<Integer> node4 = new ArrayList<>();
        List<Integer> node5 = new ArrayList<>();
        List<Integer> node6 = new ArrayList<>();
        node0.add(1);
        node0.add(3);
        node1.add(0);
        node1.add(2);
        node1.add(4);
        node1.add(5);
        node3.add(0);
        node3.add(6);
        node6.add(3);
        node2.add(1);
        node4.add(1);
        node5.add(1);
        List<List<Integer>> tree = new ArrayList<>();
        tree.add(node0);
        tree.add(node1);
        tree.add(node2);
        tree.add(node3);
        tree.add(node4);
        tree.add(node5);
        tree.add(node6);
        
        int result = Question5.getNumberOfOddSubtrees(tree, 0);
        assertEquals(5, result);
    }
    
    @Test
    public void testSingleLeaf() {
        List<Integer> node = new ArrayList<>();
        node.add(0);
        List<List<Integer>> singleton = new ArrayList<>();
        singleton.add(node);
        
        int result = Question5.getNumberOfOddSubtrees(singleton, 0);
        assertEquals(1, result);
    }
}
