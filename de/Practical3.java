import java.util.*;

public class Practical3 {

    // =======================
    // 1. SELECTION SORT
    // =======================
    static void selectionSort(int arr[]) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            // swap
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }

        System.out.println("Selection Sort Result:");
        System.out.println(Arrays.toString(arr));
    }

    // =======================
    // 2. KRUSKAL'S ALGORITHM
    // =======================
    static class Edge implements Comparable<Edge> {
        int src, dest, weight;

        Edge(int s, int d, int w) {
            src = s;
            dest = d;
            weight = w;
        }

        public int compareTo(Edge other) {
            return this.weight - other.weight;
        }
    }

    static int find(int parent[], int i) {
        if (parent[i] == i)
            return i;
        return parent[i] = find(parent, parent[i]); // path compression
    }

    static void union(int parent[], int x, int y) {
        int rootX = find(parent, x);
        int rootY = find(parent, y);
        parent[rootX] = rootY;
    }

    static void kruskalMST() {
        int V = 4;

        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 10));
        edges.add(new Edge(0, 2, 6));
        edges.add(new Edge(0, 3, 5));
        edges.add(new Edge(1, 3, 15));
        edges.add(new Edge(2, 3, 4));

        Collections.sort(edges);

        int parent[] = new int[V];
        for (int i = 0; i < V; i++)
            parent[i] = i;

        System.out.println("\nKruskal MST:");

        int count = 0;
        for (Edge e : edges) {
            int x = find(parent, e.src);
            int y = find(parent, e.dest);

            if (x != y) {
                System.out.println(e.src + " - " + e.dest + " : " + e.weight);
                union(parent, x, y);
                count++;
                if (count == V - 1)
                    break;
            }
        }
    }

    // =======================
    // 3. DIJKSTRA ALGORITHM
    // =======================
    static int minDistance(int dist[], boolean visited[]) {
        int min = Integer.MAX_VALUE, index = -1;

        for (int i = 0; i < dist.length; i++) {
            if (!visited[i] && dist[i] < min) {
                min = dist[i];
                index = i;
            }
        }
        return index;
    }

    static void dijkstra(int graph[][], int src) {
        int V = graph.length;
        int dist[] = new int[V];
        boolean visited[] = new boolean[V];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        for (int i = 0; i < V - 1; i++) {
            int u = minDistance(dist, visited);

            if (u == -1) break; // safety check

            visited[u] = true;

            for (int v = 0; v < V; v++) {
                if (!visited[v] && graph[u][v] != 0 &&
                        dist[u] != Integer.MAX_VALUE &&
                        dist[u] + graph[u][v] < dist[v]) {

                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        System.out.println("\nDijkstra Shortest Paths from source 0:");
        for (int i = 0; i < V; i++) {
            System.out.println("0 -> " + i + " = " + dist[i]);
        }
    }

    // =======================
    // MAIN METHOD
    // =======================
    public static void main(String[] args) {

        // Selection Sort
        int arr[] = {64, 25, 12, 22, 11};
        selectionSort(arr);

        // Kruskal MST
        kruskalMST();

        // Dijkstra
        int graph[][] = {
                {0, 10, 0, 30, 100},
                {10, 0, 50, 0, 0},
                {0, 50, 0, 20, 10},
                {30, 0, 20, 0, 60},
                {100, 0, 10, 60, 0}
        };

        dijkstra(graph, 0);
    }
}