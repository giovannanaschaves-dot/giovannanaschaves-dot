# Gateway de Pagamentos (Java)

Exercício desenvolvido em Java para a disciplina de Desenvolvimento de Sistemas, com o objetivo de praticar os conceitos de Herança e Polimorfismo a partir de um cenário real de arquitetura de backend: o motor central de pagamentos de um E-commerce, capaz de aceitar novas formas de pagamento sem alterar o processador principal.

## 🚀 Tecnologias

* Java

## 📚 O que aprendi

* Classes abstratas: um tipo que não pode ser instanciado diretamente
* Métodos abstratos como contrato que obriga as classes filhas a implementá-los
* Métodos concretos na classe mãe reaproveitados por todas as filhas
* Herança com extends e atributos protected visíveis para as filhas
* Uso de super() para repassar dados ao construtor da classe mãe
* Sobrescrita de métodos com a anotação @Override
* Polimorfismo: receber o tipo genérico e deixar o Java executar o comportamento do objeto real
* Princípio Aberto-Fechado: aberto para extensão, fechado para modificação
* Introdução ao padrão de projeto Strategy
* Por que evitar if/else, switch/case e instanceof para descobrir o tipo de um objeto

## 📁 Exercício

### 🔹 Gateway de Pagamentos (E-commerce)

* Criação da classe abstrata Pagamento representando qualquer forma de pagamento
* Atributos protegidos: id da transação, valor e status
* Construtor que recebe apenas o valor, gerando o id automaticamente com UUID e iniciando o status como PENDENTE
* Método concreto imprimirRecibo() compartilhado por todas as classes filhas
* Método abstrato processar() sem corpo, definindo o contrato de cobrança
* Classe PagamentoPix, com chave Pix, que gera o QR Code e sempre aprova a transação
* Classe PagamentoCartao, com número do cartão e nome do titular, que recusa compras acima de R$ 5000,00
* Classe GatewayPagamento, sem herança, com o método realizarCobranca() que recebe o tipo genérico Pagamento
* Atualização do status para APROVADO ou RECUSADO de acordo com o retorno de processar()
* Classe Main com um pagamento Pix de R$ 150,00 (aprovado) e um pagamento com cartão de R$ 6000,00 (recusado)

## ▶️ Como executar

1. Acesse a pasta do exercício
2. Compile os arquivos:
   javac Pagamento.java PagamentoPix.java PagamentoCartao.java GatewayPagamento.java Main.java
3. Execute:
   java Main

## 📌 Observações

O exercício foi desenvolvido durante a disciplina de Desenvolvimento de Sistemas, com foco em uma arquitetura em que a regra de cobrança mora dentro de cada tipo de pagamento, e não no processador central. Assim, para aceitar uma nova forma de pagamento (como criptomoedas), basta criar uma nova classe filha de Pagamento, sem alterar nenhuma linha do GatewayPagamento.
