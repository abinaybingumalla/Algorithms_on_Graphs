import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFS {
	private static int distance(ArrayList<Integer>[] adj, int s, int t) {
		// write your code here
		int dist[] = new int[adj.length];
		for (int i = 0; i < adj.length; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		dist[s] = 0;
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(s);
		while (q.size() > 0) {
			int deqObj = q.remove();
			//System.out.println("deq "+deqObj);
			for(int k=0;k<adj[deqObj].size();k++){
				if(dist[adj[deqObj].get(k)]==Integer.MAX_VALUE){
					q.add(adj[deqObj].get(k));
					//System.out.println("v "+adj[deqObj].get(k));
					dist[adj[deqObj].get(k)] = dist[deqObj]+1;
				}
			}
		}
		//System.out.println(t+" "+dist[t]);
		if (dist[t] != Integer.MAX_VALUE) {
			return (int) dist[t];
		} else
			return -1;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
		for (int i = 0; i < n; i++) {
			adj[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < m; i++) {
			int x, y;
			x = scanner.nextInt();
			y = scanner.nextInt();
			adj[x - 1].add(y - 1);
			adj[y - 1].add(x - 1);
		}
		int x = scanner.nextInt() - 1;
		int y = scanner.nextInt() - 1;
		System.out.println(distance(adj, x, y));
	}
}
