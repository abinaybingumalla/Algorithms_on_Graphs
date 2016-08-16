import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;


public class StronglyConnected {
	static int n;
	static int clock = 1;
	static Vertex[] post;
	static ArrayList<Integer>[] revAdj;
	static boolean visited[];
	static boolean postvisited[];
	
    private static int numberOfStronglyConnectedComponents(ArrayList<Integer>[] adj) {

    	 revAdj = (ArrayList<Integer>[])new ArrayList[adj.length];
    	 visited = new boolean[n];
    	 postvisited = new boolean[n];
    	 
    	 post = new Vertex[n];
         for (int i2 = 0; i2 < n; i2++) {
        	 revAdj[i2] = new ArrayList<Integer>();
             visited[i2] = false;
             postvisited[i2] = false;
             post[i2] = new Vertex(i2, 0);
         }
    	 
    	 
    	 for (int j = 0; j <  adj.length; j++) {
    		 for(int k=0;k<adj[j].size();k++){
    			 revAdj[adj[j].get(k)].add(j);
    			 //System.out.println(adj[j].get(k) + " "+j);
    		 }
         }
    	 
    	 for(int exp=0;exp<revAdj.length;exp++){
         	if(!visited[exp]){
         		explore(exp);
         	}
         }
    	 
    	 //to print RevGraph
    	 /*for(int p=0;p<revAdj.length;p++){
    		 if(revAdj[p].size() > 0 ){
    			 System.out.print(p+" ");
    			 for(int pp=0;pp<revAdj[p].size();pp++){
        			 System.out.print(revAdj[p].get(pp) +" ");
        		 }
        		 System.out.println();
    		 }
    		 
    	 }*/
    	 
    	 comp abc = new comp();
         
         Arrays.sort(post,abc);
         int result = 0;
         for(int k=post.length-1;k>=0;k--){
         	//System.out.print(post[k].vertex+" ");
         	if(!postvisited[post[k].vertex]){
         		explorepost(post[k].vertex,postvisited,adj);
        		result += 1;
         	}
         }
    	 
        return result;
    }

    private static void explorepost(int v, boolean[] postvisited,ArrayList<Integer>[] adj) {
    	postvisited[v] = true;
		for(int k=0;k<adj[v].size();k++){
			//System.out.println("r: " +result+ " "+adj[k].get(k));
			if(postvisited[adj[v].get(k)] == false){
				
				explorepost(adj[v].get(k),postvisited,adj);
			}
		}
		
	}

	static class Vertex{
		int vertex;
		int val;
		public Vertex(int vertex, int val) {
			this.vertex = vertex;
			this.val = val;
		}
		
	}
    
    
    private static void explore(Integer v) {
		visited[v] = true;
		for(int k=0;k<revAdj[v].size();k++){
			//System.out.println("r: " +result+ " "+adj[k].get(k));
			if(visited[revAdj[v].get(k)] == false){
				
				explore(revAdj[v].get(k));
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
        n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
        }
        System.out.println(numberOfStronglyConnectedComponents(adj));
        
        
    }
}

