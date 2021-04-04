package chess.dao;

import chess.User;
import chess.domain.ChessGame;
import chess.domain.Point;
import chess.domain.piece.Color;
import chess.domain.piece.PieceType;
import chess.domain.piece.kind.Piece;
import chess.dto.ChessGameDto;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ChessGameDao {
    public Connection getConnection() {
        Connection con = null;
        String server = "localhost:13306"; // MySQL 서버 주소
        String database = "chess"; // MySQL DATABASE 이름
        String option = "?useSSL=false&serverTimezone=UTC";
        String userName = "root"; //  MySQL 서버 아이디
        String password = "root"; // MySQL 서버 비밀번호

        // 드라이버 로딩
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        // 드라이버 연결
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option, userName, password);
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

    public void addUser(String userId) throws SQLException {
        String query = "INSERT INTO user(user_id) VALUES(?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, userId);
        pstmt.executeUpdate();
    }

    public User findUserById(String userId) throws SQLException {
        String query = "SELECT user_id FROM user WHERE user_id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, userId);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) {
            return null;
        }
        return new User(rs.getString("user_id"));
    }

    public ChessGameDto findGameByUserId(String userId) throws SQLException {
        String query = "SELECT * FROM game WHERE user_id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, userId);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) {
            return null;
        }

        String currentColor = rs.getString("current_color");
        String[] pieceNames = rs.getString("pieces").split("");
        Map<Point, Piece> board = new HashMap<>();

        for (int j = 0; j <= 7; j++) {
            for (int i = 7; i >= 0; i--) {
                board.put(Point.of(i, j), PieceType.createByPieceName(pieceNames[(7 - i) + j * 8].charAt(0)));
            }
        }


        ChessGameDto chessGameDto = new ChessGameDto();
        chessGameDto.setBoard(board);
        chessGameDto.setCurrentColor(Color.parseColor(currentColor));
        return chessGameDto;
    }

    public void updateGame(String userId, ChessGame chessGame) throws SQLException {
        Map<Point, Piece> board = chessGame.getBoard();
        Color currentColor = chessGame.getCurrentColor();

        StringBuilder sb = new StringBuilder();
        for (char letter = 'a'; letter <= 'h'; letter++) {
            for (int i = 1; i <= 8; i++) {
                String s = Character.toString(letter) + Integer.toString(i);
                sb.append(board.get(Point.of(s)).getName());
            }
        }

        String query = "UPDATE game SET pieces = ?, current_color = ? WHERE user_id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, sb.toString());
        pstmt.setString(2, currentColor.getName());
        pstmt.setString(3, userId);
        pstmt.executeUpdate();
    }

    public void createNewGame(String userId, Map<Point, Piece> board) throws SQLException {
        StringBuilder sb2 = new StringBuilder();
        for (char letter = 'a'; letter <= 'h'; letter++) {
            for (int i = 1; i <= 8; i++) {
                String s = Character.toString(letter) + Integer.toString(i);
                sb2.append(board.get(Point.of(s)).getName());
            }
        }
        String query = "INSERT INTO game(user_id, current_color, pieces) VALUES(?, ?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, userId);
        pstmt.setString(2, "white");
        pstmt.setString(3, sb2.toString());
        pstmt.executeUpdate();
    }
}
