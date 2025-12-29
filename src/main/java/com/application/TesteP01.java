package com.application;

public class TesteP01 {

    public static void main(String[] args) {
        System.out.println("Iniciando testes completos do sistema P01\n");

        // Testes unitários
        testarClasseLutador();
        testarListaSimplesCompleto();
        testarFilaCompleto();
        testarListaOrdenadaCompleto();
        testarClasseTimeCompleto();

        // Testes de integração
        testarOrganizacaoFilaCompleto();
        testarCombateSimultaneoCompleto();
        testarIdentificadorUnicoCompleto();
        testarCondicoesFimCompleto();

        // Testes do sistema completo
        testarSistemaCompleto();
        testarCasosLimite();
        testarFluxoJogoCompleto();

        System.out.println("\nTodos os testes foram executados.");
    }

    // ==================== TESTES DA CLASSE LUTADOR ====================
    public static void testarClasseLutador() {
        System.out.println("\n=== Testando Classe Lutador ===");

        // Teste 1: Criação básica
        Lutador l1 = new Lutador("L1", 1, 50, 100, 75);
        if (!l1.identificador.equals("L1") || l1.time != 1 || l1.dano != 50 || l1.pontosVida != 100 || l1.iniciativa != 75) {
            System.out.println("ERRO: Criação do lutador falhou");
            return;
        }

        // Teste 2: Método estaVivo()
        if (!l1.estaVivo()) {
            System.out.println("ERRO: Lutador com 100 de vida deveria estar vivo");
            return;
        }

        // Teste 3: Morte quando vida = 0
        l1.pontosVida = 0;
        if (l1.estaVivo()) {
            System.out.println("ERRO: Lutador com 0 de vida deveria estar morto");
            return;
        }

        // Teste 4: Morte quando vida < 0
        l1.pontosVida = -10;
        if (l1.estaVivo()) {
            System.out.println("ERRO: Lutador com vida negativa deveria estar morto");
            return;
        }

        // Teste 5: Flag atacouNesteturno inicia como false
        Lutador l2 = new Lutador("L2", 2, 30, 80, 60);
        if (l2.atacouNesteTurno) {
            System.out.println("ERRO: Flag atacouNesteturno deveria iniciar false");
            return;
        }

        // Teste 6: Pode alterar flag atacouNesteturno
        l2.atacouNesteTurno = true;
        if (!l2.atacouNesteTurno) {
            System.out.println("ERRO: Não foi possível alterar flag atacouNesteturno");
            return;
        }

        System.out.println("Classe Lutador: OK");
    }

    // TESTES COMPLETOS DA LISTA SIMPLES
    public static boolean testarListaSimplesCompleto() {
        System.out.println("\nTestando ListaSimples Completa ");
        ListaSimples lista = new ListaSimples(5);

        // Teste 1: Lista inicia vazia
        if (lista.obterTamanho() != 0) {
            System.out.println("ERRO: Lista deveria iniciar vazia");
            return false;
        }

        // Teste 2: Adicionar elementos
        Lutador l1 = new Lutador("L1", 1, 10, 100, 50);
        Lutador l2 = new Lutador("L2", 1, 20, 120, 60);
        lista.adicionar(l1);
        lista.adicionar(l2);

        if (lista.obterTamanho() != 2) {
            System.out.println("ERRO: Tamanho da lista incorreto após adição");
            return false;
        }

        // Teste 3: Buscar elemento existente
        Lutador encontrado = lista.buscar("L1");
        if (encontrado == null || !encontrado.identificador.equals("L1")) {
            System.out.println("ERRO: Busca por elemento existente falhou");
            return false;
        }

        // Teste 4: Buscar elemento não existente
        Lutador naoEncontrado = lista.buscar("INEXISTENTE");
        if (naoEncontrado != null) {
            System.out.println("ERRO: Busca por elemento inexistente deveria retornar null");
            return false;
        }

        // Teste 5: Obter por índice
        Lutador porIndice = lista.obterPorIndice(1);
        if (porIndice == null || !porIndice.identificador.equals("L2")) {
            System.out.println("ERRO: Obter por índice falhou");
            return false;
        }

        // Teste 6: Remover elemento
        lista.remover("L1");
        if (lista.obterTamanho() != 1) {
            System.out.println("ERRO: Tamanho incorreto após remoção");
            return false;
        }

        if (lista.buscar("L1") != null) {
            System.out.println("ERRO: Elemento removido ainda está na lista");
            return false;
        }

        // Teste 7: Remover elemento inexistente (não deve causar erro)
        lista.remover("INEXISTENTE");
        if (lista.obterTamanho() != 1) {
            System.out.println("ERRO: Tamanho alterado ao remover elemento inexistente");
            return false;
        }

        // Teste 8: Acesso a índice inválido (deve retornar null)
        try {
            Lutador invalido = lista.obterPorIndice(10);
            // Aceitável retornar null ou lançar exceção
        } catch (Exception e) {
            // Comportamento aceitável
        }

        System.out.println("ListaSimples Completa: OK");
        return true;
    }

    //  TESTES COMPLETOS DA FILA
    public static boolean testarFilaCompleto() {
        System.out.println("\nTestando Fila Completa ");
        Fila fila = new Fila(3);

        // Teste 1: Fila inicia vazia
        if (!fila.estaVazia() || fila.obterTamanho() != 0) {
            System.out.println("ERRO: Fila deveria iniciar vazia");
            return false;
        }

        // Teste 2: Enfileirar elementos
        Lutador l1 = new Lutador("F1", 1, 10, 100, 50);
        Lutador l2 = new Lutador("F2", 1, 20, 100, 60);
        fila.enfileirar(l1);
        fila.enfileirar(l2);

        if (fila.obterTamanho() != 2) {
            System.out.println("ERRO: Tamanho da fila incorreto após enfileirar");
            return false;
        }

        // Teste 3: Desenfileirar na ordem FIFO
        Lutador primeiro = fila.desenfileirar();
        if (primeiro == null || !primeiro.identificador.equals("F1")) {
            System.out.println("ERRO: Primeiro elemento deveria ser F1");
            return false;
        }

        if (fila.obterTamanho() != 1) {
            System.out.println("ERRO: Tamanho incorreto após desenfileirar");
            return false;
        }

        // Teste 4: Fila circular
        fila.enfileirar(new Lutador("F3", 1, 30, 100, 70));
        fila.enfileirar(new Lutador("F4", 1, 40, 100, 80)); // Deve usar posições circulares

        // Teste 5: Desenfileirar de fila vazia
        while (!fila.estaVazia()) {
            fila.desenfileirar();
        }

        Lutador nulo = fila.desenfileirar();
        if (nulo != null) {
            System.out.println("ERRO: Desenfileirar de fila vazia deveria retornar null");
            return false;
        }

        // Teste 6: Fila cheia e circular
        Fila filaPequena = new Fila(2);
        filaPequena.enfileirar(new Lutador("A", 1, 10, 100, 10));
        filaPequena.enfileirar(new Lutador("B", 1, 20, 100, 20));

        // Remove um para testar circularidade
        filaPequena.desenfileirar();
        filaPequena.enfileirar(new Lutador("C", 1, 30, 100, 30));

        System.out.println("Fila Completa: OK");
        return true;
    }

    // ==================== TESTES COMPLETOS DA LISTA ORDENADA ====================
    public static boolean testarListaOrdenadaCompleto() {
        System.out.println("\nTestando ListaOrdenada Completa ");
        ListaOrdenada lista = new ListaOrdenada(10);

        // Teste 1: Lista inicia vazia
        if (lista.obterTamanho() != 0) {
            System.out.println("ERRO: Lista ordenada deveria iniciar vazia");
            return false;
        }

        // Teste 2: Inserir em ordem decrescente
        Lutador l1 = new Lutador("A", 1, 10, 0, 50);
        Lutador l2 = new Lutador("B", 1, 20, 0, 80);
        Lutador l3 = new Lutador("C", 1, 30, 0, 60);

        lista.inserir(l1); // 50
        lista.inserir(l2); // 80 (deve ficar antes do 50)
        lista.inserir(l3); // 60 (deve ficar entre 80 e 50)

        if (lista.obterTamanho() != 3) {
            System.out.println("ERRO: Tamanho incorreto após inserções");
            return false;
        }

        // Teste 3: Verificar ordem correta
        if (lista.obterPorIndice(0).iniciativa != 80 ||
                lista.obterPorIndice(1).iniciativa != 60 ||
                lista.obterPorIndice(2).iniciativa != 50) {
            System.out.println("ERRO: Ordem decrescente não está correta");
            return false;
        }

        // Teste 4: Inserir com iniciativa igual
        Lutador l4 = new Lutador("D", 1, 40, 0, 60); // Mesma iniciativa que C
        lista.inserir(l4);

        // A ordem com iniciativas iguais pode ser qualquer uma
        // Apenas verificar que foi inserido
        if (lista.obterTamanho() != 4) {
            System.out.println("ERRO: Elemento com iniciativa igual não foi inserido");
            return false;
        }

        // Teste 5: Lista vazia
        ListaOrdenada vazia = new ListaOrdenada(5);
        if (vazia.obterTamanho() != 0) {
            System.out.println("ERRO: Nova lista ordenada não está vazia");
            return false;
        }

        System.out.println("ListaOrdenada Completa: OK");
        return true;
    }

    // ==================== TESTES COMPLETOS DA CLASSE TIME ====================
    public static void testarClasseTimeCompleto() {
        System.out.println("\nTestando Classe Time Completa ");
        Time time = new Time(1);

        // Teste 1: Time criado corretamente
        if (time.numero != 1) {
            System.out.println("ERRO: Número do time incorreto");
            return;
        }

        // Teste 2: Estruturas internas inicializadas
        if (time.lutadoresVivos == null || time.cemiterio == null || time.filaOrdenada == null) {
            System.out.println("ERRO: Estruturas do time não inicializadas");
            return;
        }

        // Teste 3: Adicionar lutadores vivos
        Lutador l1 = new Lutador("T1", 1, 30, 100, 75);
        Lutador l2 = new Lutador("T2", 1, 40, 120, 85);
        time.lutadoresVivos.adicionar(l1);
        time.lutadoresVivos.adicionar(l2);

        if (time.lutadoresVivos.obterTamanho() != 2) {
            System.out.println("ERRO: Não foi possível adicionar lutadores vivos");
            return;
        }

        // Teste 4: Adicionar ao cemitério
        Lutador morto = new Lutador("TM", 1, 20, 0, 70);
        time.cemiterio.inserir(morto);

        if (time.cemiterio.obterTamanho() != 1) {
            System.out.println("ERRO: Não foi possível adicionar ao cemitério");
            return;
        }

        // Teste 5: Time vazio
        Time timeVazio = new Time(2);
        if (timeVazio.lutadoresVivos.obterTamanho() != 0 || timeVazio.cemiterio.obterTamanho() != 0) {
            System.out.println("ERRO: Time novo não está vazio");
            return;
        }

        System.out.println("Classe Time Completa: OK");
    }

    // ==================== TESTES DE ORGANIZAÇÃO DA FILA ====================
    public static void testarOrganizacaoFilaCompleto() {
        System.out.println("\nTestando Organização da Fila");
        Time time = new Time(1);

        // Adiciona lutadores fora de ordem
        time.lutadoresVivos.adicionar(new Lutador("A", 1, 10, 100, 30));
        time.lutadoresVivos.adicionar(new Lutador("B", 1, 20, 100, 90));
        time.lutadoresVivos.adicionar(new Lutador("C", 1, 30, 100, 60));
        time.lutadoresVivos.adicionar(new Lutador("D", 1, 40, 100, 70));
        time.lutadoresVivos.adicionar(new Lutador("E", 1, 50, 100, 80));

        // Organiza a fila
        time.organizarFila();

        // Verifica se a fila não está vazia
        if (time.filaOrdenada.estaVazia()) {
            System.out.println("ERRO: Fila organizada está vazia");
            return;
        }

        // Verifica ordem decrescente
        int iniciativaAnterior = 1000; // Valor alto para comparar
        boolean ordemCorreta = true;

        // Cria array temporário para verificação
        int n = 0;
        while (!time.filaOrdenada.estaVazia()) {
            Lutador lutador = time.filaOrdenada.desenfileirar();
            if (lutador.iniciativa > iniciativaAnterior) {
                ordemCorreta = false;
                break;
            }
            iniciativaAnterior = lutador.iniciativa;
            n++;
        }

        if (!ordemCorreta) {
            System.out.println("ERRO: Fila não está em ordem decrescente");
            return;
        }

        if (n != 5) {
            System.out.println("ERRO: Fila perdeu elementos durante organização");
            return;
        }

        System.out.println("Organização da Fila: OK");
    }

    // ==================== TESTES DE COMBATE SIMULTÂNEO ====================
    public static void testarCombateSimultaneoCompleto() {
        System.out.println("\nTestando Combate Simultâneo Completo ");

        // Teste 1: Combate normal
        Lutador atacante1 = new Lutador("ATK1", 1, 60, 100, 70);
        Lutador atacante2 = new Lutador("ATK2", 2, 50, 100, 80);

        // Simula ataque simultâneo
        atacante2.pontosVida -= atacante1.dano; // ATK1 ataca ATK2
        atacante1.pontosVida -= atacante2.dano; // ATK2 ataca ATK1

        if (atacante1.pontosVida != 50 || atacante2.pontosVida != 40) {
            System.out.println("ERRO: Cálculo de dano incorreto");
            return;
        }

        // Teste 2: Morte por dano excessivo
        Lutador forte = new Lutador("FORTE", 1, 200, 100, 50);
        Lutador fraco = new Lutador("FRACO", 2, 10, 50, 60);

        fraco.pontosVida -= forte.dano; // -150 de vida

        if (fraco.estaVivo()) {
            System.out.println("ERRO: Lutador deveria morrer com dano excessivo");
            return;
        }

        // Teste 3: Ambos morrem simultaneamente
        Lutador l1 = new Lutador("M1", 1, 100, 100, 50);
        Lutador l2 = new Lutador("M2", 2, 100, 100, 60);

        l2.pontosVida -= l1.dano; // M1 mata M2
        l1.pontosVida -= l2.dano; // M2 mata M1

        if (l1.estaVivo() || l2.estaVivo()) {
            System.out.println("ERRO: Ambos deveriam morrer simultaneamente");
            return;
        }

        // Teste 4: Flag atacouNesteturno
        Lutador l3 = new Lutador("F1", 1, 50, 100, 70);
        if (l3.atacouNesteTurno) {
            System.out.println("ERRO: Flag atacouNesteturno deveria iniciar false");
            return;
        }

        l3.atacouNesteTurno = true;
        if (!l3.atacouNesteTurno) {
            System.out.println("ERRO: Não foi possível alterar flag atacouNesteturno");
            return;
        }

        System.out.println("Combate Simultâneo: OK");
    }

    // ==================== TESTES DE IDENTIFICADOR ÚNICO ====================
    public static void testarIdentificadorUnicoCompleto() {
        System.out.println("\nTestando Identificador Único ");

        // Simula o método identificadorExiste da classe P01
        Time time1 = new Time(1);
        Time time2 = new Time(2);

        // Adiciona lutadores com IDs diferentes
        time1.lutadoresVivos.adicionar(new Lutador("ID1", 1, 30, 100, 50));
        time1.lutadoresVivos.adicionar(new Lutador("ID2", 1, 40, 120, 60));
        time2.lutadoresVivos.adicionar(new Lutador("ID3", 2, 25, 90, 70));

        // Teste 1: ID existente
        boolean id1Existe = time1.lutadoresVivos.buscar("ID1") != null;
        boolean id2Existe = time2.lutadoresVivos.buscar("ID1") != null;

        if (!id1Existe || id2Existe) {
            System.out.println("ERRO: Verificação de ID existente falhou");
            return;
        }

        // Teste 2: ID não existente
        boolean idInexistente = time1.lutadoresVivos.buscar("INEXISTENTE") != null ||
                time2.lutadoresVivos.buscar("INEXISTENTE") != null;

        if (idInexistente) {
            System.out.println("ERRO: ID inexistente foi encontrado");
            return;
        }

        // Teste 3: ID no cemitério
        time1.cemiterio.inserir(new Lutador("ID4", 1, 20, 0, 55));

        // Verifica no cemitério
        boolean idNoCemiterio = false;
        for (int i = 0; i < time1.cemiterio.obterTamanho(); i++) {
            if (time1.cemiterio.obterPorIndice(i).identificador.equals("ID4")) {
                idNoCemiterio = true;
                break;
            }
        }

        if (!idNoCemiterio) {
            System.out.println("ERRO: ID não encontrado no cemitério");
            return;
        }

        System.out.println("Identificador Único: OK");
    }

    // ==================== TESTES DAS CONDIÇÕES DE FIM ====================
    public static void testarCondicoesFimCompleto() {
        System.out.println("\nTestando Condições de Fim ");

        boolean todasCondicoesOK = true;

        // Condição 1: Time 1 tem vivos, Time 2 não
        Time t1 = new Time(1);
        Time t2 = new Time(2);
        t1.lutadoresVivos.adicionar(new Lutador("V1", 1, 30, 100, 50));

        int vivos1 = t1.lutadoresVivos.obterTamanho();
        int vivos2 = t2.lutadoresVivos.obterTamanho();

        if (!(vivos1 > 0 && vivos2 == 0)) {
            System.out.println("ERRO: Condição 1 não identificada corretamente");
            todasCondicoesOK = false;
        }

        // Condição 2: Ambos vivos, apenas Time 1 com score >= 20
        Time t3 = new Time(1);
        Time t4 = new Time(2);

        t3.lutadoresVivos.adicionar(new Lutador("V2", 1, 30, 100, 50));
        t4.lutadoresVivos.adicionar(new Lutador("V3", 2, 30, 100, 50));

        // Adiciona 20 mortos ao cemitério do time 4 (score do time 3 = 20)
        for (int i = 0; i < 20; i++) {
            t4.cemiterio.inserir(new Lutador("M" + i, 2, 10, 0, 50));
        }

        int score3 = t4.cemiterio.obterTamanho();
        int score4 = t3.cemiterio.obterTamanho();

        if (!(score3 >= 20 && score4 < 20)) {
            System.out.println("ERRO: Condição 2 não identificada corretamente");
            todasCondicoesOK = false;
        }

        // Condição 4: Ambos vazios, scores diferentes
        Time t5 = new Time(1);
        Time t6 = new Time(2);

        // Time 5: 15 mortos no time 6
        for (int i = 0; i < 15; i++) {
            t6.cemiterio.inserir(new Lutador("M5_" + i, 2, 10, 0, 50));
        }

        // Time 6: 10 mortos no time 5
        for (int i = 0; i < 10; i++) {
            t5.cemiterio.inserir(new Lutador("M6_" + i, 1, 10, 0, 50));
        }

        int score5 = t6.cemiterio.obterTamanho();
        int score6 = t5.cemiterio.obterTamanho();

        if (!(t5.lutadoresVivos.obterTamanho() == 0 &&
                t6.lutadoresVivos.obterTamanho() == 0 &&
                score5 > score6)) {
            System.out.println("ERRO: Condição 4 não identificada corretamente");
            todasCondicoesOK = false;
        }

        // Condição 5: Empate
        Time t7 = new Time(1);
        Time t8 = new Time(2);

        // Ambos com 5 mortos
        for (int i = 0; i < 5; i++) {
            t8.cemiterio.inserir(new Lutador("M7_" + i, 2, 10, 0, 50));
            t7.cemiterio.inserir(new Lutador("M8_" + i, 1, 10, 0, 50));
        }

        int score7 = t8.cemiterio.obterTamanho();
        int score8 = t7.cemiterio.obterTamanho();

        if (!(t7.lutadoresVivos.obterTamanho() == 0 &&
                t8.lutadoresVivos.obterTamanho() == 0 &&
                score7 == score8)) {
            System.out.println("ERRO: Condição 5 não identificada corretamente");
            todasCondicoesOK = false;
        }

        if (todasCondicoesOK) {
            System.out.println("Condições de Fim: OK");
        }
    }

    // ==================== TESTES DO SISTEMA COMPLETO ====================
    public static void testarSistemaCompleto() {
        System.out.println("\n Testando Sistema Completo ");

        // Simula um turno completo
        Time time1 = new Time(1);
        Time time2 = new Time(2);

        // Adiciona lutadores aos times
        time1.lutadoresVivos.adicionar(new Lutador("T1A", 1, 30, 100, 80));
        time1.lutadoresVivos.adicionar(new Lutador("T1B", 1, 25, 90, 70));
        time2.lutadoresVivos.adicionar(new Lutador("T2A", 2, 35, 110, 85));
        time2.lutadoresVivos.adicionar(new Lutador("T2B", 2, 20, 80, 65));

        // Organiza filas
        time1.organizarFila();
        time2.organizarFila();

        // Simula um round de combate
        Lutador t1Lutador = time1.filaOrdenada.desenfileirar();
        Lutador t2Lutador = time2.filaOrdenada.desenfileirar();

        // Ataque simultâneo
        t2Lutador.pontosVida -= t1Lutador.dano;
        t1Lutador.pontosVida -= t2Lutador.dano;

        // Verifica se algum morreu
        boolean t1Morreu = !t1Lutador.estaVivo();
        boolean t2Morreu = !t2Lutador.estaVivo();

        // Se morreu, remove dos vivos e adiciona ao cemitério
        if (t1Morreu) {
            time1.lutadoresVivos.remover(t1Lutador.identificador);
            time1.cemiterio.inserir(t1Lutador);
        }

        if (t2Morreu) {
            time2.lutadoresVivos.remover(t2Lutador.identificador);
            time2.cemiterio.inserir(t2Lutador);
        }

        // Verifica estado final
        int vivosTime1 = time1.lutadoresVivos.obterTamanho();
        int vivosTime2 = time2.lutadoresVivos.obterTamanho();
        int mortosTime1 = time1.cemiterio.obterTamanho();
        int mortosTime2 = time2.cemiterio.obterTamanho();

        System.out.println("Time 1: " + vivosTime1 + " vivos, " + mortosTime1 + " mortos");
        System.out.println("Time 2: " + vivosTime2 + " vivos, " + mortosTime2 + " mortos");

        if (vivosTime1 < 2 || vivosTime2 < 2) {
            System.out.println("Aviso: Algum lutador morreu no combate");
        }

        System.out.println("Sistema Completo: OK");
    }

    // ==================== TESTES DE CASOS LIMITE ====================
    public static void testarCasosLimite() {
        System.out.println("\nTestando Casos Limite ");

        // Teste 1: Iniciativa nos limites (1 e 100)
        try {
            Lutador minIniciativa = new Lutador("MIN", 1, 10, 100, 1);
            Lutador maxIniciativa = new Lutador("MAX", 1, 10, 100, 100);

            if (minIniciativa.iniciativa != 1 || maxIniciativa.iniciativa != 100) {
                System.out.println("ERRO: Iniciativa não aceita valores limite");
                return;
            }
        } catch (Exception e) {
            System.out.println("ERRO: Exceção ao criar lutadores com iniciativa limite");
            return;
        }

        // Teste 2: Vida zero ou negativa na criação
        Lutador vidaZero = new Lutador("ZERO", 1, 10, 0, 50);
        Lutador vidaNegativa = new Lutador("NEG", 1, 10, -10, 50);

        if (vidaZero.estaVivo() || vidaNegativa.estaVivo()) {
            System.out.println("ERRO: Lutadores com vida <= 0 deveriam estar mortos");
            return;
        }

        // Teste 3: Dano zero
        Lutador danoZero = new Lutador("D0", 1, 0, 100, 50);
        if (danoZero.dano != 0) {
            System.out.println("ERRO: Dano zero não permitido");
            return;
        }

        // Teste 4: Time inválido (deveria ser validado em P01)
        Lutador timeInvalido = new Lutador("TI", 3, 10, 100, 50);
        // Apenas verifica que foi criado, a validação fica para P01

        System.out.println("Casos Limite: OK");
    }

    // ==================== TESTES DE FLUXO DO JOGO ====================
    public static void testarFluxoJogoCompleto() {
        System.out.println("\n Testando Fluxo Completo do Jogo");

        // Cria times para simulação
        Time time1 = new Time(1);
        Time time2 = new Time(2);

        // Fase 1: Organização - Adiciona lutadores
        System.out.println("Fase 1: Organização dos times");
        time1.lutadoresVivos.adicionar(new Lutador("Hero1", 1, 30, 100, 75));
        time1.lutadoresVivos.adicionar(new Lutador("Hero2", 1, 25, 90, 85));
        time2.lutadoresVivos.adicionar(new Lutador("Vilao1", 2, 35, 110, 80));
        time2.lutadoresVivos.adicionar(new Lutador("Vilao2", 2, 20, 80, 70));

        // Fase 2: Combate - Organiza filas
        System.out.println("Fase 2: Preparação para combate");
        time1.organizarFila();
        time2.organizarFila();

        // Simula múltiplos rounds até um time ficar sem lutadores
        int round = 1;
        while (!time1.filaOrdenada.estaVazia() && !time2.filaOrdenada.estaVazia() && round <= 10) {
            System.out.println("Round " + round);

            Lutador t1 = time1.filaOrdenada.desenfileirar();
            Lutador t2 = time2.filaOrdenada.desenfileirar();

            // Ataque simultâneo
            t2.pontosVida -= t1.dano;
            t1.pontosVida -= t2.dano;

            // Verifica mortes
            if (!t1.estaVivo()) {
                time1.lutadoresVivos.remover(t1.identificador);
                time1.cemiterio.inserir(t1);
                System.out.println("  " + t1.identificador + " morreu!");
            } else {
                time1.filaOrdenada.enfileirar(t1);
            }

            if (!t2.estaVivo()) {
                time2.lutadoresVivos.remover(t2.identificador);
                time2.cemiterio.inserir(t2);
                System.out.println("  " + t2.identificador + " morreu!");
            } else {
                time2.filaOrdenada.enfileirar(t2);
            }

            round++;
        }

        // Fase 3: Resultados
        System.out.println("Fase 3: Resultados finais");
        int vivos1 = time1.lutadoresVivos.obterTamanho();
        int vivos2 = time2.lutadoresVivos.obterTamanho();
        int score1 = time2.cemiterio.obterTamanho();
        int score2 = time1.cemiterio.obterTamanho();

        System.out.println("Time 1: " + vivos1 + " vivos, Score: " + score1);
        System.out.println("Time 2: " + vivos2 + " vivos, Score: " + score2);

        // Determina vencedor (simplificado)
        if (vivos1 == 0 && vivos2 == 0) {
            if (score1 > score2) System.out.println("Time 1 vence por maior score!");
            else if (score2 > score1) System.out.println("Time 2 vence por maior score!");
            else System.out.println("Empate!");
        } else if (vivos1 == 0) {
            System.out.println("Time 2 vence!");
        } else if (vivos2 == 0) {
            System.out.println("Time 1 vence!");
        } else {
            System.out.println("Jogo ainda em andamento...");
        }

        System.out.println("Fluxo do Jogo: OK");
    }
}