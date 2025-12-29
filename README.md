# 💰💻 Sistema de E-Commerce

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5-green?style=for-the-badge&logo=springboot)
![H2](https://img.shields.io/badge/H2-Database-blue?style=for-the-badge)
![Swagger](https://img.shields.io/badge/Swagger-OpenAPI%203.0-85EA2D?style=for-the-badge&logo=swagger)
![License](https://img.shields.io/badge/License-MIT-yellow?style=for-the-badge)

> API REST para gerenciamento completo de pedidos com relacionamentos entre usuários, produtos, categorias e pagamentos

---

## 📋 Sobre o Projeto 
O WorkShop-Springboot é uma API REST de E-Commerce desenvolvida em Java com Spring Boot, projetada para demonstrar a construção de um sistema backend completo para gerenciamento de pedidos, usuários, produtos, categorias e pagamentos.
O projeto serve como um exemplo prático de como utilizar o ecossistema Spring — incluindo Spring Boot e bibliotecas como Spring Data JPA — para criar uma aplicação robusta com relacionamentos entre entidades e operações
típicas de um comércio eletrônico. Ele funciona como uma base didática e funcional para quem está aprendendo desenvolvimento backend com Spring Boot, aplicando conceitos importantes como arquitetura REST,
persistência de dados em banco relacional (via JPA/Hibernate), e separação de responsabilidades entre camadas (Controller, Service, Repository).

### ✨ Principais Características

- ✅ **CRUD Completo** - Criar, listar, atualizar e deletar usuários
- ✅ **Validações Robustas** - Bean Validation para garantir integridade dos dados
- ✅ **Tratamento de Exceções** - Respostas HTTP padronizadas e mensagens de erro claras
- ✅ **Documentação Interativa** - Swagger UI para testar endpoints facilmente
- ✅ **Perfis de Ambiente** - Configurações separadas para desenvolvimento e produção
- ✅ **Persistência em H2 Database** - Banco de dados para testes
- ✅ **Consumo de Api externa com RestTemplate** - Classe para consumo de APis
---

## 🚀 Tecnologias Utilizadas

### Back-end
- **Java 21** - Linguagem de programação
- **Spring Boot 3.5** - Framework para desenvolvimento de aplicações
- **Spring Data JPA** - Persistência de dados
- **Hibernate** - ORM (Object-Relational Mapping)

### Banco de Dados
- **H2 Database** - Banco de dados de teste relacional

### Documentação
- **SpringDoc OpenAPI 3** - Geração automática de documentação
- **Swagger UI** - Interface interativa para testes

### Validação
- **Bean Validation (Jakarta)** - Validação de dados de entrada

### Build & Deploy
- **Maven** - Gerenciamento de dependências
---

## 📸 Screenshots

### Swagger UI - Endpoints Disponíveis
![Swagger UI](public/)

### Exemplo de Resposta
![JSON Response](public/)

---

## 🛠️ Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina:

- [Java JDK 21+](https://www.oracle.com/java/technologies/downloads/)
- [Maven 3.8+](https://maven.apache.org/download.cgi)
- [Git](https://git-scm.com/downloads)

---

## 🔧 Como Executar o Projeto

### 1️⃣ Clone o repositório

```bash
git clone https://github.com/PedroNunes-Dev67/Workshop-Sprinboot.git
cd Workshop-Sprinboot
```

### 2️⃣ Execute a aplicação

```bash
# Usando Maven
mvn spring-boot:run

# Ou compilando o JAR
mvn clean package
java -jar target/workshop-sprinboot-0.0.1-SNAPSHOT.jar
```

### 3️⃣ Acesse a documentação Swagger

Abra seu navegador e acesse:

```
http://localhost:8080/swagger-ui.html
```

---
