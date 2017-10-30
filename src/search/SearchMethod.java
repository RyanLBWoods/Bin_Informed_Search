package search;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class SearchMethod {
	/**
	 * Set path length to 1 as the distance between two connected point is
	 * always 1.
	 */
	public static final int PATH_LENGTH = 1;

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

	/**
	 * Method to define child nodes for current node.
	 * 
	 * @param current
	 *            Current node
	 * @param map
	 *            Map to search
	 * @param childs
	 *            Array list that stored nodes who has already become a child
	 * @param explored
	 *            Array list that stored nodes who has already been explored
	 */
	public static void findChild(Node current, char[][] map, ArrayList<Node> childs, ArrayList<Node> explored) {
		// Get child nodes and record is path cost
		// Give the potential child nodes
		// Check if the potential child node is the parent node or has
		// been explored
		// If so, get rid of that child node
		if (current.getX() - 1 >= 0 && map[current.getX() - 1][current.getY()] != 'X') {
			current.uChild = new Node(current.getX() - 1, current.getY(), map[current.getX() - 1][current.getY()]);
			if (Arrays.equals(current.uChild.getLocation(), current.parent.getLocation())
					|| !existChild(childs, current.uChild) || !notExplored(explored, current.uChild)) {
				current.uChild = null;
			} else {
				current.uChild.parent = current;
				current.uChild.path_cost = current.parent.path_cost + PATH_LENGTH;
			}
		}
		if (current.getX() + 1 < 10 && map[current.getX() + 1][current.getY()] != 'X') {
			current.dChild = new Node(current.getX() + 1, current.getY(), map[current.getX() + 1][current.getY()]);
			if (Arrays.equals(current.dChild.getLocation(), current.parent.getLocation())
					|| !existChild(childs, current.dChild) || !notExplored(explored, current.dChild)) {
				current.dChild = null;
			} else {
				current.dChild.parent = current;
				current.dChild.path_cost = current.parent.path_cost + PATH_LENGTH;
			}
		}
		if (current.getY() - 1 >= 0 && map[current.getX()][current.getY() - 1] != 'X') {
			current.lChild = new Node(current.getX(), current.getY() - 1, map[current.getX()][current.getY() - 1]);
			if (Arrays.equals(current.lChild.getLocation(), current.parent.getLocation())
					|| !existChild(childs, current.lChild) || !notExplored(explored, current.lChild)) {
				current.lChild = null;
			} else {
				current.lChild.parent = current;
				current.lChild.path_cost = current.parent.path_cost + PATH_LENGTH;
			}
		}
		if (current.getY() + 1 < 10 && map[current.getX()][current.getY() + 1] != 'X') {
			current.rChild = new Node(current.getX(), current.getY() + 1, map[current.getX()][current.getY() + 1]);
			if (Arrays.equals(current.rChild.getLocation(), current.parent.getLocation())
					|| !existChild(childs, current.rChild) || !notExplored(explored, current.rChild)) {
				current.rChild = null;
			} else {
				current.rChild.parent = current;
				current.rChild.path_cost = current.parent.path_cost + PATH_LENGTH;
			}
		}
	}

	/**
	 * Method to print output (path and explored position) of search.
	 * 
	 * @param current
	 *            Current node
	 * @param start
	 *            Start node
	 * @param goal
	 *            Target node
	 * @param explored
	 *            Array list that stored all explored nodes
	 */
	public static void printOutput(Node current, Node start, Node goal, ArrayList<Node> explored) {
		Node path = current;
		ArrayList<int[]> Path = new ArrayList<>();
		Path.add(path.getLocation()); // Get goal position
		// Put path of coordinates in to an array list of output
		while (path.getParent() != null) {
			Path.add(path.parent.getLocation());
			path = path.parent;
			if (Arrays.equals(path.getLocation(), start.getLocation())) {
				break;
			}
		}
		// Print the path of coordinates
		System.out.println("Find path from " + start.getValue() + " to " + goal.getValue());
		for (int i = Path.size() - 1; i >= 0; i--) {
			System.out.print(Arrays.toString(Path.get(i)));
		}
		System.out.println("\nPath length: " + (Path.size() - 1));
		explored.remove(0); // Get rid of first node as it is a
							// redundancy
		// Show explored times
		System.out.println("Explored " + explored.size() + " position");
		for (int j = 0; j < explored.size(); j++) {
			System.out.print(Arrays.toString(explored.get(j).getLocation()));
		}
		System.out.println("\n");
	}
}
