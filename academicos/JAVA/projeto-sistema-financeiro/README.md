# FinanceiroApp - Sistema de Gerenciamento de Finanças Pessoais (Java)

Sistema de controle financeiro pessoal desenvolvido em Java com o objetivo de praticar conceitos de Programação Orientada a Objetos, aplicando herança, polimorfismo, encapsulamento e abstração em um sistema CRUD funcional via linha de comando.

## 🚀 Tecnologias

* Java

## 📚 O que aprendi

* Criação de classes abstratas e subclasses
* Herança entre classes (Receita, Despesa e Investimento herdando de Transacao)
* Polimorfismo aplicado ao cálculo de saldo
* Encapsulamento com atributos privados, getters e setters
* Implementação de construtores
* Uso de ArrayList para armazenar e manipular dados em memória
* Operações CRUD (Create, Read, Update, Delete)
* Validação de dados e regras de negócio
* Controle de fluxo com switch, while e for
* Manipulação de entrada e saída de dados via Scanner

## 📁 Estrutura do Projeto
projeto-poo/
├── financeiro/
│   ├── Main.java
│   ├── model/
│   │   ├── Transacao.java
│   │   ├── Meta.java
│   │   └── tipos/
│   │       ├── Receita.java
│   │       ├── Despesa.java
│   │       └── Investimento.java
│   ├── service/
│   │   ├── TransacaoService.java
│   │   └── MetaService.java
│   └── util/
│       ├── Validador.java
│       └── Formatador.java
└── README.md

## 🔹 Funcionalidades

### Transações
* Adicionar receitas, despesas e investimentos
* Listar todas as transações ou filtrar por tipo
* Atualizar dados de uma transação existente
* Remover transações pelo ID
* Calcular saldo atual com base no impacto de cada transação

### Metas Financeiras
* Criar metas com nome, valor alvo e descrição
* Listar todas as metas com progresso em porcentagem
* Depositar valores em uma meta
* Atualizar dados da meta
* Remover metas pelo ID

## ▶️ Como executar

1. Acesse a pasta do projeto no terminal

2. Compile os arquivos:

javac -d bin financeiro/util/Validador.java financeiro/util/Formatador.java financeiro/model/Transacao.java financeiro/model/Meta.java financeiro/model/tipos/Receita.java financeiro/model/tipos/Despesa.java financeiro/model/tipos/Investimento.java financeiro/service/TransacaoService.java financeiro/service/MetaService.java financeiro/Main.java

3. Execute:

java -cp bin financeiro.Main

## 📌 Observações

O projeto foi desenvolvido durante a disciplina de Programação Orientada a Objetos, com foco na aplicação prática dos pilares da orientação a objetos em Java por meio de um sistema financeiro funcional com menu interativo via terminal.
