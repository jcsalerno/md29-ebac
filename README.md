# Sistema de Gestão de Clientes e Produtos

Este é um sistema simples de gestão de clientes e produtos, desenvolvido em Java, utilizando JDBC para manipulação de dados em um banco relacional.

## Funcionalidades

O sistema possui duas principais funcionalidades:

1. **Gerenciamento de Clientes**
    - Cadastro de novos clientes
    - Atualização de clientes existentes
    - Busca de cliente por código
    - Listagem de todos os clientes
    - Exclusão de clientes

2. **Gerenciamento de Produtos**
    - Cadastro de novos produtos
    - Atualização de produtos existentes
    - Busca de produto por código
    - Listagem de todos os produtos
    - Exclusão de produtos

## Tecnologias Utilizadas

- **Java 8+**
- **JDBC** para comunicação com o banco de dados
- **PostgreSQL** (pode ser adaptado para outros bancos relacionais)
- **Logger** para registro de logs

## Estrutura do Projeto

- **domain**: Contém as classes de domínio `Cliente` e `Produto`.
- **dao**: Contém os DAOs responsáveis pela manipulação de dados no banco de dados.
    - `ClienteDAO`
    - `ProdutoDAO`
- **org.example.Main**: Classe principal que contém o menu e a interação com o usuário via console.

## Pré-requisitos

1. **Banco de Dados**
    - Configure um banco de dados relacional (ex.: PostgreSQL).
    - Crie as tabelas necessárias:

      ```sql
      CREATE TABLE TB_CLIENTE (
          ID SERIAL PRIMARY KEY,
          CODIGO VARCHAR(50) UNIQUE NOT NULL,
          NOME VARCHAR(100) NOT NULL
      );
 
      CREATE TABLE TB_PRODUTO (
          ID SERIAL PRIMARY KEY,
          CODIGO VARCHAR(50) UNIQUE NOT NULL,
          NOME VARCHAR(100) NOT NULL,
          DESCRICAO TEXT,
          PRECO NUMERIC(10, 2) NOT NULL
      );
 
      CREATE SEQUENCE SQ_PRODUTO START 1;
      ```

2. **Configuração de Conexão**
    - Certifique-se de que a classe `ConnectionFactory` está configurada com as credenciais corretas para o banco de dados:

      ```java
      public class ConnectionFactory {
          private static final String URL = "jdbc:postgresql://localhost:5432/sistema_gestao";
          private static final String USER = "usuario";
          private static final String PASSWORD = "senha";
 
          public static Connection getConnection() throws SQLException {
              return DriverManager.getConnection(URL, USER, PASSWORD);
          }
      }
      ```

## Como Executar o Projeto

1. Clone o repositório.
2. Configure o banco de dados conforme descrito acima.
3. Compile e execute a classe `Main`.
4. Use o menu interativo para gerenciar clientes e produtos.

## Melhorias Futuras

- Implementar interface gráfica para substituir o menu de console.
- Adicionar testes unitários para as classes DAO.
- Incluir validações mais robustas para os dados inseridos.
- Suporte a transações para operações que envolvem múltiplos passos.
- Internacionalização do sistema (i18n).

## Licença

Este projeto é livre para uso e modificação. Não possui uma licença específica.
