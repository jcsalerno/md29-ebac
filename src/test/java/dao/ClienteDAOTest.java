package dao;

import domain.Cliente;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteDAOTest {

    @Test
    public void testCadastrarCliente() {
        try {
            // Arrange
            ClienteDAO clienteDAO = new ClienteDAO();
            Cliente cliente = new Cliente();
            cliente.setCodigo("TESTE001");
            cliente.setNome("João da Silva");

            // Act
            Integer linhasAfetadas = clienteDAO.cadastrar(cliente);

            // Assert
            assertEquals(1, linhasAfetadas);
            Cliente clienteCadastrado = clienteDAO.buscar("TESTE001");
            assertEquals(cliente.getCodigo(), clienteCadastrado.getCodigo());
            assertEquals(cliente.getNome(), clienteCadastrado.getNome());
        } catch (SQLException e) {
            fail("Erro ao cadastrar cliente: " + e.getMessage());
        }
    }

    @Test
    public void testAtualizarCliente() {
        try {
            // Arrange
            ClienteDAO clienteDAO = new ClienteDAO();
            Cliente cliente = new Cliente();
            cliente.setCodigo("TESTE001");
            cliente.setNome("João da Silva");
            clienteDAO.cadastrar(cliente);

            cliente.setNome("João da Silva Atualizado");

            // Act
            Integer linhasAfetadas = clienteDAO.atualizar(cliente);

            // Assert
            assertEquals(1, linhasAfetadas);
            Cliente clienteAtualizado = clienteDAO.buscar("TESTE001");
            assertEquals(cliente.getNome(), clienteAtualizado.getNome());
        } catch (SQLException e) {
            fail("Erro ao atualizar cliente: " + e.getMessage());
        }
    }

    @Test
    public void testBuscarCliente() {
        try {
            // Arrange
            ClienteDAO clienteDAO = new ClienteDAO();
            Cliente cliente = new Cliente();
            cliente.setCodigo("TESTE001");
            cliente.setNome("João da Silva");
            clienteDAO.cadastrar(cliente);

            // Act
            Cliente clienteEncontrado = clienteDAO.buscar("TESTE001");

            // Assert
            assertNotNull(clienteEncontrado);
            assertEquals(cliente.getCodigo(), clienteEncontrado.getCodigo());
            assertEquals(cliente.getNome(), clienteEncontrado.getNome());
        } catch (SQLException e) {
            fail("Erro ao buscar cliente: " + e.getMessage());
        }
    }

    @Test
    public void testExcluirCliente() {
        try {
            // Arrange
            ClienteDAO clienteDAO = new ClienteDAO();
            Cliente cliente = new Cliente();
            cliente.setCodigo("TESTE001");
            cliente.setNome("João da Silva");
            clienteDAO.cadastrar(cliente);

            // Act
            Integer linhasAfetadas = clienteDAO.excluir(cliente);

            // Assert
            assertEquals(1, linhasAfetadas);
            Cliente clienteExcluido = clienteDAO.buscar("TESTE001");
            assertNull(clienteExcluido);
        } catch (SQLException e) {
            fail("Erro ao excluir cliente: " + e.getMessage());
        }
    }

    @Test
    public void testBuscarTodosClientes() {
        try {
            // Arrange
            ClienteDAO clienteDAO = new ClienteDAO();
            Cliente cliente1 = new Cliente();
            cliente1.setCodigo("TESTE001");
            cliente1.setNome("João da Silva");
            clienteDAO.cadastrar(cliente1);

            Cliente cliente2 = new Cliente();
            cliente2.setCodigo("TESTE002");
            cliente2.setNome("Maria Souza");
            clienteDAO.cadastrar(cliente2);

            // Act
            List<Cliente> clientes = clienteDAO.buscarTodos();

            // Assert
            assertEquals(2, clientes.size());
            assertTrue(clientes.contains(cliente1));
            assertTrue(clientes.contains(cliente2));
        } catch (SQLException e) {
            fail("Erro ao buscar todos os clientes: " + e.getMessage());
        }
    }
}