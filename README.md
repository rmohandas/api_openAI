# 🤖 Backend - Integração com Azure OpenAI usando Spring AI

Este projeto é uma API backend desenvolvida com **Spring Boot 3.5** e **Java 21**, que expõe endpoints para interações com modelos da OpenAI através da **Azure OpenAI Service**, utilizando a biblioteca **Spring AI**.

## 🚀 Tecnologias Utilizadas

- Java 21
- Spring Boot 3.5
- Spring AI
- Azure OpenAI Service
- Maven

## 🧠 Funcionalidades da API

A aplicação disponibiliza os seguintes endpoints:

### 📘 1. Interação com Chat

- **Método:** `GET`
- **Endpoint:** `ask-ai`
- **Descrição:** Envia uma mensagem e recebe uma resposta textual do modelo.
- **Aplicação:** Chatbot, assistente virtual, suporte automatizado.

- **Método:** `GET`
- **Endpoint:** `ask-ai-options`
- **Descrição:** Envia uma mensagem e recebe uma resposta textual do modelo.
- **Descrição:** Envia parametros como deploymentName("gpt-4o-mini") e temperature(0.4).
- **Aplicação:** Chatbot, assistente virtual, suporte automatizado.

---

### 🍲 2. Geração de Receita com Prompt Template

- **Método:** `POST`
- **Endpoint:** `recipe-creator`
- **Descrição:** Gera uma receita culinária personalizada com base em entradas do usuário.
- **Técnica:** Utilização de `PromptTemplate` para criação dinâmica de prompts.

---

### 🖼️ 3. Geração de Imagens

- **Método:** `POST`
- **Endpoint:** `generate-image`
- **Descrição:** Gera uma imagem com base em uma descrição textual (prompt).
- **Aplicação:** Geração de conteúdo visual automatizado.

---

## ⚙️ Como Executar

```bash
# Clone o repositório
https://github.com/rmohandas/api_openAI.git

# Acesse o diretório do projeto
cd seu-repo

# Configure suas credenciais da Azure no application.yml ou application.properties

# Execute o projeto
./mvnw spring-boot:run
