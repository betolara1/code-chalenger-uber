# FilmsLocation 🎬

Uma aplicação Spring Boot que fornece informações sobre locais de filmagem de filmes em San Francisco.

## 📋 Descrição

FilmsLocation é uma API REST que permite consultar filmes que foram gravados em San Francisco. A aplicação utiliza OpenFeign para fazer requisições a uma API externa de filmes (SF Films) e expõe endpoints para buscar todos os filmes ou pesquisar por título.

## 🛠️ Tecnologias

- **Java**: 21
- **Spring Boot**: 3.5.9
- **Spring Cloud**: 2025.0.1
- **Spring Cloud OpenFeign**: Cliente HTTP declarativo
- **Lombok**: Redução de boilerplate
- **Maven**: Gerenciador de dependências
- **Docker**: Containerização

## 📦 Dependências Principais

```xml
- spring-boot-starter-web
- spring-cloud-starter-openfeign
- spring-boot-devtools
- projectlombok
```

## 🚀 Como Executar

### Pré-requisitos

- Java 21 instalado
- Maven instalado (ou usar o `mvnw` incluído)

### Localmente

1. Clone o repositório
```bash
git clone <seu-repositorio>
cd MoviesLocation
```

2. Execute a aplicação
```bash
./mvnw spring-boot:run
```

Ou no Windows:
```bash
mvnw.cmd spring-boot:run
```

3. A aplicação estará disponível em: `http://localhost:8080`

### Com Docker

1. Build da imagem
```bash
docker build -t filmslocation .
```

2. Execute com Docker Compose
```bash
docker-compose up
```

## 🔗 Endpoints

### GET `/api/films/allFilms`
Retorna a lista de todos os filmes gravados em San Francisco.

**Exemplo de requisição:**
```bash
curl http://localhost:8080/api/films/allFilms
```

**Resposta:**
```json
[
  {
    "id": 1,
    "title": "Film Title",
    "location": "Location Name"
  }
]
```

### GET `/api/films/search`
Pesquisa filmes por título.

**Parâmetros:**
- `title` (query parameter): Título do filme a buscar

**Exemplo de requisição:**
```bash
curl http://localhost:8080/api/films/search?title=The%20Room
```

**Resposta:**
```json
[
  {
    "id": 1,
    "title": "The Room",
    "location": "Location Name"
  }
]
```

## ⚙️ Configuração

As configurações estão no arquivo `application.properties`:

```properties
spring.application.name=FilmsLocation
app_token=7WWbcr7p4u2ZXvFOEmeJcLW3F
```

| Propriedade | Descrição |
|---|---|
| `spring.application.name` | Nome da aplicação |
| `app_token` | Token de autenticação para a API externa SF Films |

## 📁 Estrutura do Projeto

```
src/main/java/com/films/FilmsLocation/
├── FilmsLocationApplication.java  # Classe principal
├── client/
│   └── SfFilms.java              # Cliente Feign para API externa
├── controller/
│   └── FilmsController.java       # Endpoints REST
└── dto/
    └── FilmsDTO.java             # Data Transfer Object
```

## 🔒 Tratamento de Segurança

A aplicação trata caracteres especiais (como aspas simples) nas buscas por título para prevenir SQL Injection:

```java
String treatedTitleString = title.replace("'", "''");
```

## 📝 Notas de Desenvolvimento

- Use `spring-boot-devtools` para hot reload durante desenvolvimento
- O projeto segue padrões Spring Boot com separação de responsabilidades (Controller, Client, DTO)
- Feign é utilizado para simplificar requisições HTTP à API externa

## 🧪 Testes

Execute os testes com:
```bash
./mvnw test
```

## 📄 Licença

Este projeto está sob licença [Adicionar sua licença aqui]

## 👨‍💻 Autor

Desenvolvido como aplicação educacional para integração com APIs externas usando Spring Boot e OpenFeign.

---

**Versão:** 0.0.1-SNAPSHOT
