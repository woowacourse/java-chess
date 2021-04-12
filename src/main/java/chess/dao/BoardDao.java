package chess.dao;

import chess.domain.Side;
import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.position.Position;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BoardDao {

    private static final String GameNumber = "1";

    public Connection getConnection() {
        Connection con = null;
        String server = "localhost:13306"; // MySQL 서버 주소
        String database = "db_name"; // MySQL DATABASE 이름
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

    public void initBoardTable() {
        try (Connection connection = getConnection()) {
            String query = "truncate table board";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addBoard(Board board, String turn) {
        try (Connection connection = getConnection()) {
            String query = "INSERT INTO board VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, GameNumber);
            pstmt.setString(2, boardPositionSet(board.getBoard()));
            pstmt.setString(3, boardPieceSet(board.getBoard()));
            pstmt.setString(4, turn);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateBoard(Board board, String turn) {
        try (Connection connection = getConnection()) {
            String query = "UPDATE board SET position = ?, pieceName = ?, turn = ? WHERE number = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, boardPositionSet(board.getBoard()));
            pstmt.setString(2, boardPieceSet(board.getBoard()));
            pstmt.setString(3, turn);
            pstmt.setString(4, GameNumber);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Map<Position, Piece> findBoard(String gameNumber) {
        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM board WHERE number = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, gameNumber);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) return null;

            return daoToBoard(rs.getString("position"), rs.getString("pieceName"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Side findTurn(String gameNumber) {
        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM board WHERE number = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, gameNumber);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) return null;

            return Side.getTurnByName(rs.getString("turn"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private String boardPositionSet(Map<Position, Piece> board) {
        return board.keySet()
                .stream()
                .map(Position::positionName)
                .collect(Collectors.joining(","));
    }

    private String boardPieceSet(Map<Position, Piece> board) {
        List<String> pieceNames = new ArrayList<>();
        for (Piece piece : board.values()) {
            pieceNames.add(pieceToName(piece));
        }
        return String.join(",", pieceNames);
    }

    private String pieceToName(Piece piece) {
        String pieceName = piece.getInitial();
        if (piece.side() == Side.WHITE) {
            return "W" + pieceName.toUpperCase();
        }
        if (piece.side() == Side.BLACK) {
            return "B" + pieceName.toUpperCase();
        }
        return pieceName;
    }

    private Map<Position, Piece> daoToBoard(String positions, String pieces) {
        Map<Position, Piece> board = new LinkedHashMap<>();

        String[] position = positions.split(",");
        String[] piece = pieces.split(",");

        for (int i = 0; i < position.length; i++) {
            board.put(Position.from(position[i]), PieceFactory.createPieceByName(piece[i]));
        }
        return board;
    }
}
