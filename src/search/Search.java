package search;

/**
 * CS5011-A2: Search-Rescue Simulation.
 * 
 * @author bl41
 *
 */
public class Search {

	public static Node start = null;
	public static Node bob = null;
	public static Node goal = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
//			int map_num = Integer.valueOf(args[0]);
//			char[][] map = Map.getMap(1);
			char[][] map = new char[][] {
				{ 'I', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O' },
				{ 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O' },
				{ 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O' },
				{ 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'X', 'O' },
				{ 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'O', 'O' },
				{ 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O' },
				{ 'O', 'O', 'X', 'X', 'O', 'X', 'O', 'O', 'O', 'O' },
				{ 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'B', 'O' },
				{ 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O' },
				{ 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'G' }
			};
//			char[][] map = new char[][] {
//				{'I', 'O', 'O', 'O'},
//				{'O', 'B', 'O', 'O'},
//				{'O', 'O', 'O', 'O'},
//				{'O', 'O', 'X', 'G'}
//			};
//			String search_algorithm = args[1];
			// Get map information
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					if (map[i][j] == 'I') {
						start = new Node(i, j, map[i][j]);
					}
					if (map[i][j] == 'B') {
						bob = new Node(i, j, map[i][j]);
					}
					if (map[i][j] == 'G') {
						goal = new Node(i, j, map[i][j]);
					}
				}
			}
			// Find Bob
			BestFirstSearch.BestFS(map, start, bob);
			// Find Goal
			BestFirstSearch.BestFS(map, bob, goal);
			// Find Bob
			AStarSearch.AStar(map, start, bob);
			// Find Goal
			AStarSearch.AStar(map, bob, goal);
			// Search
//			switch(search_algorithm){
//				default:
//					System.out.println("Usage: java -jar Search1.jar map_number(1 - 6) search_Algorithm(BestFS or A*)");
//					break;
//				case "BestFS":
//					// Find Bob
//					BestFirstSearch.BestFS(map, start, bob);
//					// Find Goal
//					BestFirstSearch.BestFS(map, bob, goal);
//					break;
//				case "A*":
//					// Find Bob
//					DFS.DFSearch(map, start, bob);
//					// Find Goal
//					DFS.DFSearch(map, bob, goal);
//					break;
//			}
		} catch (Exception e) {
			System.out.println("Usage: java -jar Search1.jar map_number(1 - 6) search_Algorithm(BestFS or A*)");
		}
	}

}
