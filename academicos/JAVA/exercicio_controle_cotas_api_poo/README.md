# Controle de Cotas de API (Java)

Exercício desenvolvido em Java para a disciplina de Desenvolvimento de Sistemas, com o objetivo de praticar os conceitos de Abstração e Encapsulamento a partir de um cenário real de arquitetura de backend: o controle de uso de chaves de acesso (API Keys) em uma API de Inteligência Artificial.

## 🚀 Tecnologias

* Java

## 📚 O que aprendi

* Abstração: seleção apenas das características essenciais do domínio
* Encapsulamento com atributos privados
* Construtor que garante um estado inicial válido para o objeto
* Diferença entre getters, setters e métodos de negócio
* Como evitar o modelo anêmico (getters e setters desnecessários)
* Cláusulas de guarda: validar primeiro, alterar depois
* Lançamento de exceções com throw (IllegalStateException e IllegalArgumentException)
* Tratamento de exceções com try/catch
* Testes de regras de negócio com cenários positivos e negativos

## 📁 Exercício

### 🔹 Controle de Cotas de API (API Gateway)

* Criação da classe ChaveApi representando a chave de acesso de um cliente
* Atributos privados: token, plano, limite de requisições, requisições realizadas e status
* Construtor que exige apenas token, plano e limite, iniciando a chave ativa e com contador zerado
* Ausência de setters para o token e para o contador de requisições
* Getters somente de leitura para consulta dos dados
* Método registrarChamada() que bloqueia chaves inativas e impede que o contador ultrapasse o limite
* Método fazerUpgrade() que rejeita a troca de plano caso o novo limite seja menor que o atual
* Método resetarCiclo() para renovar a cota mensal
* Métodos bloquearChave() e desbloquearChave() para controle do status
* Classe Main com testes para cada regra do enunciado

## ▶️ Como executar

1. Acesse a pasta do exercício
2. Compile os arquivos:
   javac ChaveApi.java Main.java
3. Execute:
   java Main

## 📌 Observações

O exercício foi desenvolvido durante a disciplina de Desenvolvimento de Sistemas, com foco em modelar uma classe que protege o próprio estado interno e expõe apenas comportamentos com regras de negócio, em vez de permitir a alteração livre dos seus dados.
