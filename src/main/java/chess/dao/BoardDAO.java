package chess.dao;

import chess.domain.piece.PieceFactory;
import chess.domain.board.Board;
import chess.domain.Side;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BoardDAO {

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

    public void addBoard(Board board, String turn) throws SQLException {
        String query = "INSERT INTO board VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, "1");
        pstmt.setString(2, boardPositionSet(board.getBoard()));
        pstmt.setString(3, boardPieceSet(board.getBoard()));
        pstmt.setString(4, turn);
        pstmt.executeUpdate();
    }

    public void initBoardTable() throws SQLException {
        String query = "truncate table board";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.executeUpdate();
    }

    public void updateBoard(Board board, String turn) throws SQLException {
        String query = "UPDATE board SET position = ?, pieceName = ?, turn = ? WHERE number = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, boardPositionSet(board.getBoard()));
        pstmt.setString(2, boardPieceSet(board.getBoard()));
        pstmt.setString(3, turn);
        pstmt.setString(4, "1");
        pstmt.executeUpdate();
    }

    public Map<Position, Piece> findBoard(String game) throws SQLException {
        String query = "SELECT * FROM board WHERE number = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, game);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return null;

        return daoToBoard(rs.getString("position"), rs.getString("pieceName"));
    }

    public Side findTurn(String game) throws SQLException {
        String query = "SELECT * FROM board WHERE number = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, game);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return null;

        if (rs.getString("turn").equals("WHITE")) {
            return Side.WHITE;
        }
        if (rs.getString("turn").equals("BLACK")) {
            return Side.BLACK;
        }
        return Side.NONE;
    }

    private String boardPositionSet(Map<Position, Piece> board) {
        return board.keySet()
                .stream()
                .map(Position::stringPosition)
                .collect(Collectors.joining(","));
    }

    private String boardPieceSet(Map<Position, Piece> board) {
        List<String> pieceNames = new ArrayList<>();
        for (Piece piece : board.values()) {
            String pieceName = piece.getInitial();
            if (piece.side() == Side.WHITE) {
                pieceNames.add("W" + pieceName.toUpperCase());
            } else if (piece.side() == Side.BLACK) {
                pieceNames.add("B" + pieceName.toUpperCase());
            } else {
                pieceNames.add(pieceName.toUpperCase());
            }
        }
        return String.join(",", pieceNames);
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
