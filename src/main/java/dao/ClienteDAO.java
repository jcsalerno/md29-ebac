package dao;

import domain.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteDAO implements IClienteDAO {

    private static final Logger LOGGER = Logger.getLogger(ClienteDAO.class.getName());

    @Override
    public Integer cadastrar(Cliente cliente) throws SQLException {
        String sql = getSqlInsert();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {
            adicionarParametrosInsert(stm, cliente);
            return stm.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao cadastrar cliente", e);
            throw e;
        }
    }

    @Override
    public Integer atualizar(Cliente cliente) throws SQLException {
        String sql = getSqlUpdate();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {
            adicionarParametrosUpdate(stm, cliente);
            return stm.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao atualizar cliente", e);
            throw e;
        }
    }

    @Override
    public Cliente buscar(String codigo) throws SQLException {
        String sql = getSqlSelect();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {
            adicionarParametrosSelect(stm, codigo);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToCliente(rs);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao buscar cliente", e);
            throw e;
        }
        return null;
    }

    @Override
    public Integer excluir(Cliente cliente) throws SQLException {
        String sql = getSqlDelete();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {
            adicionarParametrosDelete(stm, cliente);
            return stm.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao excluir cliente", e);
            throw e;
        }
    }

    @Override
    public List<Cliente> buscarTodos() throws SQLException {
        List<Cliente> list = new ArrayList<>();
        String sql = getSqlSelectAll();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                list.add(mapResultSetToCliente(rs));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao buscar todos os clientes", e);
            throw e;
        }
        return list;
    }

    private Cliente mapResultSetToCliente(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(rs.getLong("ID"));
        cliente.setNome(rs.getString("NOME"));
        cliente.setCodigo(rs.getString("CODIGO"));
        return cliente;
    }

    private String getSqlInsert() {
        return "INSERT INTO TB_CLIENTE_2 (ID, CODIGO, NOME) VALUES (nextval('SQ_CLIENTE_2'), ?, ?)";
    }

    private void adicionarParametrosInsert(PreparedStatement stm, Cliente cliente) throws SQLException {
        stm.setString(1, cliente.getCodigo());
        stm.setString(2, cliente.getNome());
    }

    private String getSqlUpdate() {
        return "UPDATE TB_CLIENTE_2 SET NOME = ?, CODIGO = ? WHERE ID = ?";
    }

    private void adicionarParametrosUpdate(PreparedStatement stm, Cliente cliente) throws SQLException {
        stm.setString(1, cliente.getNome());
        stm.setString(2, cliente.getCodigo());
        stm.setLong(3, cliente.getId());
    }

    private String getSqlDelete() {
        return "DELETE FROM TB_CLIENTE_2 WHERE CODIGO = ?";
    }

    private void adicionarParametrosDelete(PreparedStatement stm, Cliente cliente) throws SQLException {
        stm.setString(1, cliente.getCodigo());
    }

    private String getSqlSelect() {
        return "SELECT * FROM TB_CLIENTE_2 WHERE CODIGO = ?";
    }

    private void adicionarParametrosSelect(PreparedStatement stm, String codigo) throws SQLException {
        stm.setString(1, codigo);
    }

    private String getSqlSelectAll() {
        return "SELECT * FROM TB_CLIENTE_2";
    }
}
