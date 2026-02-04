# Linketinder - MVP em Groovy

O Linketinder é uma aplicação de console desenvolvida em Groovy, inspirada nos conceitos do LinkedIn e do Tinder, com o objetivo de facilitar a conexão entre candidatos e empresas a partir de competências profissionais.

Este projeto representa um MVP (Minimum Viable Product), criado para validar a ideia inicial da aplicação, utilizando estruturas simples de dados em memória, menu interativo no terminal e princípios básicos de Programação Orientada a Objetos, sem o uso de frameworks.

---

## Funcionalidades

* Manutenção de uma lista de candidatos com no mínimo 5 registros pré-cadastrados


* Manutenção de uma lista de empresas com no mínimo 5 registros pré-cadastrados 


* Cada candidato possui:
  * Nome
  * E-mail
  * CPF
  * Idade
  * Estado
  * CEP
  * Descrição pessoal
  * Lista de competências


* Cada empresa possui:
  * Nome
  * E-mail corporativo
  * CNPJ
  * País
  * Estado
  * CEP
  * Descrição da empresa
  * Lista de competências desejadas


* Menu simples no terminal 



* Opção de listar todos os candidatos


* Opção de listar todas as empresas


* Opção de cadastrar um novo candidato


* Opção de cadastrar uma nova empresa


---

## Estrutura do projeto

```
src/main/groovy/com/project
 ├── model
 │   ├── Pessoa.groovy
 │   ├── PessoaBase.groovy
 │   ├── Candidato.groovy
 │   └── Empresa.groovy
 ├── repository
 │   └── Database.groovy
 ├── ui
 │   ├── Menu.groovy
 │   └── MenuActions.groovy
 └── Main.groovy
```

* model: entidades do domínio e abstrações comuns
* repository: armazenamento em memória (simulação de banco de dados)
* ui: menu e ações do usuário via terminal
* Main: ponto de entrada da aplicação

---

## Como executar o projeto

### Pré-requisitos
* Java JDK 8 ou superior
* Groovy instalado ou IDE com suporte a Groovy

### Executar via terminal
```
groovy src/main/groovy/com/project/Main.groovy
```
---

## Menu da aplicação

```
========= LINKETINDER =========
1 - Listar candidatos
2 - Listar empresas
3 - Cadastrar candidato
4 - Cadastrar empresa
0 - Sair
================================
```
---

## Testes Automatizados
Este projeto possui testes automatizados utilizando o Spock Framework.

Os testes cobrem principalmente as regras de negócio relacionadas ao cadastro de candidatos e empresas, garantindo que os dados sejam persistidos corretamente.

### Tecnologias de Teste
* Spock Framework 2.4
* Groovy 5
* JUnit Platform
* Maven Surefire Plugin

---

## Tecnologias utilizadas

* Groovy
* Java (JDK)
* Programação Orientada a Objetos
* Execução via terminal

## Autoria
Projeto desenvolvido por Amanda Costa

Como parte de uma atividade prática para avaliação.