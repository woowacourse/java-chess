package domain.jdbc;

import domain.ChessGame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ChessGameDao implements JdbcDao {

    private static final String SERVER = "localhost:13306";
    private static final String DATABASE = "chess";
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public void save() {

    }

    @Override
    public ChessGame select() {
        return null;
    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

}
