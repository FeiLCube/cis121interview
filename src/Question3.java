import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <h1>QUESTION 3: BUNNY HOP</h1>
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
public class Question3 {
    
    static class Node {
        int value;
        Set<Node> parents = new HashSet<>();
        
        private Node(int value) {
            this.value = value;
        }
        
        private void addParent(Node parent) {
            parents.add(parent);
        }
    }
    
    private static int shortestPath = Integer.MAX_VALUE;

    /**
     * Returns the minimum number of hops needed for the bunny rabbit to make its way from the
     * bottom right corner to the top left corner given the hopping constraints in the writeup,
     * or -1 if a path does not exist.
     *
     * @param city an n x n 2d-array representing the city where each entry is a positive integer
     *             which defines how many cells the bunny can hop up or to the left when that
     *             entry is reached.
     * @return the minimum number of hops the bunny needs to make, or -1 if there is no 
     *         possible path
     * 
     * @implSpec you may assume that input is valid and non-null. Do not throw any exceptions.
     */
    public static int getMinimumNumberOfHops(int[][] city) {
        
        if (city.length == 0) {
            return -1;
        } else if (city[0].length == 0) {
            return -1;
        }
        
        shortestPath = Integer.MAX_VALUE;
        
        Map<Integer, Node> nodes = new HashMap<>();
        
        for (int i = 0; i < city.length; i++) {
            for (int j = 0; j < city[i].length; j++) {
                int value = (i * city.length) + j; 
                Node node = new Node(value);
                nodes.put(value, node);
                
                if (i - (city[i][j]) >= 0) {
                    int upValue = ((i - city[i][j]) * city.length) + j;
                    Node upNode = nodes.get(upValue);
                    upNode.addParent(node);
                }
                
                if (j - (city[i][j]) >= 0) {
                    int leftValue = (i * city.length) + j - city[i][j];
                    Node leftNode = nodes.get(leftValue);
                    leftNode.addParent(node);
                }
            }
        }
        
        Node tgtNode = nodes.get(0);
        
        int srcNodeValue = (city.length) * (city[0].length) - 1;
        
        findSrcNode(tgtNode, 0, srcNodeValue);
        
        if (shortestPath == Integer.MAX_VALUE) {
            return -1;
        } else {
            return shortestPath;   
        }
    }
    
    // Recursive helper function that traverses a graph through the current node's parents.
    // Returns the shortest hops from the source to the target.
    private static void findSrcNode(Node node, int hops, int srcValue) {
        // Base case
        if (node.value == srcValue) {
            if (hops < shortestPath) {
                shortestPath = hops;
                return;
            }
        }
        
        if (!node.parents.isEmpty()) {
            for (Node parent : node.parents) {
                findSrcNode(parent, hops + 1, srcValue);
            }
        }
    }
}