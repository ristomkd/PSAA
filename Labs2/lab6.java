package Labs2;
import java.util.*;

class GNode<E> {
    public int num;
    public E info;
    public LinkedList<GNode<E>> list;

    public GNode(int num, E info) {
        this.num = num;
        this.info = info;
        list = new LinkedList();
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
    }

    void deleteEdge(int x, int y) {
        graph[x].deleteNeighbour(graph[y]);
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
}

public class lab6 {
    public static void main(String[] args) {
        Character[] niza = { 'A', 'B', 'C', 'D', 'E', 'H', 'F', 'G' };

        Graph<Character> graph = new Graph<Character>(8,niza);
        graph.addEdge(0,1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 5);
        graph.addEdge(2, 6);
        graph.addEdge(2, 1);
        graph.addEdge(2, 3);
        graph.addEdge(4, 2);
        graph.addEdge(4, 7);
        graph.addEdge(5, 6);
        graph.addEdge(2, 5);
        graph.addEdge(1, 6);
        graph.addEdge(1, 7);

        Graph<Character> graph1 = kopiraj(graph.getNode('A'));
    }

    private static Graph<Character> kopiraj(GNode node) {
        ArrayList<GNode<Character>> jazli = new ArrayList<GNode<Character>>();
            func(jazli, node);
        Character[] infos = new Character[jazli.size()];
        for (int i = 0; i < jazli.size(); i++) {
            infos[i] = jazli.get(i).info;
            jazli.get(i).num = i;
        }
        Graph<Character> pomGraph = new Graph<>(infos.length, infos);
        for (int i = 0; i < jazli.size(); i++) {
            for (int j = 0; j < jazli.get(i).list.size(); j++) {
                pomGraph.addEdge(i, jazli.get(i).list.get(j).num);
            }
        }
        return pomGraph;
    }

    private static void func(ArrayList<GNode<Character>> jazli, GNode<Character> node) {
        if (jazli.contains(node)) {
            return;
        }
        jazli.add(node);
        for (GNode<Character> neighbor : node.list) {
            func(jazli, neighbor);
        }
    }
}
