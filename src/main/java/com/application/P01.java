package com.application;
import java.util.Scanner;

public class P01 {
    private static Time time1;
    private static Time time2;
    private static Scanner scanner;      // Scanner para entrada do usuário
    private static int turnoAtual;       // Contador de turnos

    // Método principal do programa
    public static void main(String[] args) {
        // Inicialização dos times
        time1 = new Time(1);
        time2 = new Time(2);
        scanner = new Scanner(System.in);
        turnoAtual = 1;  // Turno inicial

        System.out.println(" JOOGO DE AÇÃO BASEADA EM TURNOS \n");

        boolean jogoFinalizado = false;

        // Loop principal do jogo - cada iteração é um turno completo
        while (!jogoFinalizado) {
            faseOrganizacao();    // Fase 1: organização dos times
            faseCombate();        // Fase 2: combate automático
            jogoFinalizado = faseResultados();  // Fase 3: verificação de resultados

            if (!jogoFinalizado) {
                turnoAtual++;  // Avança para próximo turno
            }
        }
        scanner.close();
    }

    // Verifica se um identificador já existe no jogo
    // Busca em lutadores vivos e mortos de ambos os times
    public static boolean identificadorExiste(String id) {
        // Verifica lutadores vivos do time 1
        if (time1.lutadoresVivos.buscar(id) != null) return true;

        // Verifica lutadores vivos do time 2
        if (time2.lutadoresVivos.buscar(id) != null) return true;

        // Verifica cemitério do time 1
        for (int i = 0; i < time1.cemiterio.obterTamanho(); i++) {
            if (time1.cemiterio.obterPorIndice(i).identificador.equals(id)) return true;
        }

        // Verifica cemitério do time 2
        for (int i = 0; i < time2.cemiterio.obterTamanho(); i++) {
            if (time2.cemiterio.obterPorIndice(i).identificador.equals(id)) return true;
        }
        return false;
    }

    // FASE 1: ORGANIZAÇÃO DOS TIMES
    // Interface interativa para gerenciamento pré-combate
    public static void faseOrganizacao() {
        System.out.println("TURNO " + turnoAtual + " - ORGANIZACAO DOS TIMES");

        boolean continuar = true;

        // Menu interativo
        while (continuar) {
            System.out.println("\n1. Inserir lutador");
            System.out.println("2. Relatorio de status de um time");
            System.out.println("3. Fuga de lutador");
            System.out.println("4. Iniciar combate");
            System.out.print("Escolha: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();  // Limpa buffer

            if (opcao == 1) {
                inserirLutador();
            } else if (opcao == 2) {
                relatorioStatus();
            } else if (opcao == 3) {
                fugaLutador();
            } else if (opcao == 4) {
                continuar = false;  // Sai do menu
            } else {
                System.out.println("Opcao invalida! Tente novamente.");
            }
        }

        // Prepara filas ordenadas por iniciativa
        time1.organizarFila();
        time2.organizarFila();
        System.out.println("\nTimes organizados! Iniciando combate...");
    }

    // Insere novo lutador com validações
    public static void inserirLutador() {
        System.out.print("Identificador: ");
        String id = scanner.nextLine();

        // Valida ID único
        if (identificadorExiste(id)) {
            System.out.println("ERRO: Ja existe um lutador com este identificador!");
            return;
        }

        System.out.print("Time (1 ou 2): ");
        int time = scanner.nextInt();

        // Valida número do time
        if (time != 1 && time != 2) {
            System.out.println("ERRO: Time deve ser 1 ou 2!");
            scanner.nextLine();
            return;
        }

        System.out.print("Dano: ");
        int dano = scanner.nextInt();

        System.out.print("Pontos de Vida: ");
        int vida = scanner.nextInt();

        System.out.print("Iniciativa (1-100): ");
        int iniciativa = scanner.nextInt();
        scanner.nextLine();  // Limpa buffer

        // Valida intervalo da iniciativa
        if (iniciativa < 1 || iniciativa > 100) {
            System.out.println("ERRO: Iniciativa deve estar entre 1 e 100!");
            return;
        }

        // Cria e adiciona o lutador
        Lutador novoLutador = new Lutador(id, time, dano, vida, iniciativa);

        if (time == 1) {
            time1.lutadoresVivos.adicionar(novoLutador);
        } else {
            time2.lutadoresVivos.adicionar(novoLutador);
        }

        System.out.println("Lutador inserido com sucesso no Time " + time + "!");
    }

    // Exibe status de um time específico
    public static void relatorioStatus() {
        System.out.print("Numero do time (1 ou 2): ");
        int numTime = scanner.nextInt();
        scanner.nextLine();

        if (numTime == 1) {
            time1.exibirStatus();
        } else if (numTime == 2) {
            time2.exibirStatus();
        } else {
            System.out.println("ERRO: Time deve ser 1 ou 2!");
        }
    }

    // Remove lutador vivo (fuga)
    public static void fugaLutador() {
        System.out.print("Identificador do lutador: ");
        String id = scanner.nextLine();

        // Procura no time 1
        Lutador lutador = time1.lutadoresVivos.buscar(id);
        if (lutador != null && lutador.estaVivo()) {
            time1.lutadoresVivos.remover(id);
            System.out.println("Lutador " + id + " fugiu do combate!");
            return;
        }

        // Procura no time 2
        lutador = time2.lutadoresVivos.buscar(id);
        if (lutador != null && lutador.estaVivo()) {
            time2.lutadoresVivos.remover(id);
            System.out.println("Lutador " + id + " fugiu do combate!");
            return;
        }

        System.out.println("ERRO: Lutador nao encontrado ou ja esta morto!");
    }

    // FASE 2: COMBATE
    // Executa combates entre pares de lutadores
    public static void faseCombate() {
        System.out.println("TURNO " + turnoAtual + " - FASE DE COMBATE");

        // Reseta flag de ataque para novo turno
        for (int i = 0; i < time1.lutadoresVivos.obterTamanho(); i++) {
            time1.lutadoresVivos.obterPorIndice(i).atacouNesteTurno = false;
        }
        for (int i = 0; i < time2.lutadoresVivos.obterTamanho(); i++) {
            time2.lutadoresVivos.obterPorIndice(i).atacouNesteTurno = false;
        }

        // Loop de combate
        while (!time1.filaOrdenada.estaVazia() && !time2.filaOrdenada.estaVazia()) {
            Lutador lutador1 = time1.filaOrdenada.desenfileirar();
            Lutador lutador2 = time2.filaOrdenada.desenfileirar();

            System.out.println("COMBATE: " + lutador1.identificador + " (Time 1) vs " +
                    lutador2.identificador + " (Time 2)");

            // Ataques simultâneos
            if (!lutador1.atacouNesteTurno) {
                lutador2.pontosVida = lutador2.pontosVida - lutador1.dano;
                lutador1.atacouNesteTurno = true;
                System.out.println("  " + lutador1.identificador + " causa " +
                        lutador1.dano + " de dano");
            }

            if (!lutador2.atacouNesteTurno) {
                lutador1.pontosVida = lutador1.pontosVida - lutador2.dano;
                lutador2.atacouNesteTurno = true;
                System.out.println("  " + lutador2.identificador + " causa " +
                        lutador2.dano + " de dano");
            }

            // Verifica mortes
            boolean morreu1 = !lutador1.estaVivo();
            boolean morreu2 = !lutador2.estaVivo();

            if (morreu1) {
                System.out.println("  " + lutador1.identificador + " morreu!");
                time1.lutadoresVivos.remover(lutador1.identificador);
                time1.cemiterio.inserir(lutador1);
            }

            if (morreu2) {
                System.out.println("  " + lutador2.identificador + " morreu!");
                time2.lutadoresVivos.remover(lutador2.identificador);
                time2.cemiterio.inserir(lutador2);
            }

            // Reinsere se ainda vivo
            if (!morreu1) {
                time1.filaOrdenada.enfileirar(lutador1);
            }

            if (!morreu2) {
                time2.filaOrdenada.enfileirar(lutador2);
            }

            System.out.println();
        }

        System.out.println("Combate finalizado!");
    }

    // FASE 3: RESULTADOS
    // Verifica condições de término
    public static boolean faseResultados() {
        System.out.println("TURNO " + turnoAtual + " - FASE DE RESULTADOS");

        int vivos1 = time1.lutadoresVivos.obterTamanho();
        int vivos2 = time2.lutadoresVivos.obterTamanho();

        int score1 = time2.cemiterio.obterTamanho();
        int score2 = time1.cemiterio.obterTamanho();

        System.out.println("Time 1 - Vivos: " + vivos1 + " | Score: " + score1);
        System.out.println("Time 2 - Vivos: " + vivos2 + " | Score: " + score2);
        System.out.println();

        // Condições de vitória
        if (vivos1 > 0 && vivos2 == 0) {
            System.out.println("TIME 1 VENCEU!");
            return true;
        }
        if (vivos2 > 0 && vivos1 == 0) {
            System.out.println("TIME 2 VENCEU!");
            return true;
        }

        if (vivos1 > 0 && vivos2 > 0) {
            if (score1 >= 20 && score2 < 20) {
                System.out.println("TIME 1 VENCEU! (Score >= 20)");
                return true;
            }
            if (score2 >= 20 && score1 < 20) {
                System.out.println("TIME 2 VENCEU! (Score >= 20)");
                return true;
            }

            if (score1 > 20 && score2 > 20) {
                if (score1 > score2) {
                    System.out.println("TIME 1 VENCEU! (Maior score)");
                    return true;
                } else {
                    System.out.println("TIME 2 VENCEU! (Maior score)");
                    return true;
                }
            }
        }

        if (vivos1 == 0 && vivos2 == 0) {
            if (score1 > score2) {
                System.out.println("TIME 1 VENCEU! (Maior score)");
                return true;
            } else if (score2 > score1) {
                System.out.println("TIME 2 VENCEU! (Maior score)");
                return true;
            } else {
                System.out.println("JOGO EMPATADO!");
                return true;
            }
        }

        System.out.println("O jogo continua...\n");
        return false;
    }
}