# DEV na Pática Senior - API Pedidos

Projeto criado no DEV na Prática Senior aulas 02 e 03.

### Objetivo
Desenvolver uma API RESTful que permita a criação de pedidos por um cliente cadastrado na aplicação.

**Funcionalidades:**
- Cadastro de cliente;
- Cadastro de produtos;
- Criação e cancelamento de pedidos.

**Não escopo:**
- Interface gráfica;
- Autenticação.

## Tecnologias utilizadas

- Java 8x;
- Maven;
- Spring Framework;
- Spring Boot.

## Conceitos trabalhados

- Gerência de projetos;
- Gerência de dependências;
- Inversão de Controle (IoC);
- Injeção de Dependência (DI);
- API RESTful;
- Orientação a Objetos (OO).

## Convenção utilizada para API

| Ação | Método HTTP | URI | Codigo de resposta|
|------|-------------|-----|-------------------|
| Listar todos/filtrar | `GET` | **/v1/clientes** | 200 |
| Salvar | `POST` | **/v1/clientes** | 200 |
| Buscar | `GET` | **/v1/clientes/{clienteId}** | 204 |
| Alterar | `PUT` | **/v1/clientes/{clienteId}** | 200 |
| Excluir/Deletar | `DELETE` | **/v1/clientes/{clienteId}** | 204 |

------

Gian Pasqualini <gian.pasqualini@senior.com.br>

Luiz Felipe Nazari <luiz.nazari@senior.com.br>

Matheus Raymundo <matheus.raymundo@senior.com.br>
