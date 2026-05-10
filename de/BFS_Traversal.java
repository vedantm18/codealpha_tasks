import java.util.Scanner;
public class BFS_Traversal {
    Scanner sc=new Scanner(System.in);
     int vertices;
     int adjMatrix[][];

    BFS_Traversal(int v){
        vertices=v;
        adjMatrix=new int[v][v];

    }

    void addEdge(){

        System.out.println("Enter number of Edges : ");
        int edge=sc.nextInt();

        System.out.println("Enter source and destination : ");
         for(int i=0;i<edge;i++){
            int source=sc.nextInt();
            int dest=sc.nextInt();

            adjMatrix[source][dest]=1;
            adjMatrix[dest][source]=1;
         }
    }
    void display(){
        for(int i=0;i<vertices;i++){
            for(int j=0;j<vertices;j++){
               System.out.print(adjMatrix[i][j]);
            }
            System.out.println();
        }
    }
    
    
    public static void main(String args[]){
       BFS_Traversal b=new BFS_Traversal(5);
       b.addEdge();
       b.display();

    }
}
