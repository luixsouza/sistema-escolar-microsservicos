# Sistema Escolar - Microsserviços

Projeto desenvolvido como atividade da disciplina de **Arquitetura de Software** do **9° período de Engenharia de Software** do **IFG - Campus Inhumas**.

O objetivo é demonstrar na prática a arquitetura de microsserviços, aplicando padrões como API Gateway, Service Discovery e comunicação entre serviços.

## Arquitetura

```
┌────────────┐       ┌──────────────┐       ┌────────────────────┐
│ escolar-ui │──────▶│  API Gateway │──────▶│  Discovery Server  │
│ (Angular)  │       │   (:8080)    │       │  Eureka (:8761)    │
└────────────┘       └──────┬───────┘       └────────┬───────────┘
                            │          registra-se ▲  │ resolve
                            │                      │  ▼
              ┌─────────────┼─────────────┬────────┴──────────┐
              ▼             ▼             ▼                    ▼
     ┌──────────────┐ ┌───────────┐ ┌──────────────┐ ┌──────────────┐
     │   Serviço    │ │  Serviço  │ │   Serviço    │ │   Serviço    │
     │    Aluno     │ │ Professor │ │  Disciplina  │ │  Matrícula   │
     │   (:8081)    │ │  (:8082)  │ │   (:8083)    │ │   (:8084)    │
     │    H2 DB     │ │   H2 DB   │ │    H2 DB     │ │   H2 DB      │
     └──────────────┘ └───────────┘ └──────────────┘ └──────────────┘
```

Cada microsserviço possui seu próprio banco de dados em memória (H2), seguindo o princípio de **database-per-service**.

## Tecnologias

| Camada         | Tecnologia                          |
|----------------|-------------------------------------|
| Frontend       | Angular 21 + Angular Material       |
| Backend        | Java 21 + Spring Boot 3.4           |
| Service Discovery | Spring Cloud Netflix Eureka      |
| API Gateway    | Spring Cloud Gateway                |
| Banco de Dados | H2 (em memória)                     |
| Containerização| Docker + Docker Compose             |

## Microsserviços

| Serviço              | Porta | Responsabilidade                                  |
|----------------------|-------|---------------------------------------------------|
| discovery-server     | 8761  | Registro e descoberta de serviços (Eureka)        |
| api-gateway          | 8080  | Ponto de entrada único, roteamento de requisições |
| servico-aluno        | 8081  | CRUD de alunos                                    |
| servico-professor    | 8082  | CRUD de professores                               |
| servico-disciplina   | 8083  | CRUD de disciplinas                               |
| servico-matricula    | 8084  | Matrículas (consome aluno e disciplina via Feign) |

## Como executar

```bash
docker compose up --build
```

Acesse a aplicação em `http://localhost:4200`.

## Testando com Swagger

Cada microsserviço expõe uma interface Swagger UI para testar os endpoints da API diretamente pelo navegador.

| Serviço           | Swagger UI                                      |
|-------------------|------------------------------------------------|
| servico-aluno     | http://localhost:8081/swagger-ui.html           |
| servico-professor | http://localhost:8082/swagger-ui.html           |
| servico-disciplina| http://localhost:8083/swagger-ui.html           |
| servico-matricula | http://localhost:8084/swagger-ui.html           |

Abra qualquer um dos links acima, selecione o endpoint desejado, clique em **Try it out**, preencha os parâmetros e clique em **Execute** para enviar a requisição.
