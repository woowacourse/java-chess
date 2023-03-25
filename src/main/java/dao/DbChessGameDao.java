package dao;

import domain.board.ChessGame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbChessGameDao implements ChessGameDao {

    private static final String SERVER = "localhost:13306";
    private static final String DATABASE = "chess";
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String MYSQL_USERNAME = "root";
    private static final String MYSQL_PASSWORD = "root";

    private ChessGame chessGame = new ChessGame();

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
    public void create(final ChessGame chessGame) {

    }

    @Override
    public ChessGame read() {
        return null;
    }

    @Override
    public void update(final ChessGame chessGame) {

    }

    @Override
    public void delete() {

    }
}
