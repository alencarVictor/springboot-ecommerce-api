# 🛒 Ecommerce API

API REST para gerenciamento de pedidos de um e-commerce, desenvolvida com **Spring Boot 3** e **Amazon DynamoDB**.

---

## 🚀 Tecnologias

- Java 17
- Spring Boot 3.5
- Spring Web (REST)
- AWS SDK v2 (DynamoDB)
- Maven

---

## 📁 Estrutura do Projeto

```
src/
└── main/
    └── java/com/victor/ecommerceapi/
        ├── config/
        │   └── AwsConfig.java          # Configuração do cliente DynamoDB
        ├── controller/
        │   └── OrderController.java    # Endpoints REST de pedidos
        ├── model/
        │   ├── Order.java
        │   ├── OrderItem.java
        │   ├── OrderStatus.java        # Enum: CREATED, PAID, SHIPPED, DELIVERED
        │   └── Product.java
        └── service/
            └── OrderService.java       # Regras de negócio
```

---

## ⚙️ Pré-requisitos

- Java 17+
- Maven 3.8+
- Conta AWS com permissão no DynamoDB **ou** Docker (para DynamoDB Local)

---

## 🗄️ Configuração do DynamoDB

### Opção A — DynamoDB Local com Docker (recomendado para desenvolvimento)

```bash
docker run -p 8000:8000 amazon/dynamodb-local
```

Crie a tabela localmente:

```bash
aws dynamodb create-table \
  --table-name orders \
  --attribute-definitions AttributeName=id,AttributeType=S \
  --key-schema AttributeName=id,KeyType=HASH \
  --billing-mode PAY_PER_REQUEST \
  --endpoint-url http://localhost:8000
```

### Opção B — AWS Real (região sa-east-1)

Configure suas credenciais AWS:

```bash
aws configure
```

Crie a tabela na AWS:

```bash
aws dynamodb create-table \
  --table-name orders \
  --attribute-definitions AttributeName=id,AttributeType=S \
  --key-schema AttributeName=id,KeyType=HASH \
  --billing-mode PAY_PER_REQUEST \
  --region sa-east-1
```

---

## ▶️ Como rodar

```bash
# Clone o repositório
git clone https://github.com/alencarVictor/springboot-ecommerce-api.git
cd springboot-ecommerce-api

# Rode a aplicação
./mvnw spring-boot:run
```

A API estará disponível em: `http://localhost:8080`

---

## 📦 Endpoints

### Orders

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/orders` | Lista todos os pedidos |
| `GET` | `/orders/{id}` | Busca pedido por ID |
| `POST` | `/orders` | Cria um novo pedido |
| `PATCH` | `/orders/{id}` | Atualiza o status do pedido |
| `DELETE` | `/orders/{id}` | Remove um pedido |

---

### Exemplos de Requisição

**Criar pedido**
```http
POST /orders
Content-Type: application/json

{
  "items": [
    {
      "product": {
        "id": 1,
        "name": "Camiseta",
        "price": 59.90,
        "stock": 10
      },
      "quantity": 2
    }
  ]
}
```

**Resposta:**
```json
{
  "id": 1,
  "items": [...],
  "total": 119.80,
  "status": "CREATED",
  "createdAt": "2025-06-16T10:00:00"
}
```

**Atualizar status**
```http
PATCH /orders/1
Content-Type: application/json

{
  "status": "PAID"
}
```

**Status disponíveis:** `CREATED` → `PAID` → `SHIPPED` → `DELIVERED`

---

## 🧪 Testes

```bash
./mvnw test
```

---

## 📌 Observações

- Os dados de pedidos são mantidos em memória durante a sessão; apenas a criação (`POST`) persiste no DynamoDB.
- A aplicação usa a região `sa-east-1` (São Paulo) por padrão.
- Certifique-se de que as credenciais AWS estão configuradas no ambiente antes de iniciar.

---

## 👨‍💻 Autor

**Victor Alencar** — [github.com/alencarVictor](https://github.com/alencarVictor)