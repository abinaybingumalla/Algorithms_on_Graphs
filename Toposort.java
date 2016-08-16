import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Toposort {
	
	static boolean visited[] ;
	static ArrayList<Integer>[] adj;
	static Vertex[] post;
	static int clock = 1;
    /*private static ArrayList<Integer> toposort(ArrayList<Integer>[] adj) {
        int used[] = new int[adj.length];
        ArrayList<Integer> order = new ArrayList<Integer>();
        //write your code here
        return order;
    }

    private static void dfs(ArrayList<Integer>[] adj, int[] used, ArrayList<Integer> order, int s) {
      //write your code here
    }*/
	
	static class Vertex{
		int vertex;
		int val;
		public Vertex(int vertex, int val) {
			this.vertex = vertex;
			this.val = val;
		}
		
	}
	
	private static void numberOfComponents(ArrayList<Integer>[] adj) {
        for(int i=0;i<adj.length;i++){
        	if(!visited[i]){
        		explore(i);
        	}
        }
    }
    
    private static void explore(Integer v) {
		visited[v] = true;
		for(int k=0;k<adj[v].size();k++){
			//System.out.println("r: " +result+ " "+adj[k].get(k));
			if(visited[adj[v].get(k)] == false){
				
				explore(adj[v].get(k));
			}
			postvisit(v);
		}
	}

    private static void postvisit(Integer v) {
		post[v].val = clock;
		clock +=1;
	}

    public static class comp implements Comparator<Vertex>{

		@Override
		public int compare(Vertex o1 ,Vertex o2) {
			
			if(o1.val > o2.val){ return 1;}
			if(o1.val < o2.val) {return -1;}
			return 0;
		}
    	
    }
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        visited = new boolean[n];
        adj = (ArrayList<Integer>[])new ArrayList[n];
        post = new Vertex[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            visited[i] = false;
            post[i] = new Vertex(i+1, 0);
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
        }
        
        numberOfComponents(adj);
        
        comp abc = new comp();
        
        Arrays.sort(post,abc);
        for(int k=post.length-1;k>=0;k--){
        	System.out.print(post[k].vertex+" ");
        }
        /*ArrayList<Integer> order = toposort(adj);
        for (int x : order) {
            System.out.print((x + 1) + " ");
        }*/
    }
}

