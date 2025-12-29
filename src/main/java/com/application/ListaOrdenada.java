package com.application;

// Esta classe implementa uma lista ordenada utilizando array estático
// Utilizada para armazenar os lutadores mortos (cemitério)
// Mantém automaticamente os elementos em ordem decrescente de iniciativa
class ListaOrdenada {
    private Lutador[] dados;   // Array estático que armazena os lutadores mortos
    private int tamanho;       // Quantidade atual de lutadores na lista

    // Construtor: inicializa a lista com capacidade fixa
    public ListaOrdenada(int capacidade) {
        this.dados = new Lutador[capacidade];
        this.tamanho = 0;
    }

    // Insere um lutador na posição correta para manter a ordem decrescente de iniciativa
    // Utiliza inserção ordenada (insertion sort) para uma inserção eficiente
    public void inserir(Lutador lutador) {
        // Encontra a posição correta para inserção
        // Percorre enquanto encontrar iniciativas maiores que a do novo lutador
        int posicao = 0;
        while (posicao < tamanho && dados[posicao].iniciativa > lutador.iniciativa) {
            posicao++;
        }

        // Abre espaço deslocando elementos para a direita
        // Começa do final e move cada elemento uma posição à direita
        for (int i = tamanho; i > posicao; i--) {
            dados[i] = dados[i - 1];
        }

        // Insere o novo lutador na posição correta
        dados[posicao] = lutador;

        // Incrementa o contador de tamanho
        tamanho++;
    }

    // Retorna a quantidade atual de lutadores na lista
    public int obterTamanho() {
        return tamanho;
    }

    // Retorna o lutador localizado em um índice específico
    // Como a lista está sempre ordenada, o acesso por índice é direto
    public Lutador obterPorIndice(int indice) {
        return dados[indice];
    }
}