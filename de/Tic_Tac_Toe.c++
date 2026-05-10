#include<iostream>
using namespace std;
char board[3][3]={
    {'X','O','X'},
    {'O','X',' '},
    {' ',' ','O'}
};

void printboard(){
    for(int i=0;i<3;i++){
        for(int j=0;j<3;j++){
            cout<<board[i][j]<<" ";
        }
        cout<<endl;
    }
}
bool isWinning(char player){
    //same row
    for(int i=0;i<3;i++){
      if(board[i][0]==player && board[i][1]==player && board[i][2]==player)
      return true;
    }

    //same column
    for(int j=0;j<3;j++){
      if(board[0][j]==player && board[1][j]==player && board[2][j]==player){
        return true;
      }
    }

    //same diagnol
     if(board[0][0]==player && board[1][1]==player && board[2][2]==player){
        return true;
     }

     if(board[0][2]==player && board[1][1]==player && board[2][0]==player){
        return true;
     }
     return false;
}
 int heuristic(){
    if(isWinning('X')){
      return 10;
    }
    if(isWinning('O')){
      return -10;
    }
    return 0;
    
 }
 void bestmove(){
    int bestscore=-1000;

    int bestrow=-1;
    int bestcolumn=-1;

    for(int i=0;i<3;i++){
        for(int j=0;j<3;j++){
            if(board[i][j]==' '){
                board[i][j]='X';
                int score=heuristic();
                board[i][j]=' ';

                if(score>bestscore){
                    bestscore=score;
                    bestrow=i;
                    bestcolumn=j;

                }
            }
        }
    }
    board[bestrow][bestcolumn]='X';
 }
int main(){
   cout<<"Board before : "<<endl;
   printboard();
   bestmove();
   cout<<"After AI move  : "<<endl;

      printboard();
    return 0;
}