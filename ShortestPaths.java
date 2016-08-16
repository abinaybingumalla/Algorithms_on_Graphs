import java.util.*;

public class ShortestPaths {

	
	static boolean vis[] ;
	static int times;
	static int prev[];
    private static void shortestPaths(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, long[] distance, int[] reachable, int[] shortest) {

    	
    	
    	/*long count = 0l;
    	
    	for (int i = 0; i < cost.length; i++) {
			for (int j = 0; j < cost[i].size(); j++) {
				count += cost[i].get(j); 
			}
		}
    	
    	for (int i = 0; i < distance.length; i++) {
			distance[i] = count + 2;
		}*/
    	
    	
    	distance[s] = 0;
    	reachable[s] = 1;
    	
    	
    	
    	while(times > 0){
    		for (int i = 0; i < adj.length; i++) {
    			for (int j = 0; j < adj[i].size(); j++) {
    				int v = adj[i].get(j);
    				//reachable[v] = 1;
    				boolean over = false;
    				if(distance[i] ==Long.MAX_VALUE && distance[v] == Long.MAX_VALUE ){
    					over = true;
    				}
    				if(!over){
    					if(distance[v] > distance[i] + cost[i].get(j)){
        					distance[v] = distance[i] + cost[i].get(j);
        				}
    				}
    				
    				
    			}
    		}
    		times--;
    	}
    	
    	long copy[] = new long[distance.length];
    	boolean visited[] = new boolean[distance.length];
    	vis = new boolean[distance.length];
    	
    	for (int i = 0; i < distance.length; i++) {
			copy[i] = distance[i];
			visited[i] = false;
			vis[i] = false;
		}
    
    	//print(distance);
    	//vth time iteration to check changes
    	
    	for (int i = 0; i < adj.length; i++) {
			for (int j = 0; j < adj[i].size(); j++) {
				int v = adj[i].get(j);
				//reachable[v] = 1;
				
					
				boolean over = false;
				if(distance[i] ==Long.MAX_VALUE ){
					over = true;
				}
				if(!over){
					if(distance[v] > distance[i] + cost[i].get(j)){
    					distance[v] = distance[i] + cost[i].get(j);
    				}
				}
				
				
			}
		}
    	//print(distance);
    	HashSet<Integer> hset = new HashSet<>();
    	Queue<Integer> q = new LinkedList<>();
    	
    	
    	for (int i = 0; i < distance.length; i++) {
			if(copy[i] > distance[i]){
				q.add(i);
			}
		}
    	
    	while(!q.isEmpty()){
    		
    		int temp = q.remove();
    		
    		for (int i = 0; i < adj[temp].size(); i++) {
				
    			if(!visited[adj[temp].get(i)]){
    				visited[adj[temp].get(i)] = true;
    				q.add(adj[temp].get(i));
    				hset.add(adj[temp].get(i));
    			}
			}
    		
    	}
    	
    	for (int i = 0; i < distance.length; i++) {
			if(hset.contains(i)){
				shortest[i] = 0;
			}
		}
    	
    	//explore(adj, reachable, s);
    	
    	for (int i = 0; i < distance.length; i++) {
			if(distance[i] != Long.MAX_VALUE){
				reachable[i] = 1;
			}
		}
    	
    	/*for (int i = 0; i < adj[s].size(); i++) {
			for (int j = 0; j < adj[adj[s].get(i)].size(); j++) {
				reachable[adj[adj[s].get(i)].get(j)] = 1;
			}
		}*/
    	
    }

    
    public static void explore(ArrayList<Integer>[] adj,int[] reachable,int v){
    	vis[v] = true;
    	
    	for(int k=0;k<adj[v].size();k++){
			reachable[adj[v].get(k)] = 1;
			if(vis[adj[v].get(k)] == false){
				
				explore(adj,reachable,adj[v].get(k));
			}
		}
    	
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        times = n-1;
        prev = new int[n];
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        int s = scanner.nextInt() - 1;
        long distance[] = new long[n];
        int reachable[] = new int[n];
        int shortest[] = new int[n];
        for (int i = 0; i < n; i++) {
            distance[i] = Long.MAX_VALUE;
            reachable[i] = 0;
            shortest[i] = 1;
        }
        shortestPaths(adj, cost, s, distance, reachable, shortest);
        for (int i = 0; i < n; i++) {
            if (reachable[i] == 0) {
                System.out.println('*');
            } else if (shortest[i] == 0) {
                System.out.println('-');
            } else {
                System.out.println(distance[i]);
            }
        }
    }

    
    public static void print(long[] dis){
    	for (int i = 0; i < dis.length; i++) {
			System.out.print(dis[i]+" ");
		}System.out.println();
    }
    
}

