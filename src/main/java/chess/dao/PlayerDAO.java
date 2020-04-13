package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO {
    public void addPlayer(ChessBoard chessBoard, Player player) {
        String query = "INSERT INTO player (whitePlayer, blackPlayer, chessBoardId) VALUES(?, ?, ?)";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, player.getWhitePlayer());
            pstmt.setString(2, player.getBlackPlayer());
            pstmt.setInt(3, chessBoard.getChessBoardId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Player findPlayer(ChessBoard chessBoard) {
        String query = "SELECT whitePlayer, blackPlayer FROM player WHERE chessBoardId=?";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, chessBoard.getChessBoardId());
            ResultSet rs = pstmt.executeQuery();

            while (!rs.next()) {
                return null;
            }

            Player player = new Player(
                    rs.getString("whitePlayer"),
                    rs.getString("blackPlayer")
            );
            ConnectionManager.closeResultSet(rs);
            return player;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deletePlayer(ChessBoard chessBoard) {
        String query = "DELETE FROM player WHERE chessBoardId=?";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, chessBoard.getChessBoardId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Player> findAllPlayer() {
        String query = "SELECT whitePlayer, blackPlayer, chessBoardId FROM player";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();
            List<Player> players = new ArrayList<>();

            while (rs.next()) {
                Player player = new Player(
                        rs.getString("whitePlayer"),
                        rs.getString("blackPlayer")
                );
                player.setChessBoardId(rs.getInt("chessBoardId"));
                players.add(player);
            }
            ConnectionManager.closeResultSet(rs);
            return players;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
