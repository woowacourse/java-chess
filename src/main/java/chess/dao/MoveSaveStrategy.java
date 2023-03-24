package chess.dao;

import chess.model.position.Position;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MoveSaveStrategy implements QueryStrategy {
    private final Move move;

    private MoveSaveStrategy(final Move move) {
        this.move = move;
    }

    public MoveSaveStrategy(final Position source, final Position target) {
        this(new Move(source, target));
    }

    @Override
    public void save(final PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, move.getSource());
        preparedStatement.setString(2, move.getTarget());
        preparedStatement.executeUpdate();
    }

    @Override
    public <T> List<T> findAll(final ResultSet resultSet, final RowMapper<T> rowMapper) {
        throw new UnsupportedOperationException("지원하지 않는 기능입니다.");
    }
}
