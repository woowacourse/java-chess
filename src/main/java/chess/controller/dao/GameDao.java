package chess.controller.dao;

import chess.controller.dto.ResponseDto;
import chess.domain.game.Player;
import chess.domain.status.Result;
import chess.domain.status.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameDao {
    private Connection connection = new ConnectionDao().getConnection();

    public void saveInitGame(ResponseDto responseDto) throws SQLException {
        String query = "INSERT INTO game(turn, white_score, black_score, state) VALUES (?,?,?,?)";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, responseDto.getTurn().name());
        pstmt.setDouble(2, responseDto.getResult().getStatuses().get(0).getScore());
        pstmt.setDouble(3, responseDto.getResult().getStatuses().get(1).getScore());
        pstmt.setString(4, "playing");
        pstmt.executeUpdate();
        pstmt.close();
    }

    public void updateGame(ResponseDto responseDto) throws SQLException {
        String query = "UPDATE game " +
                "SET turn = ?, white_score = ?, black_score = ?" +
                "WHERE game_id = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, responseDto.getTurn().name());
        pstmt.setDouble(2, responseDto.getResult().getStatuses().get(0).getScore());
        pstmt.setDouble(3, responseDto.getResult().getStatuses().get(1).getScore());
        pstmt.setInt(4, responseDto.getRoomNumber());
        pstmt.executeUpdate();
        pstmt.close();
    }

    public void updateEndState(int game_id) throws SQLException {
        String query = "UPDATE game SET state = ?" +
                "WHERE game_id = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, "end");
        pstmt.setInt(2, game_id);
        pstmt.executeUpdate();
        pstmt.close();
    }

    public String findState(int roomNumber) throws SQLException {
        String query = "SELECT state FROM game WHERE game_id=?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, roomNumber);
        ResultSet rs = pstmt.executeQuery();
        if (!rs.next()) return null;
        return rs.getString("state");
    }

    public Result getResult(ResultSet rs) throws SQLException {
        Status whiteStatus = new Status(Player.WHITE, rs.getDouble("white_score"));
        Status blackStatus = new Status(Player.BLACK, rs.getDouble("black_score"));
        List<Status> statuses = new ArrayList<>();
        statuses.add(whiteStatus);
        statuses.add(blackStatus);
        return new Result(statuses);
    }

    public int findMaxRoomNumber() throws SQLException {
        String query = "SELECT MAX(game_id) FROM game";
        PreparedStatement pstmt = connection.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        if(!rs.next()) return 0;

        return rs.getInt("MAX(game_id)");
    }
}