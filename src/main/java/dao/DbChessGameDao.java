package dao;

import domain.piece.move.Coordinate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbChessGameDao implements ChessGameDao {

    private static final String SERVER = "localhost:13306";
    private static final String DATABASE = "chess";
    private static final String OPTION = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String MYSQL_USERNAME = "root";
    private static final String MYSQL_PASSWORD = "root";

    /*
CREATE TABLE chess_game (
    start_row VARCHAR(255) NOT NULL,
    start_col VARCHAR(255) NOT NULL,
    end_row VARCHAR(255) NOT NULL,
    end_col VARCHAR(255) NOT NULL
);

     */
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION,
                    MYSQL_USERNAME,
                    MYSQL_PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void create(final Coordinate start, final Coordinate end) {
        final var query = "INSERT INTO chess_game(start_row, start_col, end_row, end_col) VALUES (?, ?, ?, ?)";

        try (final var connection = getConnection();
            final var preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, String.valueOf(start.getRow()));
                preparedStatement.setString(2, String.valueOf(start.getCol()));
                preparedStatement.setString(3, String.valueOf(end.getRow()));
                preparedStatement.setString(4, String.valueOf(end.getCol()));
                preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            System.err.println("생성 오류:" + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Coordinate read() {
        return null;
    }

    @Override
    public void update(final Coordinate start, final Coordinate end) {

    }

    @Override
    public void delete() {

    }
}
