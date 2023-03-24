package chess.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MoveFindAllStrategy implements MoveQueryStrategy {

    @Override
    public void save(final PreparedStatement preparedStatement) throws SQLException {
        throw new UnsupportedOperationException("지원하지 않는 기능입니다.");
    }

    @Override
    public List<Move> findAll(final PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.execute();

        final ResultSet resultSet = preparedStatement.getResultSet();

        final List<Move> moves = new ArrayList<>();
        while (resultSet.next()) {
            final Move move = new Move(
                    resultSet.getString("source"),
                    resultSet.getString("target")
            );
            moves.add(move);
        }

        return moves;
    }
}
