# Documento de Definição de Projeto: Pitanga WebRunner & Monitor

## 1. Identificação do Sistema
O **Pitanga WebRunner & Monitor** é um ecossistema de back-end baseado em microsserviços projetado para gerenciar e monitorar ambientes de emulação virtual de hardware (placas FPGAs virtuais e periféricos como o módulo TM1638). Inspirado no Sistema de Emulação Pitanga, o projeto visa fornecer uma plataforma web onde estudantes e engenheiros possam gerenciar laboratórios digitais, submeter circuitos descritos em Verilog (RTL) e simular o comportamento de portas lógicas e sinais antes do mapeamento físico.

## 2. Tecnologias Utilizadas
* **Linguagem Principal:** Java 17+ (Requisito de Web II).
* **Framework:** Quarkus (extensões: Hibernate Panache, RESTEasy Reactive, Jackson).
* **Gerenciador de Dependências:** Maven.
* **Banco de Dados:** PostgreSQL.
* **Versionamento e Infraestrutura:** Git (GitHub), Docker e GitHub Actions (Requisito de V&V).

## 3. Funcionalidades e Arquitetura (Web Services)
O sistema é dividido em dois serviços independentes que se comunicam via REST API:

### Web Service A: Lab & Project Manager (O Administrativo)
* **CRUD de Projetos:** Gerenciamento de circuitos dos usuários (ex: contadores, registradores, portas lógicas da série CD4000).
* **Configuração de Placa Virtual:** Mapeamento de pinos lógicos para os periféricos emulados (botões e displays).
* **Persistência:** Armazenamento do histórico de submissões e logs de execução em banco de dados PostgreSQL.

### Web Service B: Bitstream & Signal Analytics Engine (O Técnico)
* **Análise Lógica e Sintática:** Recebimento do arquivo Verilog e validação da integridade das conexões e portas lógicas descritas.
* **Gerador de Estímulos (Waveform):** Processamento da tabela verdade do circuito digital para prever as saídas dos LEDs com base nos estímulos dos botões.
* **Stateless:** Serviço de processamento puro (sem banco de dados direto), focado em performance.

## 4. Viabilidade para as Etapas de V&V
O projeto servirá como base para a implementação de: Análise Estática (Checkstyle/Maven), Testes Unitários (validação da lógica das portas inversoras/combinacionais no Service B), Testes de API (RestAssured validando a comunicação entre Service A e B), Testes de Sistema (Cypress simulando o upload do Verilog pelo usuário) e pipeline de CI/CD via GitHub Actions.
