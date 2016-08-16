import java.util.ArrayList;
import java.util.Scanner;

public class Acyclicity {
	
	static ArrayList<Integer>[] adj;
	static boolean visited[] ;
	static int toCompare = 0;
	static int result = 0;
    private static int acyclic(ArrayList<Integer>[] adj) {
        //write your code here
    	for(int i=0;i<adj.length;i++){
        	if(!visited[i]){
        		toCompare = i;
        		explore(i);
        	}
        }
        return result;
    }

    private static void explore(Integer v) {
		visited[v] = true;
		for(int k=0;k<adj[v].size();k++){
			//System.out.println("r: " +result+ " "+adj[k].get(k));
			if(adj[v].get(k) == toCompare){result =1; }
			if(visited[adj[v].get(k)] == false){
				
				explore(adj[v].get(k));
			}
		}
	}
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        adj = (ArrayList<Integer>[])new ArrayList[n];
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            visited[i] = false;
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
        }
        System.out.println(acyclic(adj));
    }
}

