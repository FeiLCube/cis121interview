import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <h1>QUESTION 5: ODD SIZED FAMILY TREES</h1>
 * <p/>
 *
 * Class to implement the algorithm to determine the number of odd sized sub-trees in a given
 * family tree.
 * <p/>
 *
 * See description of problem and example in the write-up.
 *
 * @author bursztyn
 */
public class Question5 {
    
    private static class Node {
        int value;
        int size = 0;
        Node parent;
        
        private Node(int value) {
            this.value = value;
        }
        
        private void setParent(Node parent) {
            this.parent = parent;
        }
    }

    /**
     * Returns the number of odd sized sub-trees in a given family tree.
     *
     * @param family an adjacency list of the family tree
     * @param root the favorite number of the root of the family tree
     * @return the number of odd sized sub-trees
     * 
     * @implSpec you may assume that input is valid and non-null. Do not throw any exceptions.
     */
    public static int getNumberOfOddSubtrees(List<List<Integer>> family, int root) {
        int numOfOddTrees = 0;
        
        // Do BFS
        // Traverse from leaf layers and add the size of the node to the parent node,
        // incrementing numOfOddTrees on the way up.
        Node rootNode = new Node(root);
        List<Set<Node>> depth = bfs(family, rootNode);
        
        for (int i = depth.size() - 1; i >= 0; i--) {
            Set<Node> layer = depth.get(i);
            for (Node node : layer) {
                node.size++;
                if ((node.size % 2) == 1) {
                    numOfOddTrees++;
                }
                if (node.parent != null) {
                    node.parent.size += node.size;
                }
            }
        }
        
        return numOfOddTrees;
    }
    
    private static List<Set<Node>> bfs(List<List<Integer>> adjList, Node rootNode) {
        List<Set<Node>> depth = new ArrayList<>();
        boolean[] discovered = new boolean[adjList.size()];
        
        depth.add(new HashSet<>());
        depth.get(0).add(rootNode);
        discovered[rootNode.value] = true;
        int i = 0;
        
        while (!depth.get(i).isEmpty()) {
            depth.add(new HashSet<>());
            Set<Node> setOfNodes = depth.get(i);
            Set<Node> nextLayer = depth.get(depth.size() - 1);
            
            for (Node node : setOfNodes) {
                List<Integer> neighbors = adjList.get(node.value);
                for (Integer val : neighbors) {
                    if (!discovered[val]) {
                        discovered[val] = true;
                        Node child = new Node(val);
                        child.setParent(node);
                        nextLayer.add(child);
                    }
                }
            }
            
            i++;
        }
        
        return depth;
    }
}