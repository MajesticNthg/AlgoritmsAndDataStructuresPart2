import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

class FindingPathInGraphTest {

    @Test
    public void testPathExists() {
        SimpleGraph g = new SimpleGraph(5);
        g.AddVertex(1);
        g.AddVertex(2);
        g.AddVertex(3);
        g.AddVertex(4);
        g.AddVertex(5);

        g.AddEdge(0, 1);
        g.AddEdge(1, 2);
        g.AddEdge(2, 3);

        ArrayList<Vertex> path = g.DepthFirstSearch(0, 3);

        assertFalse(path.isEmpty());
        assertEquals(4, path.size());  // 1 → 2 → 3 → 4
        assertEquals(1, path.get(0).Value);
        assertEquals(4, path.get(3).Value);
    }

    @Test
    public void testNoPath() {
        SimpleGraph g = new SimpleGraph(4);
        g.AddVertex(10);
        g.AddVertex(20);
        g.AddVertex(30);
        g.AddVertex(40);

        g.AddEdge(0, 1);
        // вершины 2 и 3 изолированы

        ArrayList<Vertex> path = g.DepthFirstSearch(0, 3);

        assertTrue(path.isEmpty());
    }

    @Test
    public void testSameStartAndEnd() {
        SimpleGraph g = new SimpleGraph(3);
        g.AddVertex(100);
        g.AddVertex(200);
        g.AddVertex(300);

        g.AddEdge(0, 1);
        g.AddEdge(1, 2);

        ArrayList<Vertex> path = g.DepthFirstSearch(1, 1);

        assertEquals(1, path.size());
        assertEquals(200, path.get(0).Value);
    }

    @Test
    public void testDisconnectedGraph() {
        SimpleGraph g = new SimpleGraph(6);
        g.AddVertex(1);
        g.AddVertex(2);
        g.AddVertex(3);
        g.AddVertex(4);
        g.AddVertex(5);
        g.AddVertex(6);

        g.AddEdge(0, 1);
        g.AddEdge(1, 2);
        // другая компонента
        g.AddEdge(3, 4);
        g.AddEdge(4, 5);

        ArrayList<Vertex> path = g.DepthFirstSearch(0, 5);

        assertTrue(path.isEmpty()); // вершины в разных компонентах
    }
}