import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


public class Clustering {
	static ArrayList<Vertex> list = new ArrayList<>();
	static int parent[];
	static int rank[];
    private static double clustering(int[] x, int[] y, int k) {

    	for (int i = 0; i < y.length - 1; i++) {
			for (int j = i+1; j < y.length; j++) {
				list.add(new Vertex(i, j, distance(x[i], y[i], x[j], y[j])));
			}
		}

		Collections.sort(list, new comp());
    	
		int count = 0;
		int a = 0;
		for (int i = 0; i < list.size(); i++) {
			Vertex v = list.get(i);
			if (Find(v.from) != Find(v.to)) {
				Union(list.get(i).from, list.get(i).to);
				count++;
				if(count-1 == x.length - k){
					a = i;
					break;
				}
				
			}
		}
		
		
		
    	return list.get(a).distance;
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

	public static double distance(int x1, int y1, int x2, int y2) {
		double a = (x1 - x2) * (x1 - x2);
		double b = (y1 - y2) * (y1 - y2);
		return Math.sqrt(a + b);
	}
	
	
	static class Vertex {
		int from;
		int to;
		double distance;

		public Vertex(int from, int to, double cost) {
			super();
			this.from = from;
			this.to = to;
			this.distance = cost;
		}
		
		public String toString(){
			return this.distance+" ";
		}
	}
	
	
	static class comp implements Comparator<Vertex> {
		@Override
		public int compare(Vertex o1, Vertex o2) {

			if (o1.distance > o2.distance) {
				return 1;
			} else if (o1.distance < o2.distance) {
				return -1;
			} else {
				return 0;
			}
		}

	}

	
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
            parent[i] = i;
			rank[i] = 0;
        }
        int k = scanner.nextInt();
        System.out.println(clustering(x, y, k));
    }
}

