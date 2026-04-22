# 👥 Microsserviço de Usuário com RabbitMQ

[![Java](https://img.shields.io/badge/Java-25-orange?style=flat-square&logo=openjdk)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.5-green?style=flat-square&logo=springboot)](https://spring.io/projects/spring-boot)
[![RabbitMQ](https://img.shields.io/badge/RabbitMQ-AMQP-FF6600?style=flat-square&logo=rabbitmq)](https://www.rabbitmq.com/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15+-blue?style=flat-square&logo=postgresql)](https://www.postgresql.org/)
[![License](https://img.shields.io/badge/License-MIT-blue?style=flat-square)](LICENSE)

> Microsserviço de gerenciamento de usuários desenvolvido em **Spring Boot 4.0.5**, com comunicação assíncrona via **RabbitMQ** e persistência em **PostgreSQL**.

🔗 **Integração**: Ao criar ou atualizar um usuário, eventos são publicados no RabbitMQ e consumidos pelo [📧 Microsserviço de Email](https://github.com/Arthur-Luiz19/Microsservicos-email-RabbitMQ) para envio automático de notificações.

---

## 📋 Índice

- [Sobre o Projeto](#-sobre-o-projeto)
- [Funcionalidades](#-funcionalidades)
- [Arquitetura e Fluxo de Eventos](#-arquitetura-e-fluxo-de-eventos)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Pré-requisitos](#-pré-requisitos)
- [Configuração e Execução](#-configuração-e-execução)
- [Configurações Principais](#-configurações-principais)
- [Modelo de Eventos Publicados](#-modelo-de-eventos-publicados)
- [Endpoints da API](#-endpoints-da-api)
- [Testes](#-testes)
- [Contribuindo](#-contribuindo)
- [Licença](#-licença)

---

## 🎯 Sobre o Projeto

Este microsserviço é o **serviço produtor** em uma arquitetura orientada a eventos. Ele centraliza todas as operações de **cadastro, consulta, atualização e exclusão de usuários**, publicando eventos de domínio no RabbitMQ sempre que o estado de um usuário é alterado.

### Por que usar eventos assíncronos?

| Vantagem | Descrição |
|----------|-----------|
| ⚡ **Baixa latência** | O cliente recebe resposta imediata; envio de e-mail ocorre em background |
| 🔄 **Resiliência** | Falhas no serviço de e-mail não impedem o cadastro do usuário |
| 📈 **Escalabilidade** | Múltiplos consumidores podem processar o mesmo evento independentemente |
| 🧩 **Desacoplamento** | O serviço de usuário não conhece os detalhes de envio de e-mail |

---

## ✨ Funcionalidades

- ✅ Criação de usuários
- ✅ Validação de dados com Bean Validation (`@NotBlank`, `@Email`, `@Pattern`)
- ✅ Publicação assíncrona de eventos no RabbitMQ (`RabbitTemplate`)
- ✅ Persistência com PostgreSQL e Spring Data JPA
- ✅ Retry automático e Dead Letter Queue (DLQ) para resiliência
- ✅ Paginação e filtros na listagem de usuários
- ✅ Health check com Spring Actuator
- ✅ Suporte a variáveis de ambiente para configurações sensíveis

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Versão | Descrição |
|------------|--------|-----------|
| **Java** | 25 | Linguagem base do projeto |
| **Spring Boot** | 4.0.5 | Framework para desenvolvimento rápido |
| **Spring Web** | 4.0.x | Criação de APIs REST |
| **Spring AMQP** | 4.0.x | Integração com RabbitMQ |
| **Spring Data JPA** | 4.0.x | Persistência com PostgreSQL |
| **Spring Validation** | 4.0.x | Validação de DTOs e entidades |
| **RabbitMQ** | 3.12+ | Broker de mensagens AMQP |
| **PostgreSQL** | 15+ | Banco de dados relacional |
| **Maven** | 3.9+ | Gerenciamento de dependências |
| **Lombok** | 1.18.x | Redução de boilerplate |
| **Springdoc OpenAPI** | 2.5+ | Documentação automática da API |

---

## 📋 Pré-requisitos

Antes de começar, certifique-se de ter instalado:

- [JDK 25](https://www.oracle.com/java/technologies/downloads/)
- [Maven 3.9+](https://maven.apache.org/download.cgi)
- [Docker & Docker Compose](https://docs.docker.com/get-docker/)
- [RabbitMQ](https://www.rabbitmq.com/download.html) (ou via Docker)
- [PostgreSQL 15+](https://www.postgresql.org/download/) (ou via Docker)

---

