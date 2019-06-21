package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChessGameDAO {
    private static ChessGameDAO chessGameDAO;
    private Connection con;

    private ChessGameDAO() {
        con = DataBaseConnector.getConnection();
    }

    public static ChessGameDAO getInstance() {
        if (chessGameDAO == null) {
            chessGameDAO = new ChessGameDAO();
        }
        return chessGameDAO;
    }

    public List<Integer> findPreviousGameById(String name) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Integer> previousGameId = new ArrayList<>();
        try {
            String query = "select game_id from chess_game where white_name = ? or black_name = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, name);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                previousGameId.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return previousGameId;
    }
}
