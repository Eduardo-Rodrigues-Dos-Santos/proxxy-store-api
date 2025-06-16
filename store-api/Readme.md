# 🛒 Proxxy Store - Ambiente de Desenvolvimento com OAuth2

Este projeto é composto por:

- 🔐 **Authorization Server** (`auth`)
- 🔧 **Resource Server** (`store-api`)
- 🗄️ **Banco de Dados MySQL**
- 🌐 **Nginx (Load Balancer)**

A aplicação está configurada com o profile `development`, o qual executa um `afterMigrate` responsável por inserir dados de teste no banco automaticamente ao subir os containers.

---

## 🚀 Como Executar a Aplicação

### ✅ Pré-requisitos

- Docker e Docker Compose instalados
- Permissão para editar o arquivo `hosts` da sua máquina

### ⚙️ Passo a Passo

1. **Configure os subdomínios locais para o Nginx funcionar corretamente.**

   Adicione as seguintes linhas ao seu arquivo `hosts`:

   ```
   127.0.0.1 auth.localhost
   127.0.0.1 api.localhost
   ```

    - **Linux:** `/etc/hosts`
    - **Windows:** `C:\Windows\System32\drivers\etc\hosts`

2. **Execute os containers.**

   Navegue até o diretório `store-api` e execute:

   ```bash
   docker-compose up --build
   ```

   Esse comando irá subir os seguintes serviços:

    - Authorization Server (`auth`)
    - Resource Server (`store-api`)
    - Banco de dados MySQL
    - Nginx (reverse proxy)

---

## 🧪 Dados de Teste

### 👤 Usuário Padrão

| Campo  | Valor                      |
|--------|----------------------------|
| Email  | EduardoSantos@email.com    |
| Senha  | contratado                 |

### ⚙️ Informações do Cliente OAuth2

| Campo                        | Valor                                 |
|-----------------------------|---------------------------------------|
| `client_id`                 | `web-app`                             |
| `authorization_grant_type` | `authorization_code` + `PKCE`         |
| `refresh_token`             | Habilitado                            |
| `redirect_uri`              | `http://web-app/callback`             |

---

## 🧩 Executar Componentes Individualmente

Se desejar, é possível executar os componentes de forma isolada:

- Authorization Server (`auth`) → possui seu próprio `Dockerfile`
- Resource Server (`store-api`) → possui seu próprio `Dockerfile`

---

## 🌐 Comunicação entre Serviços

As requisições devem ser feitas aos seguintes subdomínios:

| Tipo de Serviço        | Subdomínio              |
|------------------------|--------------------------|
| Authorization Server   | http://auth.localhost    |
| Resource Server (API)  | http://api.localhost     |

O Nginx atua como balanceador e roteador, encaminhando as requisições para o serviço correspondente com base no subdomínio utilizado.

---


## 📘 Documentação da API

A documentação da API (Swagger UI) pode ser acessada através do seguinte endereço:

🔗 [http://api.localhost/swagger-ui](http://api.localhost/swagger-ui)

---

## 🧱 Tecnologias Utilizadas

- ☕ Java 17
- 🌱 Spring Framework
- 🔑 OAuth2
- 🔐 Spring Security
- 🛡️ Spring Authorization Server
- 🛠️ Hibernate (JPA)
- 📜 Flyway (Migrations)
- 🐬 MySQL
- 🧪 JUnit
- 🧰 Mockito
- 🐳 Docker

---