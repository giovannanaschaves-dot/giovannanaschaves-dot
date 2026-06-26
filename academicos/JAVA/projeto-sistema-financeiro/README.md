# FinanceiroApp - Sistema de Gerenciamento de Finanças Pessoais

Sistema de controle financeiro pessoal desenvolvido em Java com o objetivo de praticar conceitos de Programação Orientada a Objetos, aplicando herança, polimorfismo, encapsulamento e abstração em um sistema CRUD funcional com interface gráfica e persistência de dados.

## 👥 Autores

* Giovanna
* Luís Felipe Nicolau

## 🚀 Tecnologias

* Java
* Swing (interface gráfica)
* Gson 2.10.1 (persistência de dados em JSON)

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
* Criação de interfaces gráficas com Swing (JFrame, JTable, JButton, JComboBox)
* Persistência de dados em arquivos JSON com a biblioteca Gson
* Organização de projetos em pacotes (model, service, util, gui)

## 🔹 Funcionalidades

### Transações
* Adicionar receitas, despesas e investimentos pela interface gráfica
* Listar todas as transações em tabela
* Atualizar dados de uma transação existente pelo ID
* Remover transações pelo ID
* Calcular saldo atual com base no impacto de cada transação

### Metas Financeiras
* Criar metas com nome, valor alvo e descrição
* Listar todas as metas com valor acumulado
* Depositar valores em uma meta pelo ID
* Atualizar dados da meta pelo ID
* Remover metas pelo ID

### Persistência
* Todos os dados são salvos automaticamente em arquivos JSON
* Os dados persistem entre sessões — nada é perdido ao fechar o programa

## ▶️ Como executar

1. Acesse a pasta do projeto no terminal:
cd "C:\Users\Giovanna\Downloads\projeto-sistema-financeiro"

2. Compile os arquivos:
javac -d bin -cp "lib/gson-2.10.1.jar" financeiro/Main.java financeiro/gui/TelaPrincipal.java financeiro/gui/TelaTransacao.java financeiro/gui/TelaMeta.java financeiro/service/TransacaoService.java financeiro/service/MetaService.java financeiro/util/JsonUtil.java financeiro/model/Transacao.java financeiro/model/Meta.java financeiro/model/tipos/Receita.java financeiro/model/tipos/Despesa.java financeiro/model/tipos/Investimento.java financeiro/util/Formatador.java financeiro/util/Validador.java

3. Execute:
java -cp "bin;lib/gson-2.10.1.jar" financeiro.Main

## 📌 Observações

O projeto foi desenvolvido durante a disciplina de Programação Orientada a Objetos, com foco na aplicação prática dos pilares da orientação a objetos em Java. Esta versão expande o sistema anterior adicionando uma interface gráfica construída com Swing e persistência de dados em formato JSON, tornando o sistema completo e funcional para uso real.