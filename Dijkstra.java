import java.util.*;

public class Dijkstra {

	static int dist[];
	static boolean processed[];
	static PriorityQueue<Vertex> q;

	private static int distance(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {

		

		/*for (int i = 0; i < cost.length; i++) {
			q.add(new Vertex(dist[i], i));
		}*/

		while (!q.isEmpty()) {
			Vertex current = q.remove();

			if (!processed[current.val]) {
				processed[current.val] = true;
				for (int i = 0; i < adj[current.val].size(); i++) {
					int v = adj[current.val].get(i);
					int w = cost[current.val].get(i);
					// System.out.println(current.val+" "+v+" "+w);
					int temp = dist[current.val]+w;
					if (temp >= 0 && dist[v] > temp) {
						dist[v] = dist[current.val] + w;
						q.add(new Vertex(dist[v], v));
					}

				}
			}

		}

		if (dist[t] != Integer.MAX_VALUE) {
			return dist[t];
		} else {
			return -1;
		}

	}

	public static int findWeight(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int u, int v) {

		for (int i = 0; i < adj[u].size(); i++) {
			if (adj[u].get(i) == v) {
				return cost[u].get(i);
			}
		}

		return 0;
	}

	static class Vertex {
		int cost;
		int val;

		public Vertex(int cost, int val) {
			super();
			this.cost = cost;
			this.val = val;
		}

	}

	static class comp implements Comparator<Vertex> {

		@Override
		public int compare(Vertex o1, Vertex o2) {
			return o1.cost - o2.cost;
		}

	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		dist = new int[n];
		processed = new boolean[n];
		q = new PriorityQueue<>(n+1, new comp());
		ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
		ArrayList<Integer>[] cost = (ArrayList<Integer>[]) new ArrayList[n];
		for (int i = 0; i < n; i++) {
			adj[i] = new ArrayList<Integer>();
			cost[i] = new ArrayList<Integer>();
			dist[i] = Integer.MAX_VALUE;
			processed[i] = false;
		}
		for (int i = 0; i < m; i++) {
			int x, y, w;
			x = scanner.nextInt();
			y = scanner.nextInt();
			w = scanner.nextInt();
			adj[x - 1].add(y - 1);
			cost[x - 1].add(w);
			q.add(new Vertex(dist[x-1], x-1));
		}
		int x = scanner.nextInt() - 1;
		int y = scanner.nextInt() - 1;
		
		dist[x] = 0;
		q.add(new Vertex(dist[x], x));
		System.out.println(distance(adj, cost, x, y));

	}

	public static void print() {
		for (int i = 0; i < dist.length; i++) {
			System.out.print(dist[i] + " ");

		}
		System.out.println();

	}

	public static void process() {
		for (int i = 0; i < dist.length; i++) {
			System.out.print(processed[i] + " ");
		}
		System.out.println();
	}
}
