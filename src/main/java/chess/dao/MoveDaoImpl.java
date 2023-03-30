package chess.dao;

import chess.model.position.Position;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MoveDaoImpl implements MoveDao {

    @Override
    public void save(final Position source, final Position target) {
        final String insertQuery = "INSERT INTO move (source, target) VALUES (?, ?)";
        connectDataBaseAndDoQuery(insertQuery, source, target);
    }

    private void connectDataBaseAndDoQuery(final String query, final Position source, final Position target) {
        try (
                final Connection connection = ConnectionGenerator.getConnection();
                final PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, source.getPosition());
            preparedStatement.setString(2, target.getPosition());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new IllegalStateException("데이터 베이스에 쿼리를 보낼 수 없습니다.");
        }
    }

    @Override
    public List<Move> findAll() {
        final String findAllQuery = "SELECT source, target FROM move";
        return connectDataBaseAndFind(findAllQuery, new MoveMapper());
    }

    private List<Move> connectDataBaseAndFind(
            final String query,
            final RowMapper<Move> rowMapper
    ) {
        try (
                final Connection connection = ConnectionGenerator.getConnection();
                final PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            final ResultSet resultSet = preparedStatement.executeQuery();

            final List<Move> results = new ArrayList<>();
            while (resultSet.next()) {
                final Move move = rowMapper.create(resultSet);
                results.add(move);
            }

            return results;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean hasGame() {
        final String existsQuery = "SELECT EXISTS(SELECT * FROM Move)";
        try (
                final Connection connection = ConnectionGenerator.getConnection();
                final PreparedStatement preparedStatement = connection.prepareStatement(existsQuery);
        ) {
            final ResultSet resultSet = preparedStatement.executeQuery();

            boolean hasGame = false;
            while (resultSet.next()) {
                hasGame = resultSet.getBoolean(1);
            }

            return hasGame;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void truncateMove() {
        final String truncateQuery = "TRUNCATE TABLE Move";

        try (
                final Connection connection = ConnectionGenerator.getConnection();
                final PreparedStatement preparedStatement = connection.prepareStatement(truncateQuery);
        ) {
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
