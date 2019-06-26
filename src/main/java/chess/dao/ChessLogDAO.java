package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChessLogDAO {
    private static ChessLogDAO chessLogDAO;

    private ChessLogDAO() {
    }

    public static ChessLogDAO getInstance() {
        if (chessLogDAO == null) {
            chessLogDAO = new ChessLogDAO();
        }
        return chessLogDAO;
    }

    public List<List<String>> findGameLogById(String gameId) {
        Connection con = DataBaseConnector.getConnection();
        List<List<String>> gameLog = new ArrayList<>(new ArrayList<>());
        try {
            String query = "select source,destination from game_log where game_id = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, gameId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                gameLog.add(Arrays.asList(rs.getString(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gameLog;
    }

    public void insertLog(String from, String to, String gameId) {
        Connection con = DataBaseConnector.getConnection();
        try {
            String query = "insert into game_log(source, destination,game_id) values(?,?,?);";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, from);
            pstmt.setString(2, to);
            pstmt.setString(3, gameId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
