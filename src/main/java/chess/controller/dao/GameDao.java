package chess.controller.dao;

import chess.controller.dto.ResponseDto;
import chess.domain.game.GameStatus;
import chess.domain.game.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameDao {
    private ConnectionDao connectionDao = new ConnectionDao();

    public void saveInitGame(ResponseDto responseDto) throws SQLException {
        String query = "INSERT INTO game(turn, white_score, black_score, state) VALUES (?,?,?,?)";

        try (Connection connection = connectionDao.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, responseDto.getTurn().name());
            pstmt.setDouble(2, responseDto.getResult().getStatuses().get(0).getScore());
            pstmt.setDouble(3, responseDto.getResult().getStatuses().get(1).getScore());
            pstmt.setString(4, responseDto.getGameStatus().toString());
            pstmt.executeUpdate();
        }
    }

    public void updateGame(ResponseDto responseDto) throws SQLException {
        String query = "UPDATE game " +
                "SET turn = ?, white_score = ?, black_score = ?" +
                "WHERE game_id = ?";

        try (Connection connection = connectionDao.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, responseDto.getTurn().name());
            pstmt.setDouble(2, responseDto.getResult().getStatuses().get(0).getScore());
            pstmt.setDouble(3, responseDto.getResult().getStatuses().get(1).getScore());
            pstmt.setInt(4, responseDto.getRoomNumber());
            pstmt.executeUpdate();
        }
    }

    public void updateEndState(int roomNumber) throws SQLException {
        String query = "UPDATE game SET state = ?" +
                "WHERE game_id = ?";

        try (Connection connection = connectionDao.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, GameStatus.FINISH.toString());
            pstmt.setInt(2, roomNumber);
            pstmt.executeUpdate();
        }
    }

    public GameStatus findState(int roomNumber) throws SQLException {
        String query = "SELECT state FROM game WHERE game_id=?";

        try (Connection connection = connectionDao.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, roomNumber);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()) return null;
            return GameStatus.valueOf(rs.getString("state"));
        }
    }

    public Player findTurn(int roomNumber) throws SQLException {
        String query = "SELECT turn FROM game WHERE game_id = ?";

        try (Connection connection = connectionDao.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, roomNumber);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()) return null;
            return Player.valueOf(rs.getString("turn"));
        }
    }

    public int findMaxRoomNumber() throws SQLException {
        String query = "SELECT MAX(game_id) FROM game";
        try (Connection connection = connectionDao.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()) return 0;
            return rs.getInt("MAX(game_id)");
        }
    }
}