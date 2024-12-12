package dao;

import domain.Produto;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProdutoDAOTest {

    @Test
    public void testCadastrarProduto() {
        try {
            // Arrange
            ProdutoDAO produtoDAO = new ProdutoDAO();
            Produto produto = new Produto();
            produto.setCodigo("TESTE001");
            produto.setNome("Produto Teste");
            produto.setPreco(new BigDecimal("10.00"));

            // Act
            Integer linhasAfetadas = produtoDAO.cadastrar(produto);

            // Assert
            assertEquals(1, linhasAfetadas);
            Produto produtoCadastrado = produtoDAO.buscar("TESTE001");
            assertEquals(produto.getCodigo(), produtoCadastrado.getCodigo());
            assertEquals(produto.getNome(), produtoCadastrado.getNome());
            assertEquals(produto.getPreco(), produtoCadastrado.getPreco());
        } catch (SQLException e) {
            fail("Erro ao cadastrar produto: " + e.getMessage());
        }
    }

    @Test
    public void testAtualizarProduto() {
        try {
            // Arrange
            ProdutoDAO produtoDAO = new ProdutoDAO();
            Produto produto = new Produto();
            produto.setCodigo("TESTE001");
            produto.setNome("Produto Teste");
            produto.setPreco(new BigDecimal("10.00"));
            produtoDAO.cadastrar(produto);

            produto.setNome("Produto Teste Atualizado");
            produto.setPreco(new BigDecimal("15.00"));

            // Act
            Integer linhasAfetadas = produtoDAO.atualizar(produto);

            // Assert
            assertEquals(1, linhasAfetadas);
            Produto produtoAtualizado = produtoDAO.buscar("TESTE001");
            assertEquals(produto.getNome(), produtoAtualizado.getNome());
            assertEquals(produto.getPreco(), produtoAtualizado.getPreco());
        } catch (SQLException e) {
            fail("Erro ao atualizar produto: " + e.getMessage());
        }
    }

    @Test
    public void testBuscarProduto() {
        try {
            // Arrange
            ProdutoDAO produtoDAO = new ProdutoDAO();
            Produto produto = new Produto();
            produto.setCodigo("TESTE001");
            produto.setNome("Produto Teste");
            produto.setPreco(new BigDecimal("10.00"));
            produtoDAO.cadastrar(produto);

            // Act
            Produto produtoEncontrado = produtoDAO.buscar("TESTE001");

            // Assert
            assertNotNull(produtoEncontrado);
            assertEquals(produto.getCodigo(), produtoEncontrado.getCodigo());
            assertEquals(produto.getNome(), produtoEncontrado.getNome());
            assertEquals(produto.getPreco(), produtoEncontrado.getPreco());
        } catch (SQLException e) {
            fail("Erro ao buscar produto: " + e.getMessage());
        }
    }

    @Test
    public void testExcluirProduto() {
        try {
            // Arrange
            ProdutoDAO produtoDAO = new ProdutoDAO();
            Produto produto = new Produto();
            produto.setCodigo("TESTE001");
            produto.setNome("Produto Teste");
            produto.setPreco(new BigDecimal("10.00"));
            produtoDAO.cadastrar(produto);

            // Act
            Integer linhasAfetadas = produtoDAO.excluir(produto);

            // Assert
            assertEquals(1, linhasAfetadas);
            Produto produtoExcluido = produtoDAO.buscar("TESTE001");
            assertNull(produtoExcluido);
        } catch (SQLException e) {
            fail("Erro ao excluir produto: " + e.getMessage());
        }
    }

    @Test
    public void testBuscarTodosProdutos() {
        try {
            // Arrange
            ProdutoDAO produtoDAO = new ProdutoDAO();
            Produto produto1 = new Produto();
            produto1.setCodigo("TESTE001");
            produto1.setNome("Produto 1");
            produto1.setPreco(new BigDecimal("10.00"));
            produtoDAO.cadastrar(produto1);

            Produto produto2 = new Produto();
            produto2.setCodigo("TESTE002");
            produto2.setNome("Produto 2");
            produto2.setPreco(new BigDecimal("20.00"));
            produtoDAO.cadastrar(produto2);

            // Act
            List<Produto> produtos = produtoDAO.buscarTodos();

            // Assert
            assertEquals(2, produtos.size());
            assertTrue(produtos.contains(produto1));
            assertTrue(produtos.contains(produto2));
        } catch (SQLException e) {
            fail("Erro ao buscar todos os produtos: " + e.getMessage());
        }
    }
}