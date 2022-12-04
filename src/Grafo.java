import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Grafo {
    
    // Lista de vértices do grafo
    private List<String> vertices;
    
    // Matriz de adjacência para armazenar as arestas
    private int[][] matrizAdjacencia;
    
    public Grafo(int numVertices) {
        vertices = new ArrayList<>(numVertices);
        matrizAdjacencia = new int[numVertices][numVertices];
    }
    
    // Adiciona um vértice ao grafo
    public void adicionarVertice(String vertice) {
        vertices.add(vertice);
    }
    
    // Adiciona uma aresta entre dois vértices
    public void adicionarAresta(int indiceVertice1, int indiceVertice2) {
        matrizAdjacencia[indiceVertice1][indiceVertice2] = 1;
        matrizAdjacencia[indiceVertice2][indiceVertice1] = 1;
    }
    
    // Imprime a matriz de adjacência do grafo
    public void imprimirMatrizAdjacencia() {
        
        // Imprime o cabeçalho com os nomes dos vértices
        System.out.print("  ");
        for (String vertice : vertices) {
            System.out.print(vertice + " ");
        }
        System.out.println();
        
        // Imprime os valores da matriz de adjacência
        for (int i = 0; i < vertices.size(); i++) {
            System.out.print(vertices.get(i) + " ");
            for (int j = 0; j < vertices.size(); j++) {
                System.out.print(matrizAdjacencia[i][j] + " ");
            }
            System.out.println();
        }

    }
    
    /**
    * Busca o estado que possui menos vizinhos
    * @return
    */
    public int menosVizinhos() {
        
        // Encontra o estado com o menor número de estados vizinhos
        int minVizinhos = Integer.MAX_VALUE;
        int minEstado = -1;
        for (int i = 0; i < this.matrizAdjacencia.length; i++) {
            int vizinhos = 0;
            for (int j = 0; j < this.matrizAdjacencia[i].length; j++) {
                if (this.matrizAdjacencia[i][j] == 1) {
                    vizinhos++;
                }
            }
            if (vizinhos < minVizinhos) {
                minVizinhos = vizinhos;
                minEstado = i;
            }
        }
        
        return minEstado;
        
    }
    
    /**
    * Busca o estado que possui mais vizinhos
    * @return
    */
    public int maisVizinhos() {
        
        // Encontra o estado com o maior número de estados vizinhos
        int maxVizinhos = Integer.MIN_VALUE;
        int maxEstado = -1;
        for (int i = 0; i < this.matrizAdjacencia.length; i++) {
            int vizinhos = 0;
            for (int j = 0; j < this.matrizAdjacencia[i].length; j++) {
                if (this.matrizAdjacencia[i][j] == 1) {
                    vizinhos++;
                }
            }
            if (vizinhos > maxVizinhos) {
                maxVizinhos = vizinhos;
                maxEstado = i;
            }
        }
        
        return maxEstado;
    }
    
    /**
    * Busca o menor caminho entre dois dados estados
    * @param startState
    * @param endState
    */
    public void menorCaminho(int startState, int endState) {
        
        // Fila para armazenar os estados que serão visitados
        Queue<Integer> queue = new LinkedList<>();
        
        int[] path = new int[this.matrizAdjacencia.length];
        
        // marca o estado inicial como visitado
        boolean[] visited = new boolean[this.matrizAdjacencia.length];
        visited[startState] = true;
        
        queue.add(startState);        
        while (!queue.isEmpty()) {
            int state = queue.remove();
            
            // para cada vizinho do estado
            for (int i = 0; i < this.matrizAdjacencia[state].length; i++) {
                if (this.matrizAdjacencia[state][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                    path[i] = state;
                    
                    if (i == endState) {
                        break;
                    }
                }
            }
        }
        // percorre o caminho do estado final até o inicial, imprimindo cada estado
        System.out.print("Menor caminho entre dois estados: ");
        int state = endState;
        while (state != startState) {
            System.out.print(state + " -> ");
            state = path[state];
        }
        System.out.println(startState);
        
    }
    
    /**
    * Busca todos os caminhos entre dois dados estados
    * @param startState
    * @param endState
    */
    public void encontrarTodosOsCaminhos(int startState, int endState) {
        
        // pilha para armazenar os estados que serão visitados
        Stack<Integer> stack = new Stack<>();
        
        int[] path = new int[this.matrizAdjacencia.length];
        
        // marca o estado inicial como visitado
        boolean[] visited = new boolean[this.matrizAdjacencia.length];
        visited[startState] = true;
        
        stack.push(startState);
        while (!stack.isEmpty()) {
            int state = stack.pop();
            
            for (int i = 0; i < this.matrizAdjacencia[state].length; i++) {
                if (this.matrizAdjacencia[state][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    stack.push(i);
                    path[i] = state;
                    
                    if (i == endState) {
                        System.out.print("Caminho possível: ");
                        state = endState;
                        while (state != startState) {
                            System.out.print(state + " -> ");
                            state = path[state];
                        }
                        System.out.println(startState);
                        
                        // volta ao estado anterior e continua a busca
                        stack.pop();
                        visited[i] = false;
                    }
                }
            }
        }
        
    }
    
    public void construir() {
        
        // Adiciona os vértices (os estados brasileiros) ao grafo
        this.adicionarVertice("Acre"); // 0
        this.adicionarVertice("Alagoas"); // 1
        this.adicionarVertice("Amapá"); // 2
        this.adicionarVertice("Amazonas"); // 3
        this.adicionarVertice("Bahia"); // 4
        this.adicionarVertice("Ceará"); // 5
        this.adicionarVertice("Distrito Federal"); // 6
        this.adicionarVertice("Espírito Santo"); // 7
        this.adicionarVertice("Goiás"); // 8
        this.adicionarVertice("Maranhão"); // 9
        this.adicionarVertice("Mato Grosso"); // 10
        this.adicionarVertice("Mato Grosso do Sul"); // 11
        this.adicionarVertice("Minas Gerais"); // 12
        this.adicionarVertice("Pará"); // 13
        this.adicionarVertice("Paraíba"); // 14
        this.adicionarVertice("Paraná"); // 15
        this.adicionarVertice("Pernambuco"); // 16
        this.adicionarVertice("Piauí"); // 17
        this.adicionarVertice("Rio de Janeiro"); // 18
        this.adicionarVertice("Rio Grande do Norte"); // 19
        this.adicionarVertice("Rio Grande do Sul"); // 20
        this.adicionarVertice("Rondônia"); // 21
        this.adicionarVertice("Roraima"); // 22
        this.adicionarVertice("Santa Catarina"); // 23
        this.adicionarVertice("São Paulo"); // 24
        this.adicionarVertice("Sergipe"); // 25
        this.adicionarVertice("Tocantins"); // 26
        
        // Adiciona a aresta entre o Acre e o Amazonas
        this.adicionarAresta(0, 3);
        
        // Adiciona a aresta entre Amazonas e Roraima
        this.adicionarAresta(3, 22);
        
        // Adiciona a aresta entre Amazonas e Pará
        this.adicionarAresta(3, 13);
        
        // Adiciona a aresta entre Amazonas e Rondônia
        this.adicionarAresta(3, 21);
        
        // Adiciona a aresta entre Amazonas e Rondônia
        this.adicionarAresta(3, 21);
        
        // Adiciona a aresta entre Roraima e Pará
        this.adicionarAresta(22, 13);
        
        // Adiciona a aresta entre Roraima e Pará
        this.adicionarAresta(22, 13);
        
        // Adiciona a aresta entre Rondônia e Mato Grosso
        this.adicionarAresta(21, 10);
        
        // Adiciona a aresta entre Pará e Amapá
        this.adicionarAresta(13, 2);
        
        // Adiciona a aresta entre Pará e Mato Grosso
        this.adicionarAresta(13, 10);
        
        // Adiciona a aresta entre Pará e Maranhão
        this.adicionarAresta(13, 9);
        
        // Adiciona a aresta entre Pará e Tocantins
        this.adicionarAresta(13, 26);
        
        // Adiciona a aresta entre Mato Grosso e Tocantins
        this.adicionarAresta(10, 26);
        
        // Adiciona a aresta entre Mato Grosso e Goiás
        this.adicionarAresta(10, 8);
        
        // Adiciona a aresta entre Mato Grosso e Mato Grosso do Sul
        this.adicionarAresta(10, 11);
        
        // Adiciona a aresta entre Maranhão e Piauí
        this.adicionarAresta(9, 17);
        
        // Adiciona a aresta entre Maranhão e Tocantins
        this.adicionarAresta(9, 26);
        
        // Adiciona a aresta entre Maranhão e Piauí
        this.adicionarAresta(9, 17);
        
        // Adiciona a aresta entre Piauí e Ceará
        this.adicionarAresta(17, 5);
        
        // Adiciona a aresta entre Ceará e Rio Grande do Norte
        this.adicionarAresta(5, 19);
        
        // Adiciona a aresta entre Ceará e Paraíba
        this.adicionarAresta(5, 14);
        
        // Adiciona a aresta entre Ceará e Pernambuco
        this.adicionarAresta(5, 16);
        
        // Adiciona a aresta entre Rio Grande do Norte e Paraíba
        this.adicionarAresta(19, 14);
        
        // Adiciona a aresta entre Paraíba e Pernambuco
        this.adicionarAresta(14, 16);
        
        // Adiciona a aresta entre Pernambuco e Alagoas
        this.adicionarAresta(16, 1);
        
        // Adiciona a aresta entre Pernambuco e Bahia
        this.adicionarAresta(16, 4);
        
        // Adiciona a aresta entre Pernambuco e Piauí
        this.adicionarAresta(16, 17);
        
        // Adiciona a aresta entre Alagoas e Sergipe
        this.adicionarAresta(1, 25);
        
        // Adiciona a aresta entre Sergipe e Bahia
        this.adicionarAresta(25, 4);
        
        // Adiciona a aresta entre Bahia e Minas Gerais
        this.adicionarAresta(4, 12);
        
        // Adiciona a aresta entre Bahia e Piauí
        this.adicionarAresta(4, 17);
        
        // Adiciona a aresta entre Bahia e Tocantins
        this.adicionarAresta(4, 26);
        
        // Adiciona a aresta entre Bahia e Espírito Santo
        this.adicionarAresta(4, 7);
        
        // Adiciona a aresta entre Bahia e Goiás
        this.adicionarAresta(4, 8);
        
        // Adiciona a aresta entre Goiás e Tocantins
        this.adicionarAresta(8, 26);
        
        // Adiciona a aresta entre Goiás e Minas Gerais
        this.adicionarAresta(8, 12);
        
        // Adiciona a aresta entre Goiás e Distrito Federal
        this.adicionarAresta(8, 6);
        
        // Adiciona a aresta entre Distrito Federal e Minas Gerais
        this.adicionarAresta(6, 12);
        
        // Adiciona a aresta entre Minas Gerais e Espírito Santo
        this.adicionarAresta(12, 7);
        
        // Adiciona a aresta entre Minas Gerais e Rio de Janeiro
        this.adicionarAresta(12, 18);
        
        // Adiciona a aresta entre Minas Gerais e São Paulo
        this.adicionarAresta(12, 24);
        
        // Adiciona a aresta entre Minas Gerais e Mato Grosso
        this.adicionarAresta(12, 10);
        
        // Adiciona a aresta entre Rio de Janeiro e Espírito Santo
        this.adicionarAresta(18, 7);
        
        // Adiciona a aresta entre Rio de Janeiro e São Paulo
        this.adicionarAresta(18, 24);
        
        // Adiciona a aresta entre Mato Grosso e São Paulo
        this.adicionarAresta(10, 24);
        
        // Adiciona a aresta entre São Paulo e Paraná
        this.adicionarAresta(24, 15);
        
        // Adiciona a aresta entre Paraná e Santa Catarina
        this.adicionarAresta(15, 23);
        
        // Adiciona a aresta entre Paraná e Mato Grosso do Sul
        this.adicionarAresta(15, 11);
        
        // Adiciona a aresta entre Santa Catarina e Rio Grande do Sul
        this.adicionarAresta(15, 20);
        
    }
    
    public static void main(String args[]) {

        // Cria um grafo com o número de vértices igual ao número de estados brasileiros
        Grafo grafo = new Grafo(27);
        grafo.construir();

        int min = grafo.menosVizinhos(), max = grafo.maisVizinhos();
        System.out.println("Estado com o menor número de vizinhos: " + grafo.vertices.get(min));
        System.out.println("Estado com o maior número de vizinhos: " + grafo.vertices.get(max));

        grafo.menorCaminho(2, 17);
        grafo.encontrarTodosOsCaminhos(2, 17);

    }
    
}
