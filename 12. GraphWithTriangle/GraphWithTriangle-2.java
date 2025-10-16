// 12 задание, задача №1, "Метод подсчета треугольников в графе", сложность данного решения - O(N*N*N). 
// Рефлексия: в основном решении я допустил ошибку по стилю - использовал вложенные циклы. 
// Поскольку мой алгоритм решения в двух случаях схожий - здесь я себе первоочередно поставил задачу избавиться от них посредством рекурсии. 
// Все неудобные расчеты постарался спрятать в приватных методах. Так или иначе, алгоритм все еще сложный O(N^3), плюс в коде присутствует много переменных-счетчиков, что ухудщает его читаемость и навигацию. 
// Не хотел изначально использовать рекомендацию, где результат можно было делить на 3, т.к. сразу было оговоречно, что это вариант для частного случая, поэтому постарался найти иной способ.

public class countTriangle {
    private final int max_vertex;
    private final Vertex[] vertex;
    private final int[][] m_adjacency;

    public countTriangle(int max_vertex, Vertex[] vertex, int[][] m_adjacency) {
        this.max_vertex = max_vertex;
        this.vertex = vertex;
        this.m_adjacency = m_adjacency;
    }
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
