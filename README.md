# 3VA_Algoritimos_LuizGonzaga
Sistema de Controle para um Jogo de Ação Baseado em Turnos, desenvolvido como Projeto 01 da disciplina de Algoritmos e Estruturas de Dados da UFRPE, com implementação manual de todas as estruturas e algoritmos, sem uso de estruturas prontas ou métodos otimizados da linguagem.


# 🎮 Projeto 01 – Sistema de Controle para Jogo de Ação Baseado em Turnos  
## 3ª Verificação de Aprendizagem (3VA)

## Universidade Federal Rural de Pernambuco – UFRPE  
**Departamento:** Computação  
**Área:** Informática  
**Disciplina:** Algoritmos e Estruturas de Dados  
**Código da Disciplina:** 06214  
**Curso:** Bacharelado em Ciência da Computação / Licenciatura em Computação  
**Professor Responsável:** Luciano Demétrio Santos Pacífico  
**Data Máxima de Entrega:** 09/12/2025  

---

## 👤 Autor
**Luiz Gonzaga**  
Graduando em Ciência da Computação – UFRPE  

---

## 🎯 Objetivo do Projeto

Este projeto tem como objetivo o desenvolvimento de um **Sistema de Controle para um Jogo de Ação Baseado em Turnos**, aplicando de forma prática os conceitos fundamentais de **Algoritmos e Estruturas de Dados**, conforme exigido na 3ª Verificação de Aprendizagem (3VA).

Todo o sistema foi implementado em uma linguagem de programação real, com **implementação manual de todas as estruturas de dados e algoritmos**, respeitando rigorosamente as regras definidas pela disciplina.

---

## 📌 Conformidade com as Regras da Disciplina

O projeto atende integralmente às regras estabelecidas, destacando-se:

- ❌ Não utilização de estruturas de dados prontas das linguagens de programação
- ❌ Não utilização de algoritmos ou comandos otimizados prontos
- ✅ Implementação manual de todas as estruturas de dados
- ✅ Uso exclusivo de:
  - Variáveis e tipos primitivos
  - Estruturas condicionais
  - Estruturas de repetição
  - Sub-rotinas (funções/métodos)
  - Estruturas homogêneas (arrays estáticos)
  - Estruturas heterogêneas (classes/structs)
- ✅ Uso de **alocação estática** para todos os arrays
- ✅ Estruturas simuladas manualmente, mesmo quando a linguagem não oferece arrays estáticos nativos

---

## 🧩 Visão Geral do Sistema

O sistema simula um **jogo de ação em turnos** entre **dois times rivais de lutadores**, onde cada turno é dividido em três fases:

1. **Organização dos Times**
2. **Combate**
3. **Resultados**

O jogo é encerrado automaticamente quando uma das condições de término é satisfeita.

---

## 🧑‍🤝‍🧑 Estrutura dos Times e Lutadores

- O sistema suporta **exatamente dois times**
- Cada time pode possuir qualquer quantidade de lutadores
- Cada lutador possui:
  - Identificador único
  - Time ao qual pertence
  - Valor de dano
  - Pontos de vida
  - Valor base de iniciativa (1 a 100)
- Um lutador é considerado:
  - **Vivo**, se seus pontos de vida > 0
  - **Morto**, caso contrário

---

## 🔁 Funcionamento do Jogo

### 🔹 Fase 1 – Organização dos Times

Nesta fase, o usuário interage com o sistema por meio de um menu, podendo executar as seguintes ações:

- **Inserção de lutadores em times**
  - O sistema garante unicidade de identificadores
  - Inserções inválidas são rejeitadas com mensagem de erro

- **Relatório de status de um time**
  - Exibe lutadores vivos e mortos
  - Lutadores vivos são listados em **ordem decrescente de iniciativa**
  - Lutadores mortos também são exibidos em ordem decrescente de iniciativa

- **Fuga de lutador**
  - Permite remover um lutador vivo do combate, independentemente do time

Após as ações do usuário, cada time é organizado em uma **Fila**, ordenada por iniciativa decrescente, dando início à fase de combate.

---

### 🔹 Fase 2 – Combate

A fase de combate é executada automaticamente:

- Os primeiros lutadores das filas de cada time combatem entre si
- Os ataques são **simultâneos**
- O dano é aplicado aos pontos de vida do adversário
- Lutadores vivos retornam à fila
- Lutadores mortos são enviados ao **Cemitério** do time

Cada time possui um **Cemitério próprio**, implementado como uma **Lista Ordenada decrescente por iniciativa**.

O combate continua até que:
- Todos os lutadores tenham atacado uma vez, ou
- Um dos times fique sem lutadores vivos

---

### 🔹 Fase 3 – Resultados

Ao final do combate:

- O **score** de cada time é calculado com base na quantidade de lutadores mortos do time adversário
- As **condições de término** do jogo são avaliadas, podendo resultar em:
  - Vitória de um dos times
  - Empate
  - Continuação para um novo turno

Caso nenhuma condição de término seja satisfeita, um novo turno é iniciado automaticamente.

---

## 📂 Estrutura dos Arquivos

O projeto segue o padrão exigido pela disciplina:

