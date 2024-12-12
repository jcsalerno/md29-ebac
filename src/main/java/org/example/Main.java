package org.example;
import dao.ClienteDAO;
import dao.ProdutoDAO;
import domain.Cliente;
import domain.Produto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClienteDAO clienteDAO = new ClienteDAO();
        ProdutoDAO produtoDAO = new ProdutoDAO();

        try {
            while (true) {
                System.out.println("\n===== Sistema de Gestão =====");
                System.out.println("1. Gerenciar Clientes");
                System.out.println("2. Gerenciar Produtos");
                System.out.println("3. Sair");
                System.out.print("Escolha uma opção: ");
                int opcao = scanner.nextInt();
                scanner.nextLine();

                if (opcao == 1) {
                    gerenciarClientes(clienteDAO, scanner);
                } else if (opcao == 2) {
                    gerenciarProdutos(produtoDAO, scanner);
                } else if (opcao == 3) {
                    System.out.println("Saindo do sistema...");
                    break;
                } else {
                    System.out.println("Opção inválida!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static void gerenciarClientes(ClienteDAO clienteDAO, Scanner scanner) throws Exception {
        while (true) {
            System.out.println("\n--- Gerenciar Clientes ---");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Atualizar Cliente");
            System.out.println("3. Buscar Cliente");
            System.out.println("4. Listar Clientes");
            System.out.println("5. Excluir Cliente");
            System.out.println("6. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 1) {
                Cliente cliente = new Cliente();
                System.out.print("Digite o código do cliente: ");
                cliente.setCodigo(scanner.nextLine());
                System.out.print("Digite o nome do cliente: ");
                cliente.setNome(scanner.nextLine());
                clienteDAO.cadastrar(cliente);
                System.out.println("Cliente cadastrado com sucesso!");

            } else if (opcao == 2) {
                System.out.print("Digite o código do cliente para atualizar: ");
                String codigo = scanner.nextLine();
                Cliente cliente = clienteDAO.buscar(codigo);
                if (cliente != null) {
                    System.out.print("Digite o novo nome do cliente: ");
                    cliente.setNome(scanner.nextLine());
                    clienteDAO.atualizar(cliente);
                    System.out.println("Cliente atualizado com sucesso!");
                } else {
                    System.out.println("Cliente não encontrado!");
                }

            } else if (opcao == 3) {
                System.out.print("Digite o código do cliente para buscar: ");
                String codigo = scanner.nextLine();
                Cliente cliente = clienteDAO.buscar(codigo);
                if (cliente != null) {
                    System.out.println("Cliente encontrado: " + cliente.getNome());
                } else {
                    System.out.println("Cliente não encontrado!");
                }

            } else if (opcao == 4) {
                List<Cliente> listaClientes = clienteDAO.buscarTodos();
                System.out.println("\n--- Lista de Clientes ---");
                for (Cliente c : listaClientes) {
                    System.out.println("Código: " + c.getCodigo() + ", Nome: " + c.getNome());
                }

            } else if (opcao == 5) {
                System.out.print("Digite o código do cliente para excluir: ");
                String codigo = scanner.nextLine();
                Cliente cliente = clienteDAO.buscar(codigo);
                if (cliente != null) {
                    clienteDAO.excluir(cliente);
                    System.out.println("Cliente excluído com sucesso!");
                } else {
                    System.out.println("Cliente não encontrado!");
                }

            } else if (opcao == 6) {
                break;

            } else {
                System.out.println("Opção inválida!");
            }
        }
    }

    private static void gerenciarProdutos(ProdutoDAO produtoDAO, Scanner scanner) throws Exception {
        while (true) {
            System.out.println("\n--- Gerenciar Produtos ---");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Atualizar Produto");
            System.out.println("3. Buscar Produto");
            System.out.println("4. Listar Produtos");
            System.out.println("5. Excluir Produto");
            System.out.println("6. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir o caractere de nova linha

            if (opcao == 1) {
                Produto produto = new Produto();
                System.out.print("Digite o código do produto: ");
                produto.setCodigo(scanner.nextLine());
                System.out.print("Digite o nome do produto: ");
                produto.setNome(scanner.nextLine());
                System.out.print("Digite a descrição do produto: ");
                produto.setDescricao(scanner.nextLine());
                System.out.print("Digite o preço do produto: ");
                produto.setPreco(BigDecimal.valueOf(scanner.nextDouble()));
                scanner.nextLine(); // Consumir o caractere de nova linha
                produtoDAO.cadastrar(produto);
                System.out.println("Produto cadastrado com sucesso!");

            } else if (opcao == 2) {
                System.out.print("Digite o código do produto para atualizar: ");
                String codigo = scanner.nextLine();
                Produto produto = produtoDAO.buscar(codigo);
                if (produto != null) {
                    System.out.print("Digite o novo nome do produto: ");
                    produto.setNome(scanner.nextLine());
                    System.out.print("Digite o novo preço do produto: ");
                    produto.setPreco(BigDecimal.valueOf(scanner.nextDouble()));
                    scanner.nextLine(); // Consumir o caractere de nova linha
                    produtoDAO.atualizar(produto);
                    System.out.println("Produto atualizado com sucesso!");
                } else {
                    System.out.println("Produto não encontrado!");
                }

            } else if (opcao == 3) {
                System.out.print("Digite o código do produto para buscar: ");
                String codigo = scanner.nextLine();
                Produto produto = produtoDAO.buscar(codigo);
                if (produto != null) {
                    System.out.println("Produto encontrado: " + produto.getNome());
                } else {
                    System.out.println("Produto não encontrado!");
                }

            } else if (opcao == 4) {
                List<Produto> listaProdutos = produtoDAO.buscarTodos();
                System.out.println("\n--- Lista de Produtos ---");
                for (Produto p : listaProdutos) {
                    System.out.println("Código: " + p.getCodigo() + ", Nome: " + p.getNome() + ", Preço: " + p.getPreco());
                }

            } else if (opcao == 5) {
                System.out.print("Digite o código do produto para excluir: ");
                String codigo = scanner.nextLine();
                Produto produto = produtoDAO.buscar(codigo);
                if (produto != null) {
                    produtoDAO.excluir(produto);
                    System.out.println("Produto excluído com sucesso!");
                } else {
                    System.out.println("Produto não encontrado!");
                }

            } else if (opcao == 6) {
                break;

            } else {
                System.out.println("Opção inválida!");
            }
        }
    }
}
