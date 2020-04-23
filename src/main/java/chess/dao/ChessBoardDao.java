package chess.dao;

import chess.domain.ChessBoard;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.square.Square;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ChessBoardDao {
    private static String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static String DATABASE = "chess"; // MySQL DATABASE 이름
    private static String OPTION = "?useSSL=false&serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8";
    private static String USERNAME = "root"; //  MySQL 서버 아이디
    private static String PASSWORD = "root"; // MySQL 서버 비밀번호
    private static ChessBoardDao instance;

    public static ChessBoardDao getInstance() {
        if (instance == null) return new ChessBoardDao();
        return instance;
    }

    private ChessBoardDao() {
    }

    public Connection getConnection() {
        Connection con = null;
        // 드라이버 로딩
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        // 드라이버 연결
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }

        return con;
    }

    // 드라이버 연결해제
    public void closeConnection(Connection con) {
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }

    public ChessBoard retrieve() throws SQLException {
        final String sql = "SELECT square, piece FROM chessboard";
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet rs = statement.executeQuery()
        ) {
            ChessBoard chessBoard = new ChessBoard();
            Map<Square, Piece> savedBoard = new HashMap<>();
            while (rs.next()) {
                String square = rs.getString("square");
                String piece = rs.getString("piece");
                savedBoard.put(Square.of(square), PieceFactory.of(piece));
                chessBoard.updateChessBoard(savedBoard);
            }
            return chessBoard;
        }
    }

    public void save(ChessBoard board) throws SQLException {
        try (
                Connection connection = getConnection()
        ) {
            removeAll(connection);
            for (Square square : board.getChessBoard().keySet()) {
                savePiece(connection, square, board.getChessBoard().get(square));
            }
        }
    }

    private void removeAll(Connection connection) throws SQLException {
        String sql = "DELETE FROM chessboard";
        Statement statement = connection.createStatement();
        statement.execute(sql);
    }

    private void savePiece(Connection connection, Square square, Piece piece) throws SQLException {
        String sql = "INSERT INTO chessboard(square, piece) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, square.toString());
        statement.setString(2, piece.toString());
        statement.executeUpdate();
    }
}
