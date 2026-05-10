#include<iostream>
#include<vector>
#include<algorithm>
#include<climits>

using namespace std;

int main()
{
    int graph[4][4] = {
        {0,10,15,20},
        {10,0,35,25},
        {15,35,0,30},
        {20,25,30,0}
    };

    vector<int> cities;

    // Store cities except starting city
    for(int i=1; i<4; i++)
    {
        cities.push_back(i);
    }

    int minCost = INT_MAX;

    do
    {
        int currentCost = 0;

        int currentCity = 0;

        cout << "Path : A ";

        for(int i=0; i<cities.size(); i++)
        {
            currentCost +=
            graph[currentCity][cities[i]];

            cout << "-> "
                 << char(cities[i] + 65)
                 << " ";

            currentCity = cities[i];
        }

        // Return back to starting city
        currentCost += graph[currentCity][0];

        cout << "-> A ";

        cout << " Cost = "
             << currentCost
             << endl;

        if(currentCost < minCost)
        {
            minCost = currentCost;
        }

    } while(next_permutation(cities.begin(),
                             cities.end()));

    cout << endl;

    cout << "Minimum Cost = "
         << minCost;

    return 0;
}