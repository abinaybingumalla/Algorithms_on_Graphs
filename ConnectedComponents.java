import java.util.ArrayList;
import java.util.Scanner;

public class ConnectedComponents {
	
	static boolean visited[] ;
	static ArrayList<Integer>[] adj;
	
    private static int numberOfComponents(ArrayList<Integer>[] adj) {
        int result = 0;
        //write your code here
        for(int i=0;i<adj.length;i++){
        	if(!visited[i]){
        		explore(i);
        		result += 1;
        	}
        }
        return result;
    }
    
    private static void explore(Integer v) {
		visited[v] = true;
		for(int k=0;k<adj[v].size();k++){
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
            adj[y - 1].add(x - 1);
        }
        System.out.println(numberOfComponents(adj));
    }
}

