package search;
/**
 * CS5011-A2: Search-Rescue Simulation.
 * 
 * @author bl41
 *
 */

import java.util.LinkedList;

public class Node {
    // public char status;
    private int x;
    private int y;
    public char value;
    Node uChild;
    Node dChild;
    Node lChild;
    Node rChild;
    Node parent;

    // Node constructor
    public Node(int x, int y, char value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    // Get value of node
    public char getValue() {
        return value;
    }

    // Get x coordinate of node
    public int getX() {
        return x;
    }

    // Get y coordinate of node
    public int getY() {
        return y;
    }

    public Node getParent() {
        return parent;
    }

    // Get location of node
    public int[] getLocation() {
        int[] location = { x, y };
        return location;
    }

    /*
     * Get the child nodes
     * Make an order of down, right, up and left
     */
    public LinkedList<Node> getChildren() {
        LinkedList<Node> childNodes = new LinkedList<>();
        if (this.dChild != null) {
            childNodes.add(dChild);
        }
        if (this.rChild != null) {
            childNodes.add(rChild);
        }
        if (this.uChild != null) {
            childNodes.add(uChild);
        }
        if (this.lChild != null) {
            childNodes.add(lChild);
        }
        return childNodes;
    }
}
