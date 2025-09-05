import java.util.*;

class Vertex
{
    public int Value;
    public Vertex (int val)
    {
        Value = val;
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

}
