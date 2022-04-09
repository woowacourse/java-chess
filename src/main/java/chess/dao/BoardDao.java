package chess.dao;

import chess.domain.piece.PieceFactory;
import chess.dto.BoardDto;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.dto.GridDto;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BoardDao {

    public void saveBoard(BoardDto boardDto) throws SQLException {
        Objects.requireNonNull(boardDto);
        Connection connection = getConnection();
        String query = "INSERT INTO chessboard VALUES (?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(query);

        for (GridDto grid : boardDto.getBoard()) {
            pstmt.setString(1, grid.getPosition());
            pstmt.setString(2, grid.getPiece());
            pstmt.setInt(3, grid.getMoveCount());
            pstmt.addBatch();
        }

        try (connection; pstmt) {
            pstmt.executeBatch();
        } catch (SQLException e) {
            throw new SQLException("CANNOT SAVE BOARD ERROR");
        }
    }

    public void deleteBoard() throws SQLException {
        Connection connection = getConnection();
        Statement stmt = connection.createStatement();

        String query = "DELETE FROM chessboard";
        try (connection; stmt) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            throw new SQLException("CANNOT DELETE BOARD ERROR");
        }
    }

    public Map<Position, Piece> getBoard() throws SQLException {
        Connection connection = getConnection();
        Statement stmt = connection.createStatement();

        String query = "SELECT * FROM chessboard";
        ResultSet rs = stmt.executeQuery(query);

        Map<Position, Piece> chessBoard = new HashMap<>();
        try (connection; stmt; rs) {
            while (rs.next()) {
                Position position = Position.of(rs.getString("position"));
                Piece piece = PieceFactory.of(rs.getString("piece"), rs.getInt("moveCount"));
                chessBoard.put(position, piece);
            }
        } catch (SQLException e) {
            throw new SQLException("CANNOT LOAD BOARD ERROR");
        }
        return chessBoard;
    }

    public boolean isBoardExists() throws SQLException {
        Connection connection = getConnection();

        Statement stmt = connection.createStatement();
        String query = "SELECT * FROM chessboard";
        ResultSet rs = stmt.executeQuery(query);
        try (connection; stmt; rs) {
            return rs.next();
        } catch (SQLException e) {
            throw new SQLException("CANNOT FIND BOARD EXISTENCE");
        }
    }

    public Connection getConnection() {
        final String server = "localhost:3306"; // MySQL 서버 주소
        final String database = "chess"; // MySQL DATABASE 이름
        final String option = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        final String userName = "user"; //  MySQL 서버 아이디
        final String password = "password"; // MySQL 서버 비밀번호

        // 드라이버 로딩
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        // 드라이버 연결
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option, userName, password);
            System.out.println("정상적으로 연결되었습니다.");
            return connection;
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    // 드라이버 연결해제
    public void close(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }
}