import java.util.List;
import java.util.ArrayList;
import java.util.*;

public class Queens {
    List<List<String>>ans=new ArrayList();
    static int N=4;
 
    boolean isSafe(char[][] board,int row,int col,int n){
     
        for(int i=0;i<row;i++){
            if(board[i][col]=='Q'){
                return false;
            }
        }

        //upper left diagnol
        for(int i=row-1,j=col-1;i>=0 && j>=0;i--,j--){
            if(board[i][j]=='Q'){
                return false;
            }
        }

        //upper right diagnol
        for(int i=row-1,j=col+1;i>=0 && j<n;i--,j++){
            if(board[i][j]=='Q'){
                return false;
            }
        }
        return true;

    }

    boolean solve(int row,char [][]board,int n){
        
        if(row==n){
            return true;
        }
        for(int col=0;col<n;col++){

            if(isSafe(board,row,col,n)){
                board[row][col]='Q';
                
                if(solve(row+1,board,n)){
                    return true;
                }

                board[row][col]='.';
            }

        }
        return false;
    }
 

    public static void main(String args[]){
     char [][]board=new char[N][N];
    Queens q=new Queens();
     for(int i=0;i<N;i++){
        Arrays.fill(board[i],'.');
     }

     for(int row=0;row<N;row++){
        q.solve(row,board,N);
     }

     for(int i=0;i<N;i++){
        for(int j=0;j<N;j++){
           System.out.print(board[i][j]+" ");
        }
        System.out.println();
     }

 }   
}
