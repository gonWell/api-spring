# **README - Aplicação API Sirio Libanes**

## **Descrição**
Esta aplicação é uma API RESTful desenvolvida em Java usando o framework Spring Boot. Ela implementa autenticação com JWT, gerenciamento de banco de dados com Hibernate e Flyway, e documentação interativa usando Swagger. A API está configurada para operar na porta **8082**.

## **Requisitos**
- **Java 21** ou superior.
- **Maven** para gerenciamento de dependências.
- **MySQL** como banco de dados.
- Ferramenta de cliente HTTP (como **Postman**) para testes (opcional).

---

## **Tecnologias Utilizadas**
1. **Spring Boot**: Framework para desenvolvimento rápido de aplicações Java.
2. **Hibernate**: ORM para persistência de dados.
3. **Flyway**: Gerenciamento de versionamento do banco de dados.
4. **JWT (JSON Web Token)**: Autenticação e autorização segura.
5. **Swagger**: Documentação interativa e explorável da API.

---

## **Configuração**

### **Banco de Dados**
1. **Criação do Banco de Dados:**
   Certifique-se de que o MySQL está rodando e crie um banco de dados para a aplicação.
   ```sql
   CREATE DATABASE samaritano_api;
   ```

2. **Configuração do Banco de Dados:**
   Configure as credenciais do banco no arquivo `application.yml` ou `application.properties`.

   **Exemplo de Configuração:**
   ```yaml
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/samaritano_api
       username: seu_usuario
       password: sua_senha
       driver-class-name: com.mysql.cj.jdbc.Driver
     jpa:
       hibernate:
         ddl-auto: update
       properties:
         hibernate:
           dialect: org.hibernate.dialect.MySQL8Dialect
   ```

3. **Versionamento com Flyway:**
   O Flyway executa automaticamente scripts de migração para criar e popular tabelas no banco. Um usuário padrão será cadastrado:
   - **Usuário JWT Default:**
     - CPF: `12345678901`
     - Este usuário pode ser usado para gerar tokens JWT.

---

## **Como Executar**

1. Clone o repositório do projeto:
   ```bash
   git clone https://github.com/seu-repositorio/api-samaritano.git
   cd api-samaritano
   ```

2. Compile e instale as dependências:
   ```bash
   mvn clean install
   ```

3. Execute a aplicação:
   ```bash
   mvn spring-boot:run
   ```

4. A aplicação estará disponível em: [http://localhost:8082](http://localhost:8082).

---

## **Acesso à Documentação**

A documentação interativa da API está disponível no Swagger. Acesse pelo navegador:
- **URL Swagger:** [http://localhost:8082/swagger-ui/index.html](http://localhost:8082/swagger-ui/index.html)

---

## **Uso do JWT**

### **1. Gerar um Token JWT**
Utilize o endpoint de login para autenticação e obtenha o token:
- **Endpoint:** `POST /api/auth/login?cpf=12345678901`
- O token será retornado no corpo da resposta.

### **2. Usar o Token JWT**
Inclua o token no cabeçalho de todas as requisições autenticadas:
- **Header:**
  ```http
  Authorization: Bearer <seu_token>
  ```

---

## **Testando a API**

1. **Listar Todos os Usuários:**
   - **Endpoint:** `GET /api/usuarios`
   - **Autenticação:** Sim (Token JWT necessário).

2. **Criar um Novo Usuário:**
   - **Endpoint:** `POST /api/usuarios`
   - **Corpo da Requisição:**
     ```json
     {
       "nome": "João da Silva",
       "cpf": "98765432109",
       "dataNascimento": "1990-01-01"
        "endereco:...
     }
     ```

3. Explore outros endpoints diretamente no Swagger!

---

## **Estrutura de Diretórios**

```
src/
├── main/
│   ├── java/
│   │   └── com.samaritano.api/
│   │       ├── auth/           # Autenticação com JWT
│   │       ├── configs/        # Configurações (JWT, segurança, etc.)
│   │       ├── usuario/        # Recursos de usuários
│   │       └── Application.java # Classe principal
│   └── resources/
│       ├── db/migration/       # Scripts Flyway
│       ├── application.yml     # Configurações da aplicação
│       └── static/             # Recursos estáticos (Swagger UI)
└── test/
    └── java/
        └── com.samaritano.api/ # Testes da aplicação
```

---

## **Principais Funcionalidades**

- **Autenticação JWT**: Segurança em todos os endpoints protegidos.
- **Banco de Dados MySQL**: Gerenciado com Hibernate e Flyway.
- **Documentação Swagger**: Interface interativa para explorar a API.
- **Migrações com Flyway**: Automação do schema do banco de dados.

---

Desenvolvido por Wellington Barbosa. 🚀
