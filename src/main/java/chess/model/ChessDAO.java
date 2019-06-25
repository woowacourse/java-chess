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

    public void updateBoard(BoardDTO boardDTO) throws SQLException {
        String query = "INSERT INTO chess_info (row1, row2, row3, row4, row5, row6, row7, row8) VALUES(?,?,?,?,?,?,?,?)";
        try (Connection connection = getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, boardDTO.getPieces().get(7));
            pstmt.setString(2, boardDTO.getPieces().get(6));
            pstmt.setString(3, boardDTO.getPieces().get(5));
            pstmt.setString(4, boardDTO.getPieces().get(4));
            pstmt.setString(5, boardDTO.getPieces().get(3));
            pstmt.setString(6, boardDTO.getPieces().get(2));
            pstmt.setString(7, boardDTO.getPieces().get(1));
            pstmt.setString(8, boardDTO.getPieces().get(0));

            pstmt.executeUpdate();
        }
    }

    public void deleteAll() throws SQLException {
        String query = "DELETE FROM chess_info";
        try (Connection connection = getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.executeUpdate();
        }
    }

    public void insertInit() throws SQLException {
        initAutoIncrement();
        String query = "INSERT INTO chess_info VALUES(0," +
                "'rnbqkbnr','pppppppp','########','########','########','########','PPPPPPPP','RNBQKBNR');";

        try (Connection connection = getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.executeUpdate();
        }
    }

    private void initAutoIncrement() throws SQLException {
        String query = "ALTER TABLE chess_info AUTO_INCREMENT = 1";

        try (Connection connection = getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.executeUpdate();
        }
    }

    public int getLatestTurn() throws SQLException {
        String query = "SELECT MAX(turn) AS latest_turn FROM chess_info";
        int latestTurn = 0;
        try (Connection connection = getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                latestTurn = rs.getInt("latest_turn");
            }
        }

        return latestTurn;
    }
}
