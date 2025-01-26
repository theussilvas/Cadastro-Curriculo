# Projeto de Cadastro e Envio de Currículos

## Descrição

 O objetivo é criar uma aplicação web para cadastro e envio de currículos. O projeto utiliza Spring Boot para o backend, PostgreSQL como banco de dados e jQuery no frontend. Foram implementados múltiplos recursos adicionais, incluindo:

Testes unitários baseados em classes de equivalência e análise de valor limite.

Validação de números brasileiros com a biblioteca libphonenumber.

Docker Compose para orquestrar contêineres de aplicação e banco de dados.

## Funcionalidades Principais

### 1. Formulário Web para

- Nome

- E-mail

- Telefone (validação com libphonenumber)

- Cargo desejado
- Escolaridade (campo select)
- Observações (opcional)

- Upload de arquivo (somente .doc, .docx ou .pdf, tamanho máximo de 1MB).

### 2.Armazenamento dos dados no banco de dados PostgreSQL, incluindo

- Endereço IP do usuário.
- Data e hora do envio.

### 3.Envio de e-mail com os dados do formulário e o arquivo anexado

### 4.Testes baseados em técnicas de qualidade de software

### 5.Execução via Docker Compose ou configuração manual

## Tecnologias utilizadas

- Backend: Spring Boot 3.1.2

- Banco de Dados: PostgreSQL 15

- Frontend: HTML, CSS, jQuery

- Gerenciamento de Dependências: Maven

- Contêinerização: Docker e Docker Compose

- Validação de Telefone: libphonenumber

- Testes: JUnit

## Como Executar o Projeto

### Opção 1: Execução com Docker Compose

1. Certifique-se de ter o Docker e o Docker Compose instalados.

2. Clone o repositório:

```bash
git clone https://github.com/theussilvas/Cadastro-Curriculo.git

cd  Cadastro-Curriculo
```

3. [Configure o serviço de email](#6-serviço-de-email)

4. Suba os contêineres com o Docker Compose:

     `docker-compose up --build`

A aplicação estará disponível em <http://localhost:80>

### Opção 2: Execução Manual

### Requisitos

- Java 21 ou superior.

- Maven instalado.

- PostgreSQL configurado.

### 1. Clone o repositório

```bash
git clone https://github.com/theussilvas/Cadastro-Curriculo.git

cd  Cadastro-Curriculo
```

### 2. Configure o banco de dados PostgreSQL

Crie um banco de dados chamado curriculos_db

Atualize as credenciais no arquivo application.properties:

```Properties
spring.datasource.url=jdbc:postgresql://localhost:5432/curriculos_db
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
```

### 3. [Configure o Serviço de email](#6-serviço-de-email)

### 4. Compile e execute o projeto

`mvn spring-boot:run`

- Navegue até a pasta frontend e abra o `index.html` direto pelo navegador

## Teste Unitários

Os testes foram implementados utilizando JUnit e cobrem as seguintes áreas:

1. Classes de Equivalência: Verificação de inputs válidos e inválidos no formulário

2. Análise de Valor Limite: Validação de limites para tamanho de arquivos e campos de texto

Para rodar os testes vá até o diretório `/backend` e execute:
`mvn test`

### 6. Serviço de email

É necessário configurar o serviço de email para execução da aplicação e dos testes

1. `cd backend/src/main/resources`
2. No arquivo `applications.properties`, adicione a configuração de email:

```properties
spring.mail.port=
spring.mail.username=
spring.mail.password=
spring.mail.protocol=
spring.mail.properties.mail.smtp.auth=
spring.mail.properties.mail.smtp.starttls.enable=
```

- Adicione as mesmas informações na application.properties do diretório `src/main/test/resouces`

## Bônus Implementados

1. Testes Unitários: Classes de equivalência e análise de valor limite.

2. Validação de Telefone: Implementada com a biblioteca libphonenumber.

3. Docker Compose: Facilita a execução com um comando simples.
