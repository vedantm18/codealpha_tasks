#include<iostream>
using namespace std;
class Tree{
    public:
    int data;
    Tree *left;
    Tree *right;

    Tree(int value){
        data=value;
        left=NULL;
        right=NULL;
    }
    Tree *insert(Tree *root,int value){
        Tree *nw =new Tree(value);
        if(root==NULL){
            return nw;
        }
         
        Tree *p=root;

        while(1){
            if(value<p->data){
                if(p->left==NULL){
                    p->left=nw;
                    break;
                }
                p=p->left;
            }
            else{
                    if(p->right==NULL){
                        p->right=nw;
                        break;
                    }
                    p=p->right;
                }
                
            }
            return root;
        }

        void DFS_Traversal(Tree *root){
            if(root==NULL){
                return;
            }
            cout<<root->data<<" ";
            DFS_Traversal(root->left);
            DFS_Traversal(root->right);
        }
    };

int main(){
  Tree t(0);
  Tree *root=NULL;
  root=t.insert(root,10);
  root=t.insert(root,5);
  root=t.insert(root,15);
  root=t.insert(root,3);
  root=t.insert(root,7);

  t.DFS_Traversal(root);



    return 0;
}