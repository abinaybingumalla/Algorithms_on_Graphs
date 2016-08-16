import java.util.ArrayList;
import java.util.Scanner;

public class Reachability {
	
	static boolean visited[] ;
	static ArrayList<Integer>[] adj;
	static int result = 0;
	static int des = 0;
    private static int reach(ArrayList<Integer>[] adj, int x, int y) {
        //write your code here
    	des = y;
    	/*for(int i=0; i < adj[x].size();i++){
    		explore(adj[x].get(i)); 
    	}*/
        explore(x);
		return result;
        
    }


	private static void explore(Integer v) {
		visited[v] = true;
		for(int k=0;k<adj[v].size();k++){
			if(adj[v].get(k) == des){result =1; } 
			//System.out.println("r: " +result+ " "+adj[k].get(k));
			if(visited[adj[v].get(k)] == false){
				
				explore(adj[v].get(k));
			}
		}
	}





	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        visited = new boolean[n];
         adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            visited[i] = false;
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
        System.out.println(reach(adj, x, y));
    }
}

