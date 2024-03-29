package chess.domain.db;

import chess.domain.pieceInfo.Team;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class ChessBoardDao {

    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "root"; // MySQL 서버 비밀번호

    public Connection getConnection() {
        // 드라이버 연결
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void addPiece(final ChessBoard chessBoard) {
        final var query = "INSERT INTO chessBoard VALUES(?, ?, ?) ON DUPLICATE KEY UPDATE team = ?, type = ?";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, chessBoard.position());
            preparedStatement.setString(2, chessBoard.team());
            preparedStatement.setString(3, chessBoard.type());
            preparedStatement.setString(4, chessBoard.team());
            preparedStatement.setString(5, chessBoard.type());
            preparedStatement.execute();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ChessBoard findByPosition(final String position) {
        final var query = "SELECT * FROM chessBoard WHERE position = ?";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, position);

            final var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new ChessBoard(
                        resultSet.getString("position"),
                        resultSet.getString("team"),
                        resultSet.getString("type")
                );
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public List<ChessBoard> findAll() {
        final var query = "SELECT * FROM chessBoard";
        final List<ChessBoard> result = new ArrayList<>();
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(new ChessBoard(
                        resultSet.getString("position"),
                        resultSet.getString("team"),
                        resultSet.getString("type")
                ));
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public void deleteAll() {
        final var query = "DELETE FROM chessBoard";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.execute();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addTurn(Team turn) {
        final var query = "INSERT INTO gameInfo VALUES(?) ON DUPLICATE KEY UPDATE turn = ?";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, turn.name());
            preparedStatement.setString(2, turn.name());
            preparedStatement.execute();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Team findTurn() {
        final var query = "SELECT * FROM gameInfo";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Team.valueOf(resultSet.getString("turn"));
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
