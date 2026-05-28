# FinanceiroApp — Sistema de Gerenciamento de Finanças Pessoais

## Descrição
Sistema de controle financeiro pessoal desenvolvido em Java com interface
de linha de comando (CLI). Permite registrar receitas, despesas e
investimentos, além de criar e acompanhar metas financeiras.

## Funcionalidades

- Adicionar, listar, atualizar e remover transações
- Tipos de transação: Receita, Despesa e Investimento
- Criar e acompanhar metas financeiras com progresso em %
- Depositar valores em metas
- Visualizar saldo atual com base nas transações registradas

## Estrutura do Projeto
projeto-poo/
├── financeiro/
│       ├── Main.java
│       ├── model/
│       │   ├── Transacao.java
│       │   ├── Meta.java
│       │   └── tipos/
│       │       ├── Receita.java
│       │       ├── Despesa.java
│       │       └── Investimento.java
│       ├── service/
│       │   ├── TransacaoService.java
│       │   └── MetaService.java
│       └── util/
│           ├── Validador.java
│           └── Formatador.java
└── README.md

## Como Executar

### Pré-requisitos
- Java JDK 11 ou superior instalado

### Passo a passo

**1. Abra o terminal dentro da pasta do projeto**

**2. Compile todos os arquivos:**
javac -d bin src/financeiro/util/.java src/financeiro/model/.java src/financeiro/model/tipos/.java src/financeiro/service/.java src/financeiro/Main.java 

**3. Execute o programa:**
java -cp bin financeiro.Main

## Conceitos de POO Utilizados

- **Herança:** Receita, Despesa e Investimento herdam de Transacao
- **Abstração:** Transacao é uma classe abstrata com métodos abstratos
- **Polimorfismo:** a lista de transações aceita qualquer subtipo e cada
  um calcula seu impacto no saldo de forma diferente
- **Encapsulamento:** todos os atributos são privados com getters e setters

## Autor
Giovanna Nascimento Chaves
Luís Felipe Nicolau
