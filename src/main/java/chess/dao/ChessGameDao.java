package chess.dao;

import chess.domain.piece.Team;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ChessGameDao {
    private static final String URL = "jdbc:mysql://localhost:13306/"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "user"; //  MySQL 서버 아이디
    private static final String PASSWORD = "password"; // MySQL 서버 비밀번호
    public static final String QUERY_INSERT_CHESS_GAME = "INSERT INTO chess_game VALUES(?, ?, ?, default)";
    public static final String WHITE_MARK = "W";
    public static final String BLACK_MARK = "B";

    public Connection getConnection() {
        // 드라이버 연결
        try {
            return DriverManager.getConnection(URL + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void addGame(RoomName roomName, Team turn, String chessBoard) {
        final var query = QUERY_INSERT_CHESS_GAME;
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, roomName.getRoomName());
            preparedStatement.setString(2, convertTurn(turn));
            preparedStatement.setString(3, chessBoard);
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String convertTurn(Team team) {
        if (team == Team.WHITE) {
            return WHITE_MARK;
        }
        if (team == Team.BLACK) {
            return BLACK_MARK;
        }
        throw new IllegalArgumentException("잘못된 팀입니다.");
    }
}
