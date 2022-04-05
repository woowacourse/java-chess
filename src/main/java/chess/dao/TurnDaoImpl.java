package chess.dao;

import chess.domain.turn.Turn;
import chess.util.DBConnection;
import chess.util.JdbcTemplate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Optional;

public class TurnDaoImpl implements TurnDao {

    private final JdbcTemplate jdbcTemplate;

    public TurnDaoImpl(Connection connection) {
        this.jdbcTemplate = new JdbcTemplate(connection);
    }

    public TurnDaoImpl() {
        this(DBConnection.getConnection());
    }

    public Optional<Turn> findCurrentTurn() {
        final String query = "select turn from chess_game";

        return jdbcTemplate.executeSelect(connection -> connection.prepareStatement(query),
                resultSet -> {
                    if (resultSet.next()) {
                        String turn = resultSet.getString("turn");
                        return Optional.of(Turn.valueOf(turn));
                    }
                    return Optional.empty();
                });
    }

    public void updateTurn(Turn currentTurn, Turn turn) {
        final String query = "update chess_game set turn = ? where turn = ?";

        jdbcTemplate.executeUpdate(connection -> {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, turn.name());
            pstmt.setString(2, currentTurn.name());
            return pstmt;
        });
    }
}
