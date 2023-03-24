package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MoveDaoImpl implements MoveDao {
    public void saveAll(final MoveQueryStrategy saveStrategy) {
        final String insertQuery = "INSERT INTO move (source, target) VALUES (?, ?)";
        connectDataBaseAndDoQuery(insertQuery, saveStrategy);
    }

    private void connectDataBaseAndDoQuery(final String query, final MoveQueryStrategy strategy) {
        try (
                final Connection connection = ConnectionGenerator.getConnection();
                final PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            connection.setAutoCommit(false);

            strategy.save(preparedStatement);

            connection.commit();
        } catch (final SQLException e) {
            throw new IllegalStateException("데이터 베이스에 쿼리를 보낼 수 없습니다.");
        }
    }

    @Override
    public List<Move> findAll(final MoveFindAllStrategy findAllStrategy) {
        final String findAllQuery = "SELECT source, target FROM move";
        return connectDataBaseAndFind(findAllQuery, findAllStrategy);
    }

    private List<Move> connectDataBaseAndFind(final String query, final MoveQueryStrategy strategy) {
        try (
                final Connection connection = ConnectionGenerator.getConnection();
                final PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            return strategy.findAll(preparedStatement);
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
