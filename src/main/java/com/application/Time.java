package com.application;

// Esta classe representa um time do jogo
// Armazena os lutadores vivos, o cemitério (lutadores mortos)
// e a fila ordenada utilizada durante o combate
class Time {
    int numero;                      // Número identificador do time (1 ou 2)
    ListaSimples lutadoresVivos;
    ListaOrdenada cemiterio;
    Fila filaOrdenada;              // Fila utilizada exclusivamente durante o combate (ordenada por iniciativa)

    // Construtor: inicializa um time com suas listas vazias
    // Capacidade definida como 1000 para uso com arrays estáticos
    public Time(int numero) {
        this.numero = numero;
        this.lutadoresVivos = new ListaSimples(1000);
        this.cemiterio = new ListaOrdenada(1000);
        this.filaOrdenada = new Fila(1000);            // Fila para controle do combate
    }

    // Organiza os lutadores vivos em uma fila ordenada por iniciativa (decrescente)
    // Este método deve ser chamado antes do início de cada combate
    public void organizarFila() {
        // Cria uma nova fila para garantir que esteja vazia antes do combate
        filaOrdenada = new Fila(1000);

        int n = lutadoresVivos.obterTamanho();
        Lutador[] temp = new Lutador[n];  // Array temporário para ordenação

        // Copia os lutadores vivos para o array temporário
        for (int i = 0; i < n; i++) {
            temp[i] = lutadoresVivos.obterPorIndice(i);
        }

        // Ordena o array por iniciativa (maior para menor) utilizando Merge Sort

        mergeSort(temp, 0, n - 1);

        // Insere todos os lutadores ordenados na fila
        for (int i = 0; i < n; i++) {
            filaOrdenada.enfileirar(temp[i]);
        }
    }

    // Método principal do Merge Sort (implementação recursiva)
    // Divide o array em duas metades e ordena cada uma recursivamente
    private void mergeSort(Lutador[] array, int esquerda, int direita) {
        // Caso base: se a sublista tem 0 ou 1 elemento, já está ordenada
        if (esquerda >= direita) {
            return;
        }

        // Calcula o ponto médio do array
        int meio = esquerda + (direita - esquerda) / 2;

        // Ordena a metade esquerda
        mergeSort(array, esquerda, meio);

        // Ordena a metade direita
        mergeSort(array, meio + 1, direita);

        // Combina (merge) as duas metades ordenadas
        merge(array, esquerda, meio, direita);
    }

    // Método que combina (merge) duas partes ordenadas do array
    // Mantém a ordem decrescente baseada na iniciativa
    private void merge(Lutador[] array, int esquerda, int meio, int direita) {
        // Calcula os tamanhos das duas sublistas
        int n1 = meio - esquerda + 1;  // Tamanho da parte esquerda
        int n2 = direita - meio;        // Tamanho da parte direita

        // Cria arrays temporários para armazenar as duas partes
        Lutador[] arrayEsquerda = new Lutador[n1];
        Lutador[] arrayDireita = new Lutador[n2];

        // Copia os dados para o array temporário da esquerda
        for (int i = 0; i < n1; i++) {
            arrayEsquerda[i] = array[esquerda + i];
        }

        // Copia os dados para o array temporário da direita
        for (int j = 0; j < n2; j++) {
            arrayDireita[j] = array[meio + 1 + j];
        }

        // Índices para percorrer os arrays temporários
        int i = 0;      // Índice do array esquerda
        int j = 0;      // Índice do array direita
        int k = esquerda;  // Índice do array original

        // Combina os dois arrays temporários no array original
        // em ordem decrescente de iniciativa (maior valor primeiro)
        while (i < n1 && j < n2) {
            // Se o lutador da esquerda tem iniciativa MAIOR ou IGUAL, insere primeiro
            if (arrayEsquerda[i].iniciativa >= arrayDireita[j].iniciativa) {
                array[k] = arrayEsquerda[i];
                i++;
            } else {
                // Caso contrário insere o lutador da direita
                array[k] = arrayDireita[j];
                j++;
            }
            k++;
        }

        // Copia os elementos restantes do array esquerda, se houver
        while (i < n1) {
            array[k] = arrayEsquerda[i];
            i++;
            k++;
        }

        // Copia os elementos restantes do array direita, se houver
        while (j < n2) {
            array[k] = arrayDireita[j];
            j++;
            k++;
        }
    }

    // Exibe o status atual do time (lutadores vivos e mortos)
    // Atende aos requisitos especificados no enunciado
    public void exibirStatus() {
        System.out.println("\n TIME " + numero );
        System.out.println("Lutadores Vivos: " + lutadoresVivos.obterTamanho());
        System.out.println("Lutadores Mortos: " + cemiterio.obterTamanho());

        // Exibe lutadores VIVOS em ordem decrescente de iniciativa
        System.out.println("\nLutadores Vivos (ordem decrescente de iniciativa)");
        int n = lutadoresVivos.obterTamanho();

        if (n == 0) {
            // Caso o time não possua lutadores vivos
            System.out.println("Nenhum lutador vivo.");
        } else {
            // Copia para um array temporário para ordenação
            Lutador[] temp = new Lutador[n];
            for (int i = 0; i < n; i++) {
                temp[i] = lutadoresVivos.obterPorIndice(i);
            }

            // Ordena utilizando Merge Sort (complexidade O(n log n) garantida)
            mergeSort(temp, 0, n - 1);

            // Imprime os resultados conforme especificado
            for (int i = 0; i < n; i++) {
                System.out.println("ID: " + temp[i].identificador +
                        " | Iniciativa: " + temp[i].iniciativa +
                        " | Vida: " + temp[i].pontosVida);
            }
        }

        // Exibe lutadores MORTOS (o cemitério já mantém a ordem automaticamente)
        System.out.println("\nLutadores Mortos (ordem decrescente de iniciativa)");
        if (cemiterio.obterTamanho() == 0) {
            System.out.println("Nenhum lutador morto.");
        } else {
            for (int i = 0; i < cemiterio.obterTamanho(); i++) {
                Lutador l = cemiterio.obterPorIndice(i);
                System.out.println("ID: " + l.identificador +
                        " | Iniciativa: " + l.iniciativa +
                        " | Vida: " + l.pontosVida);
            }
        }
    }
}















