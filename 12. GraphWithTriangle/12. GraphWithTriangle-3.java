import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class SimpleGraphWeakVerticesTest {

    @Test
    public void testTriangleGraph_AllInTriangle() {
        SimpleGraph g = new SimpleGraph(3);
        g.AddVertex(0);
        g.AddVertex(1);
        g.AddVertex(2);

        g.AddEdge(0, 1);
        g.AddEdge(1, 2);
        g.AddEdge(2, 0);

        ArrayList<Vertex> weak = g.WeakVertices();
        assertTrue(weak.isEmpty(), "В полном треугольнике нет слабых вершин");
    }

    @Test
    public void testLineGraph_AllWeak() {
        SimpleGraph g = new SimpleGraph(3);
        g.AddVertex(0);
        g.AddVertex(1);
        g.AddVertex(2);

        g.AddEdge(0, 1);
        g.AddEdge(1, 2);

        ArrayList<Vertex> weak = g.WeakVertices();
        assertEquals(3, weak.size(), "В линейном графе все вершины слабые");
    }

    @Test
    public void testTrianglePlusLeaf_OneWeak() {
        SimpleGraph g = new SimpleGraph(4);
        g.AddVertex(0);
        g.AddVertex(1);
        g.AddVertex(2);
        g.AddVertex(3);

        g.AddEdge(0, 1);
        g.AddEdge(1, 2);
        g.AddEdge(2, 0);

        g.AddEdge(0, 3); // вершина 3 только с 0 связана

        ArrayList<Vertex> weak = g.WeakVertices();
        assertEquals(1, weak.size(), "Только вершина 3 должна быть слабой");
        assertEquals(3, weak.get(0).Value);
    }

    @Test
    public void testSquareGraph_AllWeak() {
        SimpleGraph g = new SimpleGraph(4);
        g.AddVertex(0);
        g.AddVertex(1);
        g.AddVertex(2);
        g.AddVertex(3);

        g.AddEdge(0, 1);
        g.AddEdge(1, 2);
        g.AddEdge(2, 3);
        g.AddEdge(3, 0);

        ArrayList<Vertex> weak = g.WeakVertices();
        assertEquals(4, weak.size(), "В квадрате нет треугольников, все вершины слабые");
    }

    @Test
    public void testComplexGraph_MixedWeakAndStrong() {
        SimpleGraph g = new SimpleGraph(5);
        g.AddVertex(0);
        g.AddVertex(1);
        g.AddVertex(2);
        g.AddVertex(3);
        g.AddVertex(4);

        // Треугольник 0-1-2
        g.AddEdge(0, 1);
        g.AddEdge(1, 2);
        g.AddEdge(2, 0);

        // "хвостики"
        g.AddEdge(2, 3);
        g.AddEdge(3, 4);

        ArrayList<Vertex> weak = g.WeakVertices();

        assertEquals(2, weak.size(), "Только вершины 3 и 4 слабые");
        assertTrue(weak.stream().anyMatch(v -> v.Value == 3));
        assertTrue(weak.stream().anyMatch(v -> v.Value == 4));
    }
}