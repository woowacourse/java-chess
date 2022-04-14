package chess.dao;

import chess.domain.state.GameState;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChessGameDao {
    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private static final String TABLE_ID = "id";
    private static final String CHESSGAME_STATE = "state";
    private static final String SQL_STATEMENT_EXCEPTION = "[ERROR] SQL 문을 실행할 수 없습니다.";
    private static final String DATA_NOT_EXISTS_EXCEPTION = "[ERROR] 요청된 데이터가 존재하지 않습니다.";

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void save(GameState gameState) {
        final Connection connection = getConnection();
        final String sql = "insert into chessgame (state) values (?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, gameState.getClass().getSimpleName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(SQL_STATEMENT_EXCEPTION);
        }
    }

    public Integer findId() {
        final Connection connection = getConnection();
        final String sql = "select id from chessgame";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(TABLE_ID);
            }
            throw new IllegalArgumentException(DATA_NOT_EXISTS_EXCEPTION);
        } catch (SQLException e) {
            throw new IllegalArgumentException(SQL_STATEMENT_EXCEPTION);
        }
    }

    public String findStateName() {
        final Connection connection = getConnection();
        final String sql = "select state from chessgame";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(CHESSGAME_STATE);
            }
            throw new IllegalArgumentException(DATA_NOT_EXISTS_EXCEPTION);
        } catch (SQLException e) {
            throw new IllegalArgumentException(SQL_STATEMENT_EXCEPTION);
        }
    }

    public void update(GameState gameState) {
        final Connection connection = getConnection();
        final String sql = "update chessgame set state = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, gameState.getClass().getSimpleName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        final Connection connection = getConnection();
        final String sql = "delete from chessgame";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
