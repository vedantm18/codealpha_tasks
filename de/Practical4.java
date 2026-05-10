public class Practical4{
   int[][]arr=new int[4][4];
   
  static void setMatrix(int arr[][]){
   for(int i=0;i<4;i++){
      for(int j=0;j<4;j++){
        arr[i][j]=0;
      }
   }
}

   static void NQueens(int arr[][],int n){
      int previous=-2;
      for(int i=0;i<n;i++){
          int index=0;
         if(index==previous-1 && index==previous && index==previous+1){
               index++;
         }
         arr[i][index]=1;
      }
   }

   public static void main(String args[]){
    int n=4;
    int arr[][]=new int[4][4];

     setMatrix(arr);
     Practical4.NQueens(arr,n);

     for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            System.out.print(arr[i][j]);
        }
        System.out.println();
     }


   }
}
