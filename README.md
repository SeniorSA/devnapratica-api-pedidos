# DEV na Pática Senior - API Pedidos

Projeto criado no DEV na Prática Senior aulas 02 e 03 (aulas 01 e 02 de Fundamentos).

## Aula 01 - Maven e Spring Core

- [Voltar para branch master](https://github.com/SeniorSA/devnapratica-api-pedidos/tree/master)

### Objetivo
Desenvolver uma API RESTful que permita a criação de pedidos por um cliente cadastrado na aplicação para aplicar os conceitos aprendidos em aula. 

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
| Salvar | `POST` | **/v1/clientes** | 201 |
| Buscar | `GET` | **/v1/clientes/{clienteId}** | 200 (ou 404) |
| Alterar | `PUT` | **/v1/clientes/{clienteId}** | 200 |
| Excluir/Deletar | `DELETE` | **/v1/clientes/{clienteId}** | 204 |

<br> 
------

Luiz Felipe Nazari <luiz.nazari@senior.com.br>

Matheus Raymundo <matheus.raymundo@senior.com.br>
