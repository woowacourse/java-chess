package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MoveDaoImpl implements MoveDao {
    public void saveAll(final QueryStrategy saveStrategy) {
        final String insertQuery = "INSERT INTO move (source, target) VALUES (?, ?)";
        connectDataBaseAndDoQuery(insertQuery, saveStrategy);
    }

    private void connectDataBaseAndDoQuery(final String query, final QueryStrategy strategy) {
        try (
                final Connection connection = ConnectionGenerator.getConnection();
                final PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            connection.setAutoCommit(false);

            strategy.query(preparedStatement);

            connection.commit();
        } catch (final SQLException e) {
            throw new IllegalStateException("데이터 베이스에 쿼리를 보낼 수 없습니다.");
        }
    }
}
