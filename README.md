# Desafio Back-end PicPay

Esta é uma API simples de transferência de pagamentos, que permite que os usuários realizem transações de dinheiro entre si. Os usuários podem se cadastrar e efetuar transações.

## Rotas da API

### Cadastrar Usuário

- **URL**: `/usuarios`
- **Método**: `POST`
- **Corpo da Requisição**: Um objeto JSON contendo os dados do usuário, como nome, CPF, e-mail e senha.
- **Resposta de Sucesso**: Retorna o status HTTP 201 Created e uma mensagem indicando que o usuário foi criado com sucesso.
- **Exemplo de Requisição**:
  ```json
  {
    "nome": "João Silva",
    "cpf": "12345678900",
    "email": "joao@example.com",
    "senha": "senha123"
  }
  ```

### Realizar Transação

- **URL**: `/transaction`
- **Método**: `PUT`
- **Parâmetros da Requisição**:
    - `valor` (Double): O valor a ser transferido.
    - `payer` (Long): O ID do usuário que realiza o pagamento.
    - `payee` (Long): O ID do usuário que recebe o pagamento.
- **Resposta de Sucesso**: Retorna o status HTTP 200 OK e uma mensagem indicando que a transação foi realizada com sucesso.
- **Exemplo de Requisição**:
  ```
  /transaction?valor=100.00&payer=1&payee=2
  ```

## Como Usar a API

1. **Cadastrar um Usuário**:
    - Envie uma requisição POST para a rota `/usuarios` com os dados do usuário no corpo da requisição.

2. **Realizar uma Transação**:
    - Envie uma requisição PUT para a rota `/transaction` com os parâmetros `valor`, `payer` e `payee` na URL.

3. **Verificar o Saldo**:
    - Para verificar o saldo de um usuário, você pode implementar uma nova rota na API que receba o ID do usuário como parâmetro e retorne o saldo correspondente.

Lembre-se de implementar a lógica de autenticação, controle de erros e validações adicionais de acordo com as necessidades do seu projeto.

## Exemplos de Respostas

- Resposta de Sucesso (200 OK):
  ```json
  {
    "message": "Transação realizada com sucesso."
  }
  ```

- Resposta de Erro (400 Bad Request):
  ```json
  {
    "error": "Saldo insuficiente."
  }
  ```

## Dependências da API

- Spring Boot: Framework para desenvolvimento de aplicativos Java.
- Banco de Dados: Você pode escolher um banco de dados compatível com Spring Boot, como MySQL ou PostgreSQL.

Certifique-se de configurar corretamente o ambiente de desenvolvimento, as dependências e a conexão com o banco de dados antes de executar a API.

## Autor

Este projeto foi desenvolvido por Gustavo Alencar.