#include<iostream>
#include<climits>
using namespace std;

int main(){
   
    int graph[4][4]={
        {0,2,6,3},
        {2,0,0,5},
        {6,0,0,4},
        {3,5,4,0}
    };

    int selected[4]={0};
    
    selected[0]=1;

    int edges=0;

    int totalCost=0;
    
    while(edges<3){
        int mini=INT_MAX;

        int x=0;
        int y=0;
     
        for(int i=0;i<4;i++){
            if(selected[i]){
                for(int j=0;j<4;j++){
                    if(!selected[j] && graph[i][j]){
                        if(graph[i][j]<mini){
                            mini=graph[i][j];
                            x=i;
                            y=j;
                        }
                    }
                }
            }
        }

        cout<<x<<" "<<y<<" "<<graph[x][y]<<endl;
        totalCost+=graph[x][y];
        selected[y]=1;
        edges++;
    }
    


cout<<"Total Cost is : "<<totalCost;
  return 0;
}