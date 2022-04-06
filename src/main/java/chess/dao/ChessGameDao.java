package chess.dao;

import chess.domain.Color;
import chess.dto.ResponseDto;

import java.sql.*;
import java.util.Objects;

public class ChessGameDao {
    public void saveGame(ResponseDto responseDto) throws SQLException {
        Objects.requireNonNull(responseDto);

        Connection connection = getConnection();
        String query = "INSERT INTO chessgame (turn, white_score, black_score) VALUES (?,?,?)";
        PreparedStatement pstmt = connection.prepareStatement(query);

        try (connection; pstmt) {
            pstmt.setString(1, responseDto.getTurn().name());
            pstmt.setDouble(2, responseDto.getResult().getScoresOf(Color.WHITE));
            pstmt.setDouble(3, responseDto.getResult().getScoresOf(Color.BLACK));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("CANNOT SAVE GAME INFO ERROR");
        }
    }

    public void updateGame(ResponseDto responseDto) throws SQLException {
        Objects.requireNonNull(responseDto);

        Connection connection = getConnection();
        String query = "UPDATE chessgame " +
                "SET turn = ?, white_score = ?, black_score = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);

        try (connection; pstmt) {
            pstmt.setString(1, responseDto.getTurn().name());
            pstmt.setDouble(2, responseDto.getResult().getScoresOf(Color.WHITE));
            pstmt.setDouble(3, responseDto.getResult().getScoresOf(Color.BLACK));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("CANNOT UPDATE GAME INFO ERROR");
        }
    }

    public Color getTurn() throws SQLException {
        Connection connection = getConnection();

        Statement stmt = connection.createStatement();
        String query = "SELECT turn FROM chessgame";
        ResultSet rs = stmt.executeQuery(query);

        try (connection; stmt; rs) {
            String turn = null;
            if (rs.next()) {
                turn = rs.getString("turn");
            }
            return Color.of(turn);
        } catch (SQLException e) {
            throw new SQLException("CANNOT FIND CORRECT TURN ERROR.");
        }
    }

    public void deleteGame() throws SQLException {
        Connection connection = getConnection();
        Statement stmt = connection.createStatement();
        String query = "DELETE FROM chessgame";

        try (connection; stmt) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            throw new SQLException("CANNOT DELETE GAME INFO ERROR");
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