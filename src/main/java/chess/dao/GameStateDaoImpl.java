package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameStateDaoImpl implements GameStateDao {

    private static final GameStateDaoImpl INSTANCE = new GameStateDaoImpl();

    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    private static final String DATABASE_EMPTY_SYMBOL = "nothing";

    public static GameStateDaoImpl getInstance() {
        return INSTANCE;
    }

    private Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public boolean hasPlayingGame() {
        final Connection connection = getConnection();
        final String sql = "select count(*) as result from game";
        int count = 0;
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt("result");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count > 0;
    }

    @Override
    public void saveState(final String state) {
        final Connection connection = getConnection();
        String sql = "insert into game (state) values (?)";
        if (hasPlayingGame()) {
            sql = "update game set state = (?)";
        }
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, state);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveTurn(final String turn) {
        final Connection connection = getConnection();
        String sql = "insert into game (turn) values (?)";
        if (hasPlayingGame()) {
            sql = "update game set turn = (?)";
        }
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, turn);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getGameState() {
        final Connection connection = getConnection();
        final String sql = "select state from game";
        String state = null;
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                state = resultSet.getString("state");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (state == null) {
            return DATABASE_EMPTY_SYMBOL;
        }
        return state;
    }

    @Override
    public String getTurn() {
        final Connection connection = getConnection();
        final String sql = "select turn from game";
        String turn = null;
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                turn = resultSet.getString("turn");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (turn == null) {
            return DATABASE_EMPTY_SYMBOL;
        }
        return turn;
    }

    @Override
    public void removeGameState() {
        final Connection connection = getConnection();
        final String sql = "TRUNCATE game";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
