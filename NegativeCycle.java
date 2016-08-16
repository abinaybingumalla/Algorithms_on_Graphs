import java.util.ArrayList;
import java.util.Scanner;

public class NegativeCycle {
	
	static int times;
	static int dist[];
	
    private static int negativeCycle(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost) {
        
    	//dist[0] = 0;
    	
    	while(times > 0){
    		for (int i = 0; i < adj.length; i++) {
    			for (int j = 0; j < adj[i].size(); j++) {
    				int v = adj[i].get(j);
    				
    				if(dist[v] > dist[i] + cost[i].get(j)){
    					dist[v] = dist[i] + cost[i].get(j);
    				}
    				
    			}
    		}
    		times--;
    	}
    	
    	
    	
    	int copy[] = new int[dist.length];
    	for (int i = 0; i < dist.length; i++) {
			copy[i] = dist[i];
		}
    	
    	
    	for (int i = 0; i < adj.length; i++) {
			for (int j = 0; j < adj[i].size(); j++) {
				int v = adj[i].get(j);
				
				if(dist[v] > dist[i] + cost[i].get(j)){
					dist[v] = dist[i] + cost[i].get(j);
				}
				
			}
		}
    	boolean track = false;
    	
    	for (int i = 0; i < copy.length; i++) {
			if(copy[i] != dist[i]){
				track = true;
				break;
			}
		}
    	
       return track ? 1 : 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        times =n;
        dist = new int[n];
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
            dist[i] = Integer.MAX_VALUE - 47483647;
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        System.out.println(negativeCycle(adj, cost));
    }
}

