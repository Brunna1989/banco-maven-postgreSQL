# Sistema Bancário

Este é um projeto de sistema bancário simples desenvolvido em Spring Boot,Maven e PostegreSQL.

## Tecnologias Utilizadas

- Java
- Spring Boot
- PostgreSQL (ou outro banco de dados de sua escolha)
- Maven
- JUnit5 e Mockito

## Funcionalidades

O sistema bancário oferece as seguintes funcionalidades:

1. **Sacar Dinheiro:**
    - Endpoint: `/conta/sacar`
    - Método: POST
    - Parâmetros: `numeroConta` (Número da Conta), `valor` (Valor a ser sacado)
    - Retorna uma mensagem de sucesso ou erro.

2. **Depositar Dinheiro:**
    - Endpoint: `/conta/depositar`
    - Método: POST
    - Parâmetros: `numeroConta` (Número da Conta), `valor` (Valor a ser depositado)
    - Retorna uma mensagem de sucesso ou erro.

3. **Extrato da Conta:**
    - Endpoint: `/conta/extrato`
    - Método: GET
    - Retorna uma lista de todas as contas com seus saldos.

## Como Testar no Postman

1. **Sacar Dinheiro:**
    - Método: POST
    - URL: `http://localhost:8080/conta/sacar`
    - Parâmetros: `numeroConta` e `valor`

2. **Depositar Dinheiro:**
    - Método: POST
    - URL: `http://localhost:8080/conta/depositar`
    - Parâmetros: `numeroConta` e `valor`

3. **Extrato da Conta:**
    - Método: GET
    - URL: `http://localhost:8080/conta/extrato`

Lembre-se de substituir `http://localhost:8080` pelo endereço real onde sua aplicação está sendo executada.

## Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para abrir problemas ou enviar pull requests.

