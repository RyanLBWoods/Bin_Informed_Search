package search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BestFirstSearch extends SearchMethod {
	/**
	 * Best-first algorithm
	 * 
	 * @param map
	 *            The map to search
	 * @param start
	 *            The start node
	 * @param goal
	 *            The target node
	 */
	public static void BestFS(char[][] map, Node start, Node goal) {
		// Set comparator for priority queue
		// f(n) = Manhattan Distance
		Comparator<Node> cmp = new Comparator<Node>() {
			public int compare(Node n1, Node n2) {
				if (n1.ManhattanDis(goal) > n2.ManhattanDis(goal)) {
					return 1;
				} else if (n1.ManhattanDis(goal) < n2.ManhattanDis(goal)) {
					return -1;
				}
				return 0;
			}
		};

		PriorityQueue<Node> frontier = new PriorityQueue<Node>(cmp);
		// Record nodes that have already become a child
		ArrayList<Node> explored = new ArrayList<>();
		// Record nodes that have been explored
		ArrayList<Node> childs = new ArrayList<>();
		// Initialise process
		start.parent = start;
		frontier.add(start);
		explored.add(start);

		while (!frontier.isEmpty()) {
			Node current = frontier.remove();
			if (current.getValue() == goal.getValue()) {
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
				break;
			} else {
				// Get child nodes
				// Give the potential child nodes
				// Check if the potential child node is the parent node or has
				// been explored
				// If so, get rid of that child node
				if (current.getX() - 1 >= 0 && map[current.getX() - 1][current.getY()] != 'X') {
					current.uChild = new Node(current.getX() - 1, current.getY(),
							map[current.getX() - 1][current.getY()]);
					if (Arrays.equals(current.uChild.getLocation(), current.parent.getLocation())
							|| !existChild(childs, current.uChild) || !notExplored(explored, current.uChild)) {
						current.uChild = null;
					} else {
						current.uChild.parent = current;
					}
				}
				if (current.getX() + 1 < 10 && map[current.getX() + 1][current.getY()] != 'X') {
					current.dChild = new Node(current.getX() + 1, current.getY(),
							map[current.getX() + 1][current.getY()]);
					if (Arrays.equals(current.dChild.getLocation(), current.parent.getLocation())
							|| !existChild(childs, current.dChild) || !notExplored(explored, current.dChild)) {
						current.dChild = null;
					} else {
						current.dChild.parent = current;
					}
				}
				if (current.getY() - 1 >= 0 && map[current.getX()][current.getY() - 1] != 'X') {
					current.lChild = new Node(current.getX(), current.getY() - 1,
							map[current.getX()][current.getY() - 1]);
					if (Arrays.equals(current.lChild.getLocation(), current.parent.getLocation())
							|| !existChild(childs, current.lChild) || !notExplored(explored, current.lChild)) {
						current.lChild = null;
					} else {
						current.lChild.parent = current;
					}
				}
				if (current.getY() + 1 < 10 && map[current.getX()][current.getY() + 1] != 'X') {
					current.rChild = new Node(current.getX(), current.getY() + 1,
							map[current.getX()][current.getY() + 1]);
					if (Arrays.equals(current.rChild.getLocation(), current.parent.getLocation())
							|| !existChild(childs, current.rChild) || !notExplored(explored, current.rChild)) {
						current.rChild = null;
					} else {
						current.rChild.parent = current;
					}
				}
				// Insert nodes to frontier
				if (!current.getChildren().isEmpty()) {
					frontier.addAll(current.getChildren());
					childs.addAll(current.getChildren());
				}
				explored.add(current);
			}
		}
	}
}
