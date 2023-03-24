package chess.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MoveSaveStrategy implements QueryStrategy {
    private final Move move;

    public MoveSaveStrategy(final Move move) {
        this.move = move;
    }

    @Override
    public void save(final PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, move.getSource());
        preparedStatement.setString(2, move.getTarget());
        preparedStatement.executeUpdate();
    }

    @Override
    public <T> List<T> findAll(final ResultSet resultSet, final RowMapper<T> rowMapper) throws SQLException {
        throw new UnsupportedOperationException("지원하지 않는 기능입니다.");
    }
}
