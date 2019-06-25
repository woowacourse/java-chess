package chess.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChessDAO {
    private static ChessDAO CHESS_DAO = new ChessDAO();


    public static ChessDAO getInstance() {
        return CHESS_DAO;
    }

    public Connection getConnection() {
        Connection conn = null;
        String server = "localhost";
        String database = "chess_db";
        String userName = "chess";
        String password = "chess";

        loadDriver();

        try {
            conn = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false", userName, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
        }

        return conn;
    }

    private void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public BoardDTO selectByTurn(int turn) throws SQLException {
        String query = "SELECT * FROM chess_info WHERE turn = ?";
        List<String> pieces = new ArrayList<>();

        try (Connection connection = getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, turn);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                pieces = (Arrays.asList(
                        rs.getString("row8"),
                        rs.getString("row7"),
                        rs.getString("row6"),
                        rs.getString("row5"),
                        rs.getString("row4"),
                        rs.getString("row3"),
                        rs.getString("row2"),
                        rs.getString("row1")));
            }

            return new BoardDTO(pieces);
        }
    }
}
