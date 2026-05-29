# Sistema de Supermercado (Python + MySQL)
Aplicação desenvolvida em Python com o objetivo de praticar conexão com banco de dados relacional, realizando operações completas de CRUD em uma tabela de produtos, com validações de entrada e controle de fluxo via menu interativo.

## 🚀 Tecnologias
* Python
* MySQL

## 📚 O que aprendi
* Conexão com banco de dados MySQL via `mysql.connector`
* Criação de banco de dados e tabelas via código
* Operações CRUD (Create, Read, Update, Delete)
* Uso de placeholders `%s` para prevenção de SQL Injection
* Uso de cursores para execução de comandos SQL
* Confirmação de transações com `commit()`
* Validação de entradas do usuário com `try/except`
* Estruturas de repetição e condicionais para controle de fluxo
* Formatação de saída de dados no terminal
* Busca parcial com operador `LIKE` no SQL

## 📁 Estrutura do Projeto

### 🔹 Configuração e Conexão com o Banco
* Importação do módulo `mysql.connector`
* Definição da constante com o nome do banco de dados
* Função `conectar()` para acesso ao servidor MySQL
* Função `criar_banco()` com `CREATE DATABASE IF NOT EXISTS`
* Função `conectar_bd()` para conexão ao banco específico
* Função `criar_tabela()` com definição dos campos e tipos SQL

### 🔹 Validações de Entrada
* Função `ler_float()` para leitura segura de números decimais
* Função `ler_int()` para leitura segura de números inteiros
* Tratamento de erros com `try/except ValueError`

### 🔹 Adição de Produto
* Validação de nome não vazio
* Validação de preço maior que zero
* Validação de quantidade não negativa
* Inserção no banco com `INSERT INTO` e placeholders `%s`
* Retorno do ID gerado automaticamente com `cursor.lastrowid`

### 🔹 Listagem e Busca
* Consulta com `SELECT` e ordenação por `ORDER BY`
* Recuperação de múltiplos resultados com `fetchall()`
* Exibição formatada em colunas no terminal
* Busca parcial por nome com operador `LIKE` e `%termo%`

### 🔹 Atualização de Produto
* Busca do produto pelo ID com `fetchone()`
* Manutenção de valores atuais quando campo deixado em branco
* Atualização com `UPDATE ... SET ... WHERE`

### 🔹 Exclusão e Menu Principal
* Confirmação do usuário antes de excluir
* Remoção com `DELETE FROM ... WHERE`
* Menu interativo em loop `while True`
* Inicialização automática do banco e tabela com `if __name__ == "__main__"`

## ▶️ Como executar

1. Instale a dependência:
   ```
   pip install mysql-connector-python
   ```
2. Certifique-se de que o MySQL está rodando localmente
3. Ajuste as credenciais de acesso (usuário e senha) nas funções `conectar()` e `conectar_bd()`
4. Execute o programa:
   ```
   python supermercado.py
   ```

## 📌 Observações
O projeto foi desenvolvido durante a disciplina de Banco de Dados II, com foco na integração entre uma aplicação Python e um banco de dados MySQL, aplicando na prática os conceitos de criação e manipulação de tabelas, consultas SQL e boas práticas como prevenção de SQL Injection.
