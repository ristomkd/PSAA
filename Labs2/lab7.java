import java.util.LinkedList;
import java.util.Scanner;

class GNode<E> {
    public int num;
    public E info;
    public LinkedList<GNode> list;

    public GNode(int num, E info) {
        this.num = num;
        this.info = info;
        this.list = new LinkedList<>();
    }

    void addNeighbour(GNode<E> node) {
        if (!list.contains(node)) {
            list.add(node);
        }
    }

    void deleteNeighbour(GNode<E> node) {
        if (list.contains(node)) {
            list.remove(node);
        }
    }

    boolean hasNeighbour(GNode<E> node) {
        return list.contains(node);
    }

}

class Graph<E> {
    public int n;
    public GNode<E> graph[];

    public Graph(int n, E[] infos) {
        this.n = n;
        graph = new GNode[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new GNode(i, infos[i]);
        }
    }

    boolean neighbours(int x, int y) {
        return graph[x].hasNeighbour(graph[y]);
    }

    void addEdge(int x, int y) {
        graph[x].addNeighbour(graph[y]);
        graph[y].addNeighbour(graph[x]);
    }

    void deleteEdge(int x, int y) {
        graph[x].deleteNeighbour(graph[y]);
        graph[y].deleteNeighbour(graph[x]);
    }

    GNode getNode(E info) {
        for (int i = 0; i < n; i++) {
            if (graph[i].info == info) {
                return graph[i];
            }
        }
        return null;
    }

    void addNode(E info) {
        ++n;

        GNode<E>[] graphpom = new GNode[n];

        for (int i = 0; i < n; i++) {
            graphpom[i] = graph[i];
        }
        graphpom[n - 1] = new GNode(n - 1, info);

        graph = graphpom;
    }

    void deleteNode(E info) {
        GNode node = getNode(info);
        if (node == null) {
            return;
        } else {
            for (int i = 0; i < n; i++) {
                if (graph[i].hasNeighbour(node)) {
                    graph[i].deleteNeighbour(node);
                }
            }

            for (int i = node.num; i < n - 1; i++) {
                graph[i] = graph[i + 1];
                graph[i].num = i;
            }
            n--;
        }

    }

    void dfs(int visited[], int start) {
        visited[start] = 1;
        System.out.println("Node: " + graph[start].info);

        GNode<E> pom = graph[start];
        GNode<E> next;
        for (int i = 0; i < pom.list.size(); i++) {
            next = pom.list.get(i);
            if (visited[next.num] == 0) {
                dfs(visited, next.num);
            }
        }

    }

    public void printaj() {
        for (int i = 0; i < n; i++) {
            System.out.print(graph[i].info + " ");
            for (int j = 0; j < graph[i].list.size(); j++) {
                System.out.print(graph[i].list.get(j).info + ", ");
            }
            System.out.println("");
        }
    }

}

public class lab7 {
    /*
     * Дадена е мапа од градови. Помеѓу два града може, но не мора, да постои пат.
     * Ако помеѓу два града има пат, тогаш може да се стигне од првиот во вториот и
     * од вториот во првиот град. Група туристи тргнува на пат од град кој го
     * внесува корисникот. Да се одредат сите градови до кои може да стигне таа
     * група туристи.
     * 
     * Прво корисникот го внесува бројот на градови, по што корисникот ги внесува
     * имињата на градовите. Потоа се внесува бројот на патишта. За секој пат се
     * внесува парот од градови кои тој пат ги поврзува.
     * 
     * На крај, корисникот внесува град од каде тргнуваат групата туристи. За
     * внесената вредност, на екран треба да се прикажат сите градови до кои може да
     * се дојде.
     * 
     * Напомена: Реализацијата може да ја направите во главна програма, со помош на
     * една функција, или да искористите повеќе помошни функции.
     * 
     * Пример:
     * 
     * Број на градови: 5
     * 
     * Градови: Скопје, Берлин, Осло, Лондон, Бирмингем
     * 
     * Број на патишта: 4
     * 
     * Патишта:
     * 
     * Скопје – Берлин
     * 
     * Скопје – Осло
     * 
     * Берлин – Осло
     * 
     * Лондон – Бирмингем
     * 
     * За внесен град Осло треба да се испечатат Скопје и Берлин.
     * 
     * За внесен град Бирмингем треба да се испечати Лондон
     */
    public static void main(String[] args) throws Exception {
        Character[] niza = { 'S', 'B', 'O', 'L', 'R' };

        Graph<Character> g = new Graph<Character>(5, niza);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(4, 3);

        Scanner s = new Scanner(System.in);

        Character c = s.next().charAt(0);

        for (int i = 0; i < g.n; i++) {
            if (c.equals(g.graph[i].info)) {
                int[] visited = new int[g.n];
                for (int j = 0; j < visited.length; j++) {
                    visited[j] = 0;
                }
                g.dfs(visited, i);
                break;
            }
        }
    }
}