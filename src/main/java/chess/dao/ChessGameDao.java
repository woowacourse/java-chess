package chess.dao;

import chess.domain.piece.Team;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class ChessGameDao {
    private static final String URL = "jdbc:mysql://localhost:13306/"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "user"; //  MySQL 서버 아이디
    private static final String PASSWORD = "password"; // MySQL 서버 비밀번호
    private static final String QUERY_INSERT_CHESS_GAME = "INSERT INTO chess_game VALUES(?, ?, ?, default)";
    private static final String WHITE_MARK = "W";
    private static final String BLACK_MARK = "B";
    private static final String QUERY_SELECT_ROOM_NAMES = "SELECT room_name FROM chess_game";
    private static final String QUERY_SELECT_CHESS_GAME = "SELECT turn, chess_board FROM chess_game WHERE room_name = ?";
    private static final String QUERY_DELETE_CHESS_GAME = "DELETE FROM chess_game WHERE room_name = ?";

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
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(QUERY_INSERT_CHESS_GAME)) {
            preparedStatement.setString(1, roomName.getRoomName());
            preparedStatement.setString(2, convertTurnToMark(turn));
            preparedStatement.setString(3, chessBoard);
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String convertTurnToMark(Team team) {
        if (team == Team.WHITE) {
            return WHITE_MARK;
        }
        if (team == Team.BLACK) {
            return BLACK_MARK;
        }
        throw new IllegalArgumentException("잘못된 팀입니다.");
    }

    public List<String> findRoomNames() {
        final List<String> roomNames = new ArrayList<>();

        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(QUERY_SELECT_ROOM_NAMES)) {

            final var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                roomNames.add(0, resultSet.getString("room_name"));
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }

        return roomNames;
    }

    public ChessGameData findChessGame(final String roomName) {
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(QUERY_SELECT_CHESS_GAME)) {
            preparedStatement.setString(1, roomName);

            final var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String turn = resultSet.getString("turn");
                String oneLineChessBoard = resultSet.getString("chess_board");
                return new ChessGameData(turn, oneLineChessBoard);
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public void deleteGame(final RoomName roomName) {
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(QUERY_DELETE_CHESS_GAME)) {
            preparedStatement.setString(1, roomName.getRoomName());
            preparedStatement.execute();

        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
