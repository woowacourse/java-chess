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
    private Connection con;

    private ChessLogDAO() {
        con = DataBaseConnector.getConnection();
    }

    public static ChessLogDAO getInstance() {
        if (chessLogDAO == null) {
            chessLogDAO = new ChessLogDAO();
        }
        return chessLogDAO;
    }

    public List<List<String>> findGameLogById(String gameId) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<List<String>> gameLog = new ArrayList<>(new ArrayList<>());
        try {
            String query = "select source,destination from game_log where game_id = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, gameId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                gameLog.add(Arrays.asList(rs.getString(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gameLog;
    }

    public void insertLog(String from, String to, String gameId) {
        PreparedStatement pstmt = null;
        try {
            String query = "insert into game_log(source, destination,game_id) values(?,?,?);";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, from);
            pstmt.setString(2, to);
            pstmt.setString(3, gameId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
