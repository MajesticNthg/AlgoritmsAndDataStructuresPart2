import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SimpleGraphTest {

    @Test
    void testAddVertex() {
        SimpleGraph graph = new SimpleGraph(3);

        graph.addVertex(10);
        graph.addVertex(20);

        assertNotNull(graph.vertex[0]);
        assertEquals(10, graph.vertex[0].Value);
        assertEquals(20, graph.vertex[1].Value);
    }

    @Test
    void testAddEdgeAndIsEdge() {
        SimpleGraph graph = new SimpleGraph(3);

        graph.addVertex(1);
        graph.addVertex(2);

        graph.AddEdge(0, 1);

        assertTrue(graph.IsEdge(0, 1));
        assertTrue(graph.IsEdge(1, 0));
        assertFalse(graph.IsEdge(0, 2)); // вершины 2 нет
    }

    @Test
    void testRemoveEdge() {
        SimpleGraph graph = new SimpleGraph(3);

        graph.addVertex(1);
        graph.addVertex(2);
        graph.AddEdge(0, 1);

        assertTrue(graph.IsEdge(0, 1));

        graph.RemoveEdge(0, 1);

        assertFalse(graph.IsEdge(0, 1));
        assertFalse(graph.IsEdge(1, 0));
    }

    @Test
    void testRemoveVertex() {
        SimpleGraph graph = new SimpleGraph(3);

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        graph.AddEdge(0, 1);
        graph.AddEdge(1, 2);

        assertTrue(graph.IsEdge(0, 1));
        assertTrue(graph.IsEdge(1, 2));

        graph.RemoveVertex(1);

        assertNull(graph.vertex[1]);
        assertFalse(graph.IsEdge(0, 1));
        assertFalse(graph.IsEdge(1, 2));
    }

    @Test
    void testAddVertexOverflow() {
        SimpleGraph graph = new SimpleGraph(2);

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3); // лишняя, места нет

        assertNotNull(graph.vertex[0]);
        assertNotNull(graph.vertex[1]);
        assertEquals(1, graph.vertex[0].Value);
        assertEquals(2, graph.vertex[1].Value);
    }
}