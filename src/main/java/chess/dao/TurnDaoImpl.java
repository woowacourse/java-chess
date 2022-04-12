package chess.dao;

import chess.domain.state.Turn;
import chess.util.JdbcContext;
import java.sql.PreparedStatement;

public class TurnDaoImpl implements TurnDao{

    private final JdbcContext jdbcContext;

    public TurnDaoImpl(final JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public Turn findCurrentTurn() {
        String sql = "select current_turn from game";

        return jdbcContext.queryForObject(
                connection -> connection.prepareStatement(sql),
                resultSet -> {
                    if (resultSet.next()) {
                        String currentTurn = resultSet.getString("current_turn");
                        return Turn.valueOf(currentTurn);
                    }
                    throw new IllegalStateException("턴이 존재하지 않습니다");
                });
    }

    public void updateTurn(Turn currentTurn, Turn turn) {
        String sql = "update game set current_turn = ? where current_turn = ?";
        jdbcContext.executeUpdate(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, turn.name());
            preparedStatement.setString(2, currentTurn.name());
            return preparedStatement;
        });
    }
}
