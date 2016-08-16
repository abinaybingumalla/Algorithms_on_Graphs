import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bipartite {
	static int det[];

	private static int bipartite(ArrayList<Integer>[] adj) {

		Queue<Integer> q = new LinkedList();

		q.add(0);
		det[0] = 0;

		while (!q.isEmpty()) {
			int temp = q.remove();
			for (int i = 0; i < adj[temp].size(); i++) {

				if (det[adj[temp].get(i)] == Integer.MAX_VALUE) {

					if (det[temp] == 0) {
						det[adj[temp].get(i)] = 1;
					} else {
						det[adj[temp].get(i)] = 0;
					}

					q.add(adj[temp].get(i));

				} else if (det[temp] == det[adj[temp].get(i)]) {
					return 0;
				}

			}
		}

		return 1;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		det = new int[n];
		ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
		for (int i = 0; i < n; i++) {
			adj[i] = new ArrayList<Integer>();
			det[i] = Integer.MAX_VALUE;
		}
		for (int i = 0; i < m; i++) {
			int x, y;
			x = scanner.nextInt();
			y = scanner.nextInt();
			adj[x - 1].add(y - 1);
			adj[y - 1].add(x - 1);
		}
		System.out.println(bipartite(adj));
		/*for (int i = 0; i < det.length; i++) {
			System.out.print(det[i] + " ");
		}*/
	}
}
