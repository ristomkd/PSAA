
package Labs2;
import java.util.Scanner;

class GraphInfo<E extends Comparable<E>> {
    E info;

    public GraphInfo(E info) {
        this.info = info;
    }
}

class Graph<E extends Comparable<E>> {
    private GraphInfo<E> infos[];
    private int n;
    private int mtx[][];

    public Graph(int n) {
        this.n = n;
        infos = new GraphInfo[n];
        mtx = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mtx[i][j] = 0;
            }
        }
    }

    void addEdge(int x, int y) {
        mtx[x][y] = 1;
        mtx[y][x] = 1;
    }

    void deleteEdge(int x, int y) {
        mtx[x][y] = 0;
        mtx[y][x] = 0;
    }

    void setInfo(int pozz, E info) {
        infos[pozz] = new GraphInfo(info);
    }

    E getInfo(int pozz) {
        return infos[pozz].info;
    }

    int getIndex(E info) {
        for (int i = 0; i < n; i++) {
            if (infos[i].info == info) {
                return i;
            }
        }
        return -1;
    }

    boolean neighbours(int x, int y) {
        if (mtx[x][y] == 1) {
            return true;
        } else {
            return false;
        }
    }

    void addNode(E info) {
        ++n;
        GraphInfo[] infospom = new GraphInfo[n];
        for (int i = 0; i < n - 1; i++) {
            infospom[i] = infos[i];
        }
        infospom[n - 1] = new GraphInfo(info);

        int[][] mtxpom = new int[n][n];
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                mtxpom[i][j] = mtx[i][j];
            }
        }

        for (int i = 0; i < n; i++) {
            mtxpom[n - 1][i] = 0;
            mtxpom[i][n - 1] = 0;
        }

        infos = infospom;
        mtx = mtxpom;
    }

    void deleteNode(E info) {
        int ind = getIndex(info);

        if (ind != n - 1) {
            for (int i = ind; i < n - 1; i++) {
                for (int j = 0; j < n; j++) {
                    mtx[i][j] = mtx[i + 1][j];
                }
            }
            for (int j = ind; j < n - 1; j++) {
                for (int i = 0; i < n; i++) {
                    mtx[i][j] = mtx[i][j + 1];
                }
            }

            for (int i = ind; i < n - 1; i++) {
                infos[i] = infos[i + 1];
            }
        }
        n--;
    }

    public void printMtx() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(mtx[i][j] + ", ");
            }
            System.out.println();
        }
    }

    void dfs(int visited[], int start) {
        visited[start] = 1;
        System.out.println("Node: " + infos[start].info);

        for (int i = 0; i < n; i++) {
            if (i != start) {
                if (mtx[i][start] > 0 && visited[i] == 0) {
                    dfs(visited, i);
                }
            }
        }
    }

    public int getN() {
        return n;
    }

    public int func(int[] visited, int current, int start, int depth, int N) {
        if (depth == N) {
            return (current == start) ? 1 : 0;
        }

        visited[current] = 1;
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (mtx[current][i] > 0) {
                if (i == start && depth == N - 1) {
                    count++;
                } else if (visited[i] == 0) {
                    count += func(visited, i, start, depth + 1, N);
                }
            }
        }
        visited[current] = 0;
        return count;
    }
}

public class lab8 {

    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        int num_nodes = 5;
        Graph<String> g = new Graph(num_nodes);

        for (int i = 0; i < num_nodes; i++) {
            g.setInfo(i, "A" + i);
        }

        g.addEdge(0, 4);
        g.addEdge(0, 1);
        g.addEdge(0, 3);
        g.addEdge(1, 3);
        g.addEdge(1, 2);
        g.addEdge(2, 3);

        pateki(g, N);
        s.close();
    }

    private static void pateki(Graph<String> graph, int N) {
        int count = 0;
        int[] visited = new int[graph.getN()];
        for (int i = 0; i < graph.getN(); i++) {
            count += graph.func(visited, i, i, 0, N);
        }
        System.out.println(count);
    }
}