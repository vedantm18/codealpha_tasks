import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
public class GraphList {
    Scanner sc=new Scanner(System.in);
    int vertices;
    ArrayList<ArrayList<Integer>>adjList;
    
    GraphList(int v){
        vertices=v;
        adjList=new ArrayList<>();

        for(int i=0;i<v;i++){
            adjList.add(new ArrayList<>());
        }
    }

    void addEdge(){
        System.out.println("Enter number of Egde : ");
        int edge=sc.nextInt();

        System.out.println("Enter source and Destination : ");
        for(int i=0;i<edge;i++){
            int source=sc.nextInt();
            int dest=sc.nextInt();

            adjList.get(source).add(dest);
            adjList.get(dest).add(source);
        }
        System.out.println();
    }
    void display(){
        System.out.println("-----Gragh-----");
        for(int i=0;i<vertices;i++){
             System.out.print(i+" -> ");
            for(int a:adjList.get(i)){
                System.out.print(" "+a);
            }
            System.out.println();
        }
    }
    void BFS(){
        Queue<Integer>q=new LinkedList<>();
        boolean []visited=new boolean[vertices];

        q.add(0);
        visited[0]=true;
        System.out.println("BFS Traversal");
        while(!q.isEmpty()){
            int source=q.poll();
             System.out.println(source+" ");

            for(int a:adjList.get(source)){
                if(!visited[a]){
                    visited[a]=true;
                    q.add(a);
                }
            }

        }
        
    }
  public static void main(String args[]){
     GraphList g=new GraphList(5);
     g.addEdge();
     g.display();
     g.BFS();
  }
}
