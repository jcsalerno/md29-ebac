package dao;

import domain.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProdutoDAO implements IProdutoDAO {

    private static final Logger LOGGER = Logger.getLogger(ProdutoDAO.class.getName());

    @Override
    public Integer cadastrar(Produto produto) throws SQLException {
        String sql = getSqlInsert();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {
            adicionarParametrosInsert(stm, produto);
            return stm.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao cadastrar produto", e);
            throw e;
        }
    }

    @Override
    public Integer atualizar(Produto produto) throws SQLException {
        String sql = getSqlUpdate();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {
            adicionarParametrosUpdate(stm, produto);
            return stm.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao atualizar produto", e);
            throw e;
        }
    }

    @Override
    public Produto buscar(String codigo) throws SQLException {
        String sql = getSqlSelect();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {
            adicionarParametrosSelect(stm, codigo);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToProduto(rs);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao buscar produto", e);
            throw e;
        }
        return null;
    }

    @Override
    public Integer excluir(Produto produto) throws SQLException {
        String sql = getSqlDelete();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {
            adicionarParametrosDelete(stm, produto);
            return stm.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao excluir produto", e);
            throw e;
        }
    }

    @Override
    public List<Produto> buscarTodos() throws SQLException {
        List<Produto> list = new ArrayList<>();
        String sql = getSqlSelectAll();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                list.add(mapResultSetToProduto(rs));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao buscar todos os produtos", e);
            throw e;
        }
        return list;
    }

    private Produto mapResultSetToProduto(ResultSet rs) throws SQLException {
        Produto produto = new Produto();
        produto.setId(rs.getLong("ID"));
        produto.setNome(rs.getString("NOME"));
        produto.setCodigo(rs.getString("CODIGO"));
        produto.setPreco(rs.getBigDecimal("PRECO"));
        return produto;
    }

    private String getSqlInsert() {
        return "INSERT INTO TB_PRODUTO (ID, CODIGO, NOME, PRECO) VALUES (nextval('SQ_PRODUTO'), ?, ?, ?)";
    }

    private void adicionarParametrosInsert(PreparedStatement stm, Produto produto) throws SQLException {
        stm.setString(1, produto.getCodigo());
        stm.setString(2, produto.getNome());
        stm.setBigDecimal(3, produto.getPreco());
    }

    private String getSqlUpdate() {
        return "UPDATE TB_PRODUTO SET NOME = ?, CODIGO = ?, PRECO = ? WHERE ID = ?";
    }

    private void adicionarParametrosUpdate(PreparedStatement stm, Produto produto) throws SQLException {
        stm.setString(1, produto.getNome());
        stm.setString(2, produto.getCodigo());
        stm.setBigDecimal(3, produto.getPreco());
        stm.setLong(4, produto.getId());
    }

    private String getSqlDelete() {
        return "DELETE FROM TB_PRODUTO WHERE CODIGO = ?";
    }

    private void adicionarParametrosDelete(PreparedStatement stm, Produto produto) throws SQLException {
        stm.setString(1, produto.getCodigo());
    }

    private String getSqlSelect() {
        return "SELECT * FROM TB_PRODUTO WHERE CODIGO = ?";
    }

    private void adicionarParametrosSelect(PreparedStatement stm, String codigo) throws SQLException {
        stm.setString(1, codigo);
    }

    private String getSqlSelectAll() {
        return "SELECT * FROM TB_PRODUTO";
    }
}


