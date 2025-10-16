public class countTriangle {
    public int countTriangle() {
        return calculateCountTriangle(0, 0, 1);
    }

    private int calculateCountTriangle(int countTriangle, int i, int j) {
        if (i >= max_vertex) {
            return countTriangle;
        }

        if (j >= max_vertex) {
            return calculateCountTriangle(countTriangle, i + 1, i + 2);
        }

        if (vertex[i] != null && vertex[j] != null && m_adjacency[i][j] == 1) {
            countTriangle = isTriangle(i, j, countTriangle);
        }

        return calculateCountTriangle(countTriangle, i, j + 1);
    }

    private int isTriangle(int i, int j, int countTriangle) {
        for (int x = j + 1; x < max_vertex; x++) {
            if (vertex[x] == null) continue;
            if (m_adjacency[i][x] == 1 && m_adjacency[j][x] == 1) {
                countTriangle++;
            }
        }
        return countTriangle;
    }
}

