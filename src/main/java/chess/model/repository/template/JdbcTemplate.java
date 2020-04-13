package chess.model.repository.template;

import static chess.model.repository.connector.ChessMySqlConnector.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class JdbcTemplate {

    public void executeUpdate(String query) throws SQLException {
        try (Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            setParameterUpdate(pstmt);
        }
    }

    public int executeUpdateWithGeneratedKey(String query) throws SQLException {
        try (Connection conn = getConnection();
            PreparedStatement pstmt =
                conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            setParameterUpdate(pstmt);
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
                throw new SQLException();
            }
        }
    }

    public abstract void setParameterUpdate(PreparedStatement pstmt)
        throws SQLException;
}
