package chess.dao;

import chess.domain.piece.PieceColor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChessTurnDao {
    private static final String insertQuery = "INSERT INTO chess_turn (turn) VALUES (?)";
    private static final String updateQuery = "UPDATE chess_turn SET turn=? WHERE game_id=?";
    private static final String selectTurnQuery = "SELECT turn FROM chess_turn WHERE game_id=?";
    private static final String selectMaxGameIdQuery = "SELECT max(game_id) FROM chess_turn";
    private static final String selectGameIdsQuery = "SELECT game_id FROM chess_turn";
    private static final String deleteQuery = "DELETE FROM chess_turn WHERE game_id=?";

    private static ChessTurnDao instance;

    private JdbcTemplate jdbcTemplate;

    private ChessTurnDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static ChessTurnDao getInstance(JdbcTemplate jdbcTemplate) {
        if (instance == null) {
            instance = new ChessTurnDao(jdbcTemplate);
        }

        if (!instance.jdbcTemplate.equals(jdbcTemplate)) {
            instance.jdbcTemplate = jdbcTemplate;
        }
        return instance;
    }

    public PieceColor selectChessTurn(int id) throws SQLException {
        List<Object> parameters = new ArrayList();
        parameters.add(id);

        ResultSet rs = jdbcTemplate.executeQuery(selectTurnQuery, parameters);

        if (rs.next()) {
            return PieceColor.valueOf(rs.getString("turn"));
        }
        throw new SQLException();
    }

    public void updateChessTurn(int id, PieceColor turn) throws SQLException {
        List<Object> parameters = new ArrayList();
        parameters.add(String.valueOf(turn));
        parameters.add(id);

        jdbcTemplate.executeUpdate(updateQuery, parameters);
    }

    public void insertChessTurn(PieceColor turn) throws SQLException {
        List<Object> parameters = new ArrayList();
        parameters.add(String.valueOf(turn));

        jdbcTemplate.executeUpdate(insertQuery, parameters);
    }

    public int selectMaxGameId() throws SQLException {
        ResultSet rs = jdbcTemplate.executeQuery(selectMaxGameIdQuery, new ArrayList());

        if (rs.next()) {
            return rs.getInt("max(game_id)");
        }
        throw new SQLException();
    }

    public void deleteChessTurn(int id) throws SQLException {
        List<Object> parameters = new ArrayList();
        parameters.add(id);

        jdbcTemplate.executeUpdate(deleteQuery, parameters);
    }

    public List<Integer> selectChessGames() throws SQLException {
        ResultSet rs = jdbcTemplate.executeQuery(selectGameIdsQuery, new ArrayList());

        List<Integer> gameIds = new ArrayList<>();
        while (rs.next()) {
            gameIds.add(rs.getInt("game_id"));
        }

        return gameIds;
    }
}
