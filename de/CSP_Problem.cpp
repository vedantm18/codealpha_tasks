#include<iostream>
using namespace std;

int graph[4][4] = {
    {0,1,1,0},
    {1,0,1,1},
    {1,1,0,0},
    {0,1,0,0}
};

int color[4];

bool isSafe(int node, int col)
{
    for(int i=0; i<4; i++)
    {
        if(graph[node][i] == 1 &&
           color[i] == col)
        {
            return false;
        }
    }

    return true;
}

bool solve(int node)
{
    // All vertices colored
    if(node == 4)
    {
        return true;
    }

    // Try colors
    for(int col=1; col<=3; col++)
    {
        if(isSafe(node, col))
        {
            color[node] = col;

            // Recursive call
            if(solve(node + 1))
            {
                return true;
            }

            // Backtracking
            color[node] = 0;
        }
    }

    return false;
}

int main()
{
    // Initially no colors assigned
    for(int i=0; i<4; i++)
    {
        color[i] = 0;
    }

    if(solve(0))
    {
        cout << "Color Assignment:\n";

        for(int i=0; i<4; i++)
        {
            cout << "Vertex "
                 << i
                 << " -> Color "
                 << color[i]
                 << endl;
        }
    }
    else
    {
        cout << "No Solution";
    }

    return 0;
}