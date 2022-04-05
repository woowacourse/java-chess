package chess.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcTemplate {

    private final Connection connection;

    public JdbcTemplate(Connection connection) {
        this.connection = connection;
    }

    public void execute(PreparedStatementCreator creator) {
        try (PreparedStatement pstmt = creator.excute(connection)) {

            pstmt.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    public <T> T executeSelect(PreparedStatementCreator creator, ResultSetMapper<T> mapper) {
        try (PreparedStatement pstmt = creator.excute(connection)) {

            return mapper.map(pstmt.executeQuery());
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }
}
