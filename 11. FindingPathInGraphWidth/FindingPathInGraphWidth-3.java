import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class FindingPathInGraphWidthTest {

    @Test
    public void testBFS_PathExists() {
        SimpleGraph graph = new SimpleGraph(5);
        for (int i = 0; i < 5; i++) {
            graph.AddVertex(i + 1);
        }

        graph.AddEdge(0, 1);
        graph.AddEdge(1, 2);
        graph.AddEdge(2, 3);
        graph.AddEdge(3, 4);

        ArrayList<Vertex> path = graph.BreadthFirstSearch(0, 4);

        assertFalse(path.isEmpty());
        assertEquals(5, path.size());
        assertEquals(1, path.get(0).Value);
        assertEquals(5, path.get(path.size() - 1).Value);
    }

    @Test
    public void testBFS_NoPath() {
        SimpleGraph graph = new SimpleGraph(4);
        for (int i = 0; i < 4; i++) {
            graph.AddVertex(i + 10);
        }

        // 0-1 связаны, 2-3 связаны, но между группами рёбер нет
        graph.AddEdge(0, 1);
        graph.AddEdge(2, 3);

        ArrayList<Vertex> path = graph.BreadthFirstSearch(0, 3);

        assertTrue(path.isEmpty()); // пути нет
    }

    @Test
    public void testBFS_CycleGraph() {
        SimpleGraph graph = new SimpleGraph(4);
        for (int i = 0; i < 4; i++) {
            graph.AddVertex(i + 100);
        }

        // цикл 0-1-2-3-0
        graph.AddEdge(0, 1);
        graph.AddEdge(1, 2);
        graph.AddEdge(2, 3);
        graph.AddEdge(3, 0);

        ArrayList<Vertex> path = graph.BreadthFirstSearch(0, 2);

        assertFalse(path.isEmpty());
        assertEquals(3, path.size()); // кратчайший путь: 0-1-2 или 0-3-2
        assertEquals(100, path.get(0).Value);
        assertEquals(102, path.get(path.size() - 1).Value);
    }

    @Test
    public void testBFS_MultiplePaths() {
        SimpleGraph graph = new SimpleGraph(6);
        for (int i = 0; i < 6; i++) {
            graph.AddVertex(i + 1);
        }

        // Две дороги от 0 до 5:
        // короткая: 0-5
        // длинная: 0-1-2-3-4-5
        graph.AddEdge(0, 5);
        graph.AddEdge(0, 1);
        graph.AddEdge(1, 2);
        graph.AddEdge(2, 3);
        graph.AddEdge(3, 4);
        graph.AddEdge(4, 5);

        ArrayList<Vertex> path = graph.BreadthFirstSearch(0, 5);

        assertFalse(path.isEmpty());
        assertEquals(2, path.size()); // BFS должен выбрать кратчайший путь 0-5
        assertEquals(1, path.get(0).Value);
        assertEquals(6, path.get(1).Value);
    }
}