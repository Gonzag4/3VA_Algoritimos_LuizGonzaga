package com.application;

// Esta classe representa um lutador do jogo
// Armazena todos os atributos de um lutador: vida, dano, iniciativa, etc.
class Lutador {
    String identificador;      // ID único do lutador (não pode se repetir)
    int time;
    int dano;
    int pontosVida;
    int iniciativa;            // (1-100)
    boolean atacouNesteTurno;  // Flag que indica se o lutador já atacou no turno atual

    // Construtor inicializa um novo lutador com os atributos fornecidos (usando por padão da linguagem e do POO)
    public Lutador(String identificador, int time, int dano, int pontosVida, int iniciativa) {
        this.identificador = identificador;
        this.time = time;
        this.dano = dano;
        this.pontosVida = pontosVida;
        this.iniciativa = iniciativa;
        this.atacouNesteTurno = false;  // Inicialmente não atacou no turno
    }

    // Verifica se o lutador está vivo (pontos de vida maiores que zero)
    public boolean estaVivo() {
        return pontosVida > 0;
    }
}