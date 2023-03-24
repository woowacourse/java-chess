package chess.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MoveSaveStrategy implements MoveQueryStrategy {

    private final List<Move> moves;

    public MoveSaveStrategy(final List<Move> moves) {
        this.moves = moves;
    }

    @Override
    public void save(final PreparedStatement preparedStatement) throws SQLException {
        for (Move move : moves) {
            preparedStatement.setString(1, move.getSource());
            preparedStatement.setString(2, move.getTarget());
            preparedStatement.addBatch();
            preparedStatement.clearParameters();
        }

        preparedStatement.executeBatch();
        preparedStatement.clearBatch();
    }

    @Override
    public List<Move> findAll(final PreparedStatement preparedStatement) throws SQLException {
        throw new UnsupportedOperationException("지원하지 않는 기능입니다.");
    }
}
