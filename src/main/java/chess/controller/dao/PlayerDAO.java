package chess.controller.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO {
    public void addPlayer(ChessBoard chessBoard, Player player) throws SQLException {
        Connection con = ConnectionManager.getConnection();
        String query = "INSERT INTO player (whitePlayer, blackPlayer, chessBoardId) VALUES(?, ?, ?)";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, player.getWhitePlayer());
        pstmt.setString(2, player.getBlackPlayer());
        pstmt.setInt(3, chessBoard.getChessBoardId());
        pstmt.executeUpdate();
        ConnectionManager.closeConnection(con);
    }

    public Player findPlayer(ChessBoard chessBoard) throws SQLException {
        Connection con = ConnectionManager.getConnection();
        String query = "SELECT whitePlayer, blackPlayer FROM player WHERE chessBoardId=?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setInt(1, chessBoard.getChessBoardId());
        ResultSet rs = pstmt.executeQuery();

        while (!rs.next()) {
            return null;
        }

        Player player = new Player(
                rs.getString("whitePlayer"),
                rs.getString("blackPlayer")
        );
        ConnectionManager.closeConnection(con);
        return player;
    }

    public void deletePlayer(ChessBoard chessBoard) throws SQLException {
        Connection con = ConnectionManager.getConnection();
        String query = "DELETE FROM player WHERE chessBoardId=?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setInt(1, chessBoard.getChessBoardId());
        pstmt.executeUpdate();
        ConnectionManager.closeConnection(con);
    }

    public List<Player> findAllPlayer() throws SQLException {
        Connection con = ConnectionManager.getConnection();
        String query = "SELECT whitePlayer, blackPlayer, chessBoardId FROM player";
        PreparedStatement pstmt = con.prepareStatement(query);
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

        return players;
    }
}
