package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {


    private static final String URL = "jdbc:postgresql://localhost:15432/vendas_online_2";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Postgres2019!";
    private static Connection connection;


    private ConnectionFactory() {
    }


    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = initConnection();
        }
        return connection;
    }

    private static Connection initConnection() {
        try {

            Class.forName("org.postgresql.Driver");

            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {

            throw new RuntimeException("Erro ao conectar ao banco de dados", e);
        }
    }
}
