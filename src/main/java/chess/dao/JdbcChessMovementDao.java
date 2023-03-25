package chess.dao;

import chess.model.position.Position;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JdbcChessMovementDao implements ChessMovementDao {

    private static final String SOURCE_COLUMN_NAME = "source";
    private static final String TARGET_COLUMN_NAME = "target";

    private final Connection connection;

    public JdbcChessMovementDao(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Movement> findAll() {
        final String sql = "SELECT * FROM movement";

        try (final PreparedStatement preparedStatement = connection.prepareStatement(sql);
                final ResultSet resultSet = preparedStatement.executeQuery()) {
            return mapToMovementFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new IllegalStateException("DB 접속에 실패했습니다.", e);
        }
    }

    private List<Movement> mapToMovementFromResultSet(final ResultSet resultSet) throws SQLException {
        final List<Movement> movements = new ArrayList<>();

        while (resultSet.next()) {
            final String source = resultSet.getString(SOURCE_COLUMN_NAME);
            final String target = resultSet.getString(TARGET_COLUMN_NAME);

            movements.add(new Movement(source, target));
        }
        return movements;
    }

    @Override
    public void save(final Position source, final Position target) {
        final String sql = "INSERT INTO movement(source, target) VALUES(?, ?)";

        executeUpdate(sql, List.of(source.getPosition(), target.getPosition()));
    }

    @Override
    public void delete() {
        final String sql = "DELETE FROM movement";

        executeUpdate(sql, Collections.emptyList());
    }

    private void executeUpdate(final String sql, final List<String> parameters) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            setupPreparedStatement(parameters, preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("DB 관련 문제가 발생했습니다.", e);
        }
    }

    private void setupPreparedStatement(final List<String> parameters, final PreparedStatement preparedStatement)
            throws SQLException {
        for (int index = 0; index < parameters.size(); index++) {
            preparedStatement.setString(index + 1, parameters.get(index));
        }
    }
}
