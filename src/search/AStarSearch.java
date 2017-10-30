package search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class AStarSearch extends SearchMethod {
	/**
	 * Set path length to 1 as the distance between two connected point is
	 * always 1.
	 */
//	public static final int PATH_LENGTH = 1;

	/**
	 * A* Search
	 * 
	 * @param map
	 *            The map to search
	 * @param start
	 *            The start node
	 * @param goal
	 *            The target node
	 */
	public static void AStar(char[][] map, Node start, Node goal) {
		// Set comparator for priority queue
		// f(n) = Euclidian distance + path cost
		Comparator<Node> cmp = new Comparator<Node>() {
			public int compare(Node n1, Node n2) {
				if (n1.path_cost + n1.EuclidianDis(goal) > n2.path_cost + n2.EuclidianDis(goal)) {
					return 1;
				}
				if (n1.path_cost + n1.EuclidianDis(goal) < n2.path_cost + n2.EuclidianDis(goal)) {
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
		start.path_cost = 0;
		frontier.add(start);
		explored.add(start);
		// Search from frontier
		while (!frontier.isEmpty()) {
			Node current = frontier.remove();
			// Check if reach the goal
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
				findChild(current, map, childs, explored);
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
