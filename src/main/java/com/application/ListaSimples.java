package com.application;

// Esta classe implementa uma lista simples utilizando array estático
// Utilizada para armazenar os lutadores VIVOS de cada time
class ListaSimples {
    private Lutador[] dados;   // Array estático que armazena os lutadores
    private int tamanho;       // Quantidade atual de lutadores na lista

    // Construtor: inicializa a lista com capacidade fixa
    public ListaSimples(int capacidade) {
        this.dados = new Lutador[capacidade];
        this.tamanho = 0;
    }

    // Adiciona um lutador ao final da lista
    public void adicionar(Lutador lutador) {
        // Insere o lutador na próxima posição disponível do array
        dados[tamanho] = lutador;
        tamanho++;
    }

    // Remove um lutador da lista utilizando seu identificador
    // Mantém a continuidade do array deslocando elementos para a esquerda
    public void remover(String identificador) {
        // Procura a posição do lutador na lista
        for (int i = 0; i < tamanho; i++) {
            if (dados[i].identificador.equals(identificador)) {
                // Desloca todos os elementos à direita para a esquerda
                for (int j = i; j < tamanho - 1; j++) {
                    dados[j] = dados[j + 1];
                }

                // Limpa a última posição (agora duplicada)
                dados[tamanho - 1] = null;

                // Atualiza o tamanho da lista
                tamanho--;
                return;  // Encerra após encontrar e remover
            }
        }
    }

    // Busca um lutador pelo identificador e retorna sua referência
    // Retorna null se o lutador não for encontrado
    public Lutador buscar(String identificador) {
        for (int i = 0; i < tamanho; i++) {
            if (dados[i].identificador.equals(identificador)) {
                return dados[i];  // Retorna o lutador encontrado
            }
        }
        return null;  // Lutador não encontrado
    }

    // Retorna a quantidade atual de lutadores na lista
    public int obterTamanho() {
        return tamanho;
    }

    // Retorna o lutador localizado em um índice específico
    // Útil para iteração sobre todos os lutadores
    public Lutador obterPorIndice(int indice) {
        return dados[indice];
    }
}