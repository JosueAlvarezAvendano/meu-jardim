# 🌱 Meu Jardim

Catálogo pessoal de plantas — Projeto Integrador SPTech 3º Semestre

Aplicação web para cadastro e consulta de plantas pessoais. O usuário pode registrar as plantas do seu jardim com informações de cuidado e consultá-las quando quiser.

---

## 🛠️ Tecnologias

**Back-end**
- Java 21
- Spring Boot
- JdbcTemplate
- MySQL

**Front-end**
- React
- Vite
- CSS Modules

---

## 📁 Estrutura do Projeto

```
meu-jardim/
├── api/        → Back-end Spring Boot
├── cliente/    → Front-end React
└── README.md
```

---

## ▶️ Como rodar o projeto

### Pré-requisitos
- Java 21
- Maven
- MySQL
- Node.js

### Back-end

1. Entre na pasta `api/`
2. Copie o arquivo de exemplo e renomeie:
```
src/main/resources/application.properties.example
→
src/main/resources/application.properties
```
3. Preencha com suas credenciais do MySQL:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/meu_jardim?createDatabaseIfNotExist=true
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
```
4. Rode a aplicação — o banco e a tabela são criados automaticamente:
```bash
./mvnw spring-boot:run
```
A API estará disponível em `http://localhost:8080`

### Front-end

1. Entre na pasta `cliente/`
2. Instale as dependências:
```bash
npm install
```
3. Rode a aplicação:
```bash
npm run dev
```
O site estará disponível em `http://localhost:5173`

---

## 🌿 Endpoints da API

| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/plantas` | Lista todas as plantas |
| GET | `/plantas/{id}` | Busca uma planta pelo ID |
| POST | `/plantas` | Cadastra uma nova planta |

### Exemplo de requisição POST

```json
{
  "nome": "Samambaia",
  "especie": "Nephrolepis exaltata",
  "tipo": "Erva",
  "frequenciaRega": "Semanal",
  "nivelLuz": "Meia sombra",
  "descricao": "Fica na varanda"
}
```

### Exemplo de resposta GET

```json
[
  {
    "id": 1,
    "nome": "Samambaia",
    "especie": "Nephrolepis exaltata",
    "tipo": "Erva",
    "frequenciaRega": "Semanal",
    "nivelLuz": "Meia sombra",
    "descricao": "Fica na varanda"
  }
]
```

---

## 👨‍💻 Autor

Josué Alvarez Avendano — SPTech 3º Semestre ADS