package chess.model.repository.template;

import static chess.model.repository.connector.ChessMySqlConnector.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class JdbcTemplate {

    public void executeUpdate(String query) throws SQLException {
        try (Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            setParameterUpdate(pstmt);
        }
    }

    public abstract void setParameterUpdate(PreparedStatement pstmt)
        throws SQLException;
}
