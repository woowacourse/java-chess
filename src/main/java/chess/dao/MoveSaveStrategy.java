package chess.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MoveSaveStrategy implements QueryStrategy {

    private final List<MoveDto> moves;

    public MoveSaveStrategy(final List<MoveDto> moves) {
        this.moves = moves;
    }

    @Override
    public void query(final PreparedStatement preparedStatement) throws SQLException {
        for (MoveDto move : moves) {
            preparedStatement.setString(1, move.getSource());
            preparedStatement.setString(2, move.getTarget());
            preparedStatement.addBatch();
            preparedStatement.clearParameters();
        }

        preparedStatement.executeBatch();
        preparedStatement.clearBatch();
    }
}
