package com.application;

// Esta classe implementa uma fila (estrutura FIFO) utilizando array estático
// Utilizada para controlar a ordem do combate de acordo com a iniciativa
// Implementa fila circular para eficiência de espaço
class Fila {
    private Lutador[] dados;   // Array que armazena os lutadores (implementação circular)
    private int capacidade;    // Capacidade máxima da fila
    private int frente;        // Índice do primeiro elemento da fila
    private int tras;          // Índice do último elemento da fila
    private int tamanho;       // Quantidade atual de elementos na fila

    // Construtor: inicializa a fila com capacidade específica
    public Fila(int capacidade) {
        this.capacidade = capacidade;
        this.dados = new Lutador[capacidade];  // Cria array com tamanho fixo
        this.frente = 0;                       // Primeiro elemento começa na posição 0
        this.tras = -1;                        // Fila inicialmente vazia
        this.tamanho = 0;                      // Nenhum elemento inicialmente
    }

    // Verifica se a fila está vazia
    public boolean estaVazia() {
        return tamanho == 0;
    }

    // Adiciona um lutador ao final da fila
    // Implementa comportamento circular para reutilização de espaço
    public void enfileirar(Lutador lutador) {
        // Avança o índice traseiro com comportamento circular
        tras = (tras + 1) % capacidade;

        // Insere o lutador na posição traseira
        dados[tras] = lutador;

        // Incrementa o contador de tamanho
        tamanho++;
    }

    // Remove e retorna o lutador da frente da fila
    // Implementa comportamento circular para eficiência
    public Lutador desenfileirar() {
        // Verifica se a fila está vazia
        if (estaVazia()) {
            return null;  // Retorna null se não houver elementos
        }

        // Obtém o lutador da frente da fila
        Lutador lutador = dados[frente];

        // Limpa a posição (opcional, mas ajuda na depuração)
        dados[frente] = null;

        // Avança o índice frontal com comportamento circular
        frente = (frente + 1) % capacidade;

        // Decrementa o contador de tamanho
        tamanho--;

        return lutador;  // Retorna o lutador removido
    }

    // Retorna a quantidade atual de elementos na fila
    // Método utilizado apenas para testes no TesteP01
    public int obterTamanho() {
        return tamanho;
    }
}