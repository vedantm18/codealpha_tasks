public class Selection{
    public static void main(String args[]){
        int arr[]={20,11,15,14,10,22};
         
        System.out.println("Original Array: ");
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }

        for(int i=0;i<arr.length-1;i++){
            int low=i;
            for(int j=i+1;j<arr.length;j++){
                 if(arr[j]<arr[low]){
                    low=j;
                 }
            }
            int temp=arr[i];
            arr[i]=arr[low];
            arr[low]=temp;
        }
        System.out.println();
        System.out.println("Sorted Array : ");
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
    }
}