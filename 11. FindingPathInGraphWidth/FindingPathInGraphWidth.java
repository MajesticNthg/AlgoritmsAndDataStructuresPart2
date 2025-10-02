import java.util.*;

class Vertex
{
    public int Value;
    public boolean Hit;
    public Vertex (int val)
    {
        Value = val;
        Hit = false;
    }
}

class SimpleGraph
{
    Vertex [] vertex;
    int [][] m_adjacency;
    int max_vertex;

    public SimpleGraph (int size) {
        max_vertex = size;
        m_adjacency = new int[size][size];
        vertex = new Vertex[size];
    }

    public void AddVertex (int value)
    {
        for (int i = 0; i < max_vertex; i++) {
            if (vertex[i] == null) {
                vertex[i] = new Vertex(value);
                return;
            }
        }
    }

    public void RemoveVertex (int v)
    {
        if (v < 0 || v >= max_vertex || vertex[v] == null) {
            return;
        }

        vertex[v] = null;
        for (int i = 0; i < max_vertex; i++) {
            m_adjacency[v][i] = 0;
            m_adjacency[i][v] = 0;
        }
    }

    public boolean IsEdge (int v1, int v2)
    {
        if (v1 < 0 || v1 >= max_vertex || v2 < 0 || v2 >= max_vertex) return false;
        return m_adjacency[v1][v2] == 1;
    }

    public void AddEdge (int v1, int v2)
    {
        if (v1 < 0 || v1 >= max_vertex || v2 < 0 || v2 >= max_vertex) {
            return;
        }

        if (vertex[v1] == null || vertex[v2] == null) {
            return;
        }

        m_adjacency[v1][v2] = 1;
        m_adjacency[v2][v1] = 1;
    }

    public void RemoveEdge (int v1, int v2)
    {
        if (v1 < 0 || v1 >= max_vertex || v2 < 0 || v2 >= max_vertex) return;
        m_adjacency[v1][v2] = 0;
        m_adjacency[v2][v1] = 0;
    }

    public ArrayList<Vertex> DepthFirstSearch (int VFrom, int VTo) {
        ArrayList<Vertex> path = new ArrayList<>();
        if (VFrom < 0 || VFrom >= max_vertex || VTo < 0 || VTo >= max_vertex) {
            return path;
        }
        if (vertex[VFrom] == null || vertex[VTo] == null) {
            return path;
        }

        for (int i = 0; i < max_vertex; i++) {
            if (vertex[i] != null) {
                vertex[i].Hit = false;
            }
        }

        Stack<Integer> stack = new Stack<>();
        stack.push(VFrom);
        vertex[VFrom].Hit = true;

        return result(stack, VTo, path);
    }

    private ArrayList<Vertex> result (Stack<Integer> stack, int VTo, ArrayList<Vertex> path) {
        int current = stack.peek();

        if (current == VTo) {
            for (int index : stack) {
                path.add(vertex[index]);
            }
            return path;
        }

        boolean found = false;
        for (int i = 0; i < max_vertex; i++) {
            if (m_adjacency[current][i] == 1 && vertex[i] != null && !vertex[i].Hit) {
                vertex[i].Hit = true;
                stack.push(i);
                found = true;
                break;
            }
        }

        if (!found) {
            stack.pop();
        }

        if (!stack.isEmpty()) {
            return result(stack, VTo, path);
        }

        return path;
    }

    public ArrayList<Vertex> BreadthFirstSearch(int VFrom, int VTo) {
        ArrayList<Vertex> path = new ArrayList<>();

        if (VFrom < 0 || VFrom >= max_vertex || VTo < 0 || VTo >= max_vertex) {
            return path;
        }
        if (vertex[VFrom] == null || vertex[VTo] == null) {
            return path;
        }

        for (int i = 0; i < max_vertex; i++) {
            if (vertex[i] != null) {
                vertex[i].Hit = false;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        int[] parent = new int[max_vertex];
        Arrays.fill(parent, -1);

        vertex[VFrom].Hit = true;
        queue.add(VFrom);

        return resultForBreadthFirstSearch(VTo, queue, parent, path);
    }

    private ArrayList<Vertex> resultForBreadthFirstSearch (int VTo, Queue<Integer> queue, int[] parent,ArrayList<Vertex> path) {
        int current;
        if (!queue.isEmpty()) {
            current = queue.poll();
        } else {
            return path;
        }

        if (current == VTo) {
            LinkedList<Vertex> revPath = new LinkedList<>();
            int v = VTo;
            createPath(revPath, v, parent);
            path.addAll(revPath);
            return path;
        }

        for (int i = 0; i < max_vertex; i++) {
            if (m_adjacency[current][i] == 1 && vertex[i] != null && !vertex[i].Hit) {
                vertex[i].Hit = true;
                parent[i] = current;
                queue.add(i);
            }
        }

        if (!queue.isEmpty()) {
            return resultForBreadthFirstSearch(VTo, queue, parent, path);
        }
        return path;
    }

    private void createPath (LinkedList<Vertex> revPath, int v, int[] parent) {
        revPath.addFirst(vertex[v]);
        v = parent[v];

        if (v != -1) {
            createPath(revPath, v, parent);
        }
    }
}
