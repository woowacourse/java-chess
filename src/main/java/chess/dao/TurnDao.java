package chess.dao;

import chess.domain.state.Turn;
import chess.util.DBConnection;
import chess.util.JdbcTemplate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class TurnDao {

    private final JdbcTemplate jdbcTemplate;

    public TurnDao(Connection connection) {
        this.jdbcTemplate = new JdbcTemplate(connection);
    }

    public TurnDao() {
        this(DBConnection.getConnection());
    }

    public Optional<Turn> findCurrentTurn() {
        final String query = "select turn from chess_game";

        return jdbcTemplate.executeSelect(connection -> connection.prepareStatement(query), this::turnMapper);
    }

    private Optional<Turn> turnMapper(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            String turn = resultSet.getString("turn");
            return Optional.of(Turn.valueOf(turn));
        }
        return Optional.empty();
    }

    public int updateTurn(Turn currentTurn, Turn turn) {
        final String query = "update chess_game set turn = ? where turn = ?";

        return jdbcTemplate.executeUpdate(connection -> {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, turn.name());
            pstmt.setString(2, currentTurn.name());
            return pstmt;
        });
    }
}
