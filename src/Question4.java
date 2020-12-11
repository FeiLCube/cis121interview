import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <h1>QUESTION 4: AIRPORT CODE GAME -- OFF BY ONE!</h1>
 * <p/>
 *
 * Class to implement the algorithm to find the minimum number of hops a bunny rabbit
 * must make to navigate from the bottom right corner to the top left corner of the city.
 * <p/>
 *
 * See description of problem and example in the write-up.
 *
 * @author bursztyn
 */
public class Question4 {
    
    private static class Node {
        String code;
        Set<Node> neighbors = new HashSet<>();
        Node parent;
        
        private Node(String code) {
            this.code = code;
        }
        
        private void addNeighbor(Node node) {
            neighbors.add(node);
        }
    }

    /**
     * Solves the "Airport Code Change" game. See complete problem description above.
     * <p/>
     * 
     * You may assume that all codes contain only UPPERCASE alphabetic (A-Z) characters.
     *
     * @param code1 the 3 letter airport code you want to start from
     * @param code2 the 3 letter airport code you want to end at
     * @param codes the set of 3 letter airport codes you can use to get from code1 -> code2
     * @return the smallest chain of airport codes to get from {@code code1 -> code2}. {@code code1}
     *         should be the first element, and {@code code2} should be the last. If no valid
     *         solution exists, return {@code null}. If {@code code1.equals(code2)}, return an empty
     *         list.
     *
     * @implSpec you may assume that all inputs are valid. Do not throw any exceptions.
     */
    public static List<String> getSmallestChain(String code1, String code2, Set<String> codes) {
        
        List<String> soln = new ArrayList<>();
        if (code1.equals(code2)) {
            return soln;
        }
        Set<Node> nodes = new HashSet<>();
        
        Node srcNode = new Node(code1);
        Node tgtNode = new Node(code2);
        nodes.add(srcNode);
        nodes.add(tgtNode);
        codes.remove(code1);
        codes.remove(code2);
        
        for (String code : codes) {
            Node node = new Node(code);
            nodes.add(node);
        }
        
        // Construct tree
        for (Node node : nodes) {
            for (Node otherNode : nodes) {
                if (node.code.equals(otherNode.code)) {
                    continue;
                }
                
                char[] arr1 = node.code.toCharArray();
                char[] arr2 = otherNode.code.toCharArray();
                int lettersInCommon = 0;
                
                for (int i = 0; i < arr1.length; i++) {
                    if (arr1[i] == arr2[i]) {
                        lettersInCommon++;
                    }
                }
                
                if (lettersInCommon >= 2) {
                    node.addNeighbor(otherNode);
                }
            }
        }
        
        Set<Node>[] layers = bfs(nodes, srcNode, tgtNode);
        
        // Start at target node and work backwards to source node via parent.
        soln.add(srcNode.code);
        
        if (srcNode.code.equals(tgtNode.code)) {
            return soln;
        }
        
        while (srcNode.parent != null) {
            srcNode = srcNode.parent;
            soln.add(srcNode.code);
            if (tgtNode.code.equals(srcNode.code)) {
                return soln;
            }
        }
        
        return null;
    }
    
    private static Set<Node>[] bfs(Set<Node> nodes, Node srcNode, Node tgtNode) {
        Set<Node>[] layers = new HashSet[nodes.size()];
        for (int i = 0; i < layers.length; i++) {
            layers[i] = new HashSet<>();
        }
        layers[0].add(tgtNode);
        nodes.remove(tgtNode);
        
        int i = 0;
        
        while (!layers[i].isEmpty()) {
            
            for (Node node : layers[i]) {
                for (Node neighbor : node.neighbors) {
                    if (nodes.contains(neighbor)) {
                        nodes.remove(neighbor);
                        neighbor.parent = node;
                        layers[i + 1].add(neighbor);
                    }
                }
            }
            
            i++;
        }
        
        return layers;
    }
}