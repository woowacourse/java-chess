package chess.dao;

import chess.dto.ResponseDto;
import chess.domain.game.Player;
import chess.domain.result.Result;
import chess.domain.result.Score;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChessGameDao {
    private final ConnectionDao connectionDao = new ConnectionDao();

    public void saveGame(ResponseDto responseDto) throws SQLException {
        Objects.requireNonNull(responseDto);
        Connection connection = connectionDao.getConnection();
        String query = "INSERT INTO chessgame (turn, white_score, black_score) VALUES (?,?,?)";
        PreparedStatement pstmt = connection.prepareStatement(query);

        pstmt.setString(1, responseDto.getTurn().name());
        pstmt.setDouble(2, responseDto.getResult().getWhiteScore());
        pstmt.setDouble(3, responseDto.getResult().getBlackScore());
        pstmt.executeUpdate();

        pstmt.close();
        connection.close();
    }

    public void updateGame(ResponseDto responseDto) throws SQLException {
        Objects.requireNonNull(responseDto);
        Connection connection = connectionDao.getConnection();
        String query = "UPDATE chessgame " +
                "SET turn = ?, white_score = ?, black_score = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);

        pstmt.setString(1, responseDto.getTurn().name());
        pstmt.setDouble(2, responseDto.getResult().getWhiteScore());
        pstmt.setDouble(3, responseDto.getResult().getBlackScore());
        pstmt.executeUpdate();

        pstmt.close();
        connection.close();
    }


    public boolean isGameExists() throws SQLException {
        Connection connection = connectionDao.getConnection();

        Statement stmt = connection.createStatement();
        String query = "SELECT * FROM chessgame";
        ResultSet rs = stmt.executeQuery(query);

        boolean result = rs.next();
        connection.close();
        return result;
    }

    public Player getTurn() throws SQLException {
        Connection connection = connectionDao.getConnection();

        Statement stmt = connection.createStatement();
        String query = "SELECT turn FROM chessgame";
        ResultSet rs = stmt.executeQuery(query);

        String player = null;
        if (rs.next()) {
            player = rs.getString("turn");
        }
        connection.close();
        return Player.of(player);
    }

    public void deleteChessGame() throws SQLException {
        Connection connection = new ConnectionDao().getConnection();
        Statement stmt = connection.createStatement();

        String query = "DELETE FROM chessgame";
        stmt.executeUpdate(query);

        stmt.close();
        connection.close();
    }
}