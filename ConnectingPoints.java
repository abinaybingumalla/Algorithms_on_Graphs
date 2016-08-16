import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class ConnectingPoints {

	static ArrayList<Vertex> list = new ArrayList<>();
	static int parent[];
	static int rank[];
	static int[] x;
	static int[] y;

	private static double minimumDistance(int[] x, int[] y) {
		double result = 0.;
		createEdges(x, y);

		parent = new int[x.length];
		rank = new int[x.length];
		for (int i = 0; i < parent.length; i++) {
			parent[i] = i;
			rank[i] = 0;
		}

		for (int i = 0; i < list.size(); i++) {
			if (Find(list.get(i).from) != Find(list.get(i).to)) {
				result = result + list.get(i).weight;
				Union(list.get(i).from, list.get(i).to);
			}
		}

		/* System.out.println(list);

		 for (int i = 0; i < parent.length; i++) {
			System.out.print(parent[i]+" ");
		}System.out.println();*/
		 
		return result;
	}

	public static int Find(int i) {

		while (i != parent[i]) {
			i = parent[i];
		}
		return i;
	}

	public static void Union(int i, int j) {
		int i_id = Find(i);
		int j_id = Find(j);

		if (i_id == j_id) {
			return;
		}

		if (rank[i_id] > rank[j_id]) {
			parent[j_id] = i_id;
		} else {
			parent[i_id] = j_id;
			if (rank[i_id] == rank[j_id]) {
				rank[j_id] = rank[j_id] + 1;
			}
		}

	}

	static class Vertex {
		int from;
		int to;
		double weight;

		public Vertex(int from, int to, double cost) {
			super();
			this.from = from;
			this.to = to;
			this.weight = cost;
		}

		public String toString() {
			return x[this.from]+" "+y[this.from]+":"+x[this.to]+" "+y[this.to]+" " ;

		}
	}

	static class comp implements Comparator<Vertex> {
		@Override
		public int compare(Vertex o1, Vertex o2) {

			if (o1.weight > o2.weight) {
				return 1;
			} else if (o1.weight < o2.weight) {
				return -1;
			} else {
				return 0;
			}
		}

	}

	public static void createEdges(int[] x, int[] y) {

		for (int i = 0; i < y.length - 1; i++) {
			for (int j = i+1; j < y.length; j++) {
				list.add(new Vertex(i, j, distance(x[i], y[i], x[j], y[j])));
			}
		}

		Collections.sort(list, new comp());

	}

	public static double distance(int x1, int y1, int x2, int y2) {
		double a = (x1 - x2) * (x1 - x2);
		double b = (y1 - y2) * (y1 - y2);
		return Math.sqrt(a + b);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		 x = new int[n];
		 y = new int[n];
		for (int i = 0; i < n; i++) {
			x[i] = scanner.nextInt();
			y[i] = scanner.nextInt();
		}
		System.out.println(minimumDistance(x, y));
	}
}
