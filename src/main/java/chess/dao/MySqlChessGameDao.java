package chess.dao;

import chess.domain.piece.Side;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlChessGameDao implements ChessGameDao {

    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "root"; // MySQL 서버 비밀번호

    @Override
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

    @Override
    public Side selectTurn() {
        final var query = "SELECT turn FROM chess_game";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            Side side = null;

            final var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                final String turn = resultSet.getString("turn");
                side = Side.valueOf(turn);
            }

            return side;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateTurn(Side turn) {
        final var query = "UPDATE chess_game SET turn = ?";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, turn.name());

            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete() {
        final var query = "DELETE FROM chess_game";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertTurn(Side turn) {
        final var query = "INSERT INTO chess_game(turn) VALUES (?)";

        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, turn.name());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
