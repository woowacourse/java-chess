package chess.dao;

import chess.domain.board.Team;
import chess.dto.web.UsersInRoomDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    private final ChessDataSource chessDataSource;

    public UserDao(ChessDataSource chessDataSource) {
        this.chessDataSource = chessDataSource;
    }

    public void insert(String userName) {
        String query = "INSERT INTO users (name) "
            + "SELECT * FROM (SELECT ?) AS tmp "
            + "WHERE NOT EXISTS (SELECT * FROM users WHERE name = ?);";

        try (Connection connection = chessDataSource.connection();
            PreparedStatement pstmt = connection.prepareStatement(query);) {
            pstmt.setString(1, userName);
            pstmt.setString(2, userName);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("유저 정보를 db에 생성하는 중에 문제가 발생했습니다.", e);
        }
    }

    public UsersInRoomDto usersInRoom(String roomId) {
        String query = "SELECT"
            + " white_user.name AS whiteName,"
            + " white_user.win AS whiteWin,"
            + " white_user.lose AS whiteLose,"
            + " black_user.name AS blackName,"
            + " black_user.win AS blackWin,"
            + " black_user.lose AS blackLose "
            + "FROM"
            + " room AS r"
            + " INNER JOIN users AS black_user ON r.black = black_user.name"
            + " INNER JOIN users AS white_user ON r.white = white_user.name "
            + "WHERE r.id = ?;";

        try (Connection connection = chessDataSource.connection();
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
        } catch (SQLException e) {
            throw new IllegalStateException("유저 정보를 db에 불러오는 중에 문제가 발생했습니다.", e);
        }
    }

    public void updateStatistics(String roomId, Team winnerTeam) {
        String winner = "white";
        String loser = "black";
        if (winnerTeam.isBlack()) {
            winner = "black";
            loser = "white";
        }
        updateWinner(roomId, winner);
        updateLoser(roomId, loser);
    }

    private void updateWinner(String roomId, String winner) {
        String updateWinnerQueryForm = "UPDATE users "
            + "SET users.win = users.win + 1 "
            + "WHERE users.name = (SELECT %s FROM room WHERE id = ?);";

        try (Connection connection = chessDataSource.connection();
            PreparedStatement pstmt = connection
                .prepareStatement(String.format(updateWinnerQueryForm, winner));) {
            pstmt.setString(1, roomId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("유저 기록을 db에 갱신하는 중에 문제가 발생했습니다.", e);
        }
    }

    private void updateLoser(String roomId, String loser) {
        String updateLoserQuery = "UPDATE users "
            + "SET users.lose = users.lose + 1 "
            + "WHERE users.name = (SELECT %s FROM room WHERE id = ?);";

        try (Connection connection = chessDataSource.connection();
            PreparedStatement pstmt = connection
                .prepareStatement(String.format(updateLoserQuery, loser));) {
            pstmt.setString(1, roomId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("유저 기록을 db에 갱신하는 중에 문제가 발생했습니다.", e);
        }
    }
}
