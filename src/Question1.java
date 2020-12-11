import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h1>QUESTION 1: FLIGHT SCHEDULES</h1>
 * <p/>
 *
 * Class to implement the algorithm to determine a possible flight schedule given the conditions.
 * <p/>
 *
 * See description of problem and example in the write-up.
 *
 * @author bursztyn
 */
public class Question1 {
    
    private static class Node {
        boolean discovered = false;
        boolean finished = false;
        int indegree = 0;
        int finishTime;
        int flightNumber;
        Node parent;
        List<Node> neighbors = new ArrayList<>();
        
        private Node(int flightNum) {
            flightNumber = flightNum;
        }
        
        private void addNeighbor(Node neighbor) {
            neighbors.add(neighbor);
        }
    }
    
    private static int time = 0;
    private static boolean possibleSchedule = true;
    private static List<Node> reverseSchedule = new ArrayList<>();

    /**
     * Given the number of flights to schedule and conditions for departure, returns one possible
     * departure schedule.
     *
     * @param numFlights the number of flights waiting to take off
     * @param conditions the list of condition pairs (no duplicate pairs may exist)
     * @return one possible valid flight departure ordering, or an empty list if none exists
     * 
     * @implSpec you may assume that input is valid (well formatted, mx2 2d-array) and non-null.
     * Do not throw any exceptions.
     */
    public static List<Integer> getFlightDepartureSchedule(int numFlights, int[][] conditions) {
        
        time = 0;
        possibleSchedule = true;
        reverseSchedule = new ArrayList<>();
        List<Integer> soln = new ArrayList<>();
        Map<Integer, Node> nodes = new HashMap<>();
        
        for (int i = 0; i < numFlights; i++) {
            Node node = new Node(i);
            nodes.put(i, node);
        }
        
        for (int i = 0; i < conditions.length; i++) {
            int source = conditions[i][1];
            int target = conditions[i][0];
            Node srcNode = nodes.get(source);
            Node tgtNode = nodes.get(target);
            
            srcNode.addNeighbor(tgtNode);
            tgtNode.indegree++;
        }
        
        // DFS Implementation
        List<Node> orderFinished = new ArrayList<>();
        
        for (int i = 0; i < numFlights; i++) {
            reverseSchedule.clear();
            Node node = nodes.get(i);
            if (!node.discovered) {
                dfs(nodes, node);
                for (Node n : reverseSchedule) {
                    orderFinished.add(n);
                }
            }
        }
        
        for (int i = orderFinished.size() - 1; i >= 0; i--) {
            soln.add(orderFinished.get(i).flightNumber);
        }
        
        if (possibleSchedule) {
            return soln;
        } else {
            return new ArrayList<Integer>();
        }
    }
    
    private static void dfs(Map<Integer, Node> nodes, Node rootNode) {
        rootNode.discovered = true;
        for (Node node : rootNode.neighbors) {
            if (!node.discovered) {
                node.parent = rootNode;
                dfs(nodes, node);
            } else if (node.discovered && !node.finished) {
                // A back edge has been found, and no flight schedule is possible.
                possibleSchedule = false;
            }
        }
        rootNode.finished = true;
        rootNode.finishTime = time;
        time++;
        reverseSchedule.add(rootNode);
    }
}
