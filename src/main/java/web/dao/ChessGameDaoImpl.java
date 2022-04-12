package web.dao;

import chess.domain.game.ChessGame;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import web.DBConnectionSetUp;
import web.dto.ChessGameResponse;

public class ChessGameDaoImpl implements ChessGameDao {

    @Override
    public Long createGame(ChessGame chessGame) throws SQLException {
        String query = "INSERT INTO game (name, turn) VALUES (?, ?)";
        Connection con = DBConnectionSetUp.getConnection();
        PreparedStatement pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, chessGame.getName());
        pstmt.setString(2, chessGame.currentTurn().name());
        pstmt.executeUpdate();
        ResultSet rs = pstmt.getGeneratedKeys();

        try (con; pstmt; rs) {
            rs.next();
            return rs.getLong(1);
        }
    }

    @Override
    public List<ChessGameResponse> findRunningGames() throws SQLException {
        String query = "SELECT * FROM game WHERE is_end = false ORDER BY created_at DESC";
        Connection con = DBConnectionSetUp.getConnection();
        PreparedStatement pstmt = con.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        try (con; pstmt; rs) {
            return getChessGameResponses(rs);
        }
    }

    private List<ChessGameResponse> getChessGameResponses(ResultSet rs) throws SQLException {
        List<ChessGameResponse> gameResponses = new ArrayList<>();
        while (rs.next()) {
            ChessGameResponse chessGameResponse = new ChessGameResponse(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("turn")
            );
            gameResponses.add(chessGameResponse);
        }
        return gameResponses;
    }

    @Override
    public ChessGameResponse findByGameId(String gameId) throws SQLException {
        String query = "SELECT * FROM game WHERE id = ? AND is_end = false ORDER BY created_at";
        Connection connection = DBConnectionSetUp.getConnection();
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setLong(1, Long.parseLong(gameId));
        ResultSet rs = pstmt.executeQuery();

        try (connection; pstmt; rs) {
            rs.next();
            return new ChessGameResponse(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("turn")
            );
        }
    }

    @Override
    public void updateTurn(Long gameId, String turn) throws SQLException {
        String query = "UPDATE game SET turn = ? WHERE id = ?";
        Connection connection = DBConnectionSetUp.getConnection();
        PreparedStatement pstmt = connection.prepareStatement(query);

        try (connection; pstmt) {
            pstmt.setString(1, turn);
            pstmt.setLong(2, gameId);
            pstmt.executeUpdate();
        }
    }

    @Override
    public String findTurn(String gameId) throws SQLException {
        String query = "SELECT * FROM game WHERE id = ?";
        Connection connection = DBConnectionSetUp.getConnection();
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setLong(1, Long.parseLong(gameId));
        ResultSet rs = pstmt.executeQuery();

        try (connection; pstmt; rs) {
            rs.next();
            return rs.getString("turn");
        }
    }

    @Override
    public void updateGameEnd(Long gameId) throws SQLException {
        String query = "UPDATE game SET is_end = ? WHERE id = ?";
        Connection connection = DBConnectionSetUp.getConnection();
        PreparedStatement pstmt = connection.prepareStatement(query);

        try (connection; pstmt){
            pstmt.setBoolean(1, true);
            pstmt.setLong(2, gameId);
            pstmt.executeUpdate();
        }
    }
}
