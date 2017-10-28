package search;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class SearchMethod {
    /**
     * Check if node has been explored.
     * 
     * @param check
     *            A list of explored nodes
     * @param node
     *            The node to check
     * @return Return true if the node has not been explored
     */
    public static boolean notExplored(ArrayList<Node> check, Node node) {
        for (int i = 0; i < check.size(); i++) {
            Node n = check.get(i);
            if (Arrays.equals(n.getLocation(), node.getLocation())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if node has been given to a parent.
     * 
     * @param check
     *            A list of nodes that have been given to a parent
     * @param node
     *            The node to check
     * @return Return true if the node has not been given to a parent
     */
    public static boolean existChild(ArrayList<Node> check, Node node) {
        for (int i = 0; i < check.size(); i++) {
            Node n = check.get(i);
            if (Arrays.equals(n.getLocation(), node.getLocation())) {
                return false;
            }
        }
        return true;
    }

}
