package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcTemplate {
    private static JdbcTemplate instance;

    private Connection connection;

    private JdbcTemplate(Connection connection) {
        this.connection = connection;
    }

    public static JdbcTemplate getInstance(Connection connection) {
        if (instance == null) {
            instance = new JdbcTemplate(connection);
        }
        if (!instance.connection.equals(connection)) {
            instance.connection = connection;
        }
        return instance;
    }

    public void executeUpdate(String query, List<Object> parameters) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement(query);
        setPreparedStatement(pstmt, parameters);
        pstmt.executeUpdate();
    }

    public ResultSet executeQuery(String query, List<Object> parameters) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement(query);
        setPreparedStatement(pstmt, parameters);
        return pstmt.executeQuery();
    }

    private void setPreparedStatement(PreparedStatement pstmt, List<Object> parameters) throws SQLException {
        int index = 1;
        for (Object parameter : parameters) {
            pstmt.setObject(index++, parameter);
        }
    }
}
