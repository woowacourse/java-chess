package chess.dao;

import chess.domain.board.Team;
import chess.dto.web.UsersInRoomDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends ChessDao {

    public void insert(String userName) throws SQLException {
        String query = "INSERT INTO users (name) "
            + "SELECT * FROM (SELECT ?) AS tmp "
            + "WHERE NOT EXISTS (SELECT * FROM users WHERE name = ?);";

        try (Connection connection = connection();
            PreparedStatement pstmt = connection.prepareStatement(query);) {
            pstmt.setString(1, userName);
            pstmt.setString(2, userName);
            pstmt.executeUpdate();
        }
    }

    public UsersInRoomDto usersInRoom(String roomId) throws SQLException {
        String query = "SELECT\n"
            + "    white_user.name as whiteName,\n"
            + "    white_user.win as whiteWin,\n"
            + "    white_user.lose as whiteLose,\n"
            + "    black_user.name as blackName,\n"
            + "    black_user.win as blackWin,\n"
            + "    black_user.lose as blackLose\n"
            + "FROM \n"
            + "\troom AS r\n"
            + "    INNER JOIN users AS black_user ON r.black = black_user.name\n"
            + "    INNER JOIN users AS white_user ON r.white = white_user.name\n"
            + "WHERE r.id = ?;";

        try (Connection connection = connection();
            PreparedStatement pstmt = connection.prepareStatement(query);) {
            pstmt.setString(1, roomId);
            try (ResultSet rs = pstmt.executeQuery();) {
                rs.next();
                return new UsersInRoomDto(rs.getString("whiteName"),
                    rs.getString("whiteWin"),
                    rs.getString("whiteLose"),
                    rs.getString("blackName"),
                    rs.getString("blackWin"),
                    rs.getString("blackLose"));
            }
        }
    }

    public void updateStats(String roomId, Team winnerTeam) throws SQLException {
        String winner = "white";
        String loser = "black";
        if (winnerTeam.isBlack()) {
            winner = "black";
            loser = "white";
        }
        updateWinner(roomId, winner);
        updateLoser(roomId, loser);
    }

    private void updateWinner(String roomId, String winner) throws SQLException {
        String updateWinnerQueryForm = "UPDATE users\n"
            + "SET users.win = users.win + 1\n"
            + "WHERE users.name = (SELECT %s FROM room WHERE id = ?);";

        try (Connection connection = connection();
            PreparedStatement pstmt = connection
                .prepareStatement(String.format(updateWinnerQueryForm, winner));) {
            pstmt.setString(1, roomId);
            pstmt.executeUpdate();
        }
    }

    private void updateLoser(String roomId, String loser) throws SQLException {
        String updateLoserQuery = "UPDATE users\n"
            + "SET users.lose = users.lose + 1\n"
            + "WHERE users.name = (SELECT %s FROM room WHERE id = ?);";

        try (Connection connection = connection();
            PreparedStatement pstmt = connection
                .prepareStatement(String.format(updateLoserQuery, loser));) {
            pstmt.setString(1, roomId);
            pstmt.executeUpdate();
        }
    }
}
