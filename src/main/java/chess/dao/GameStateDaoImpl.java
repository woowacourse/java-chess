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

    private GameStateDaoImpl() {
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
        final String sql = "select count(*) as result from game";
        int count = 0;
        try (final PreparedStatement statement = getConnection().prepareStatement(sql);
             final ResultSet resultSet = statement.executeQuery()) {
            count = getGameCount(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count > 0;
    }

    private int getGameCount(final ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return resultSet.getInt("result");
        }
        return 0;
    }

    @Override
    public void saveState(final String state) {
        String sql = "insert into game (state) values (?)";
        if (hasPlayingGame()) {
            sql = "update game set state = (?)";
        }
        try (final PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, state);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveTurn(final String turn) {
        String sql = "insert into game (turn) values (?)";
        if (hasPlayingGame()) {
            sql = "update game set turn = (?)";
        }
        try (final PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, turn);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getGameState() {
        final String sql = "select state from game";
        String state = null;
        try (final PreparedStatement statement = getConnection().prepareStatement(sql);
             final ResultSet resultSet = statement.executeQuery()) {
            state = getGameState(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return state;
    }

    private String getGameState(final ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return resultSet.getString("state");
        }
        return DATABASE_EMPTY_SYMBOL;
    }

    @Override
    public String getTurn() {
        final String sql = "select turn from game";
        String turn = null;
        try (final PreparedStatement statement = getConnection().prepareStatement(sql);
             final ResultSet resultSet = statement.executeQuery()) {
            turn = getTurn(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return turn;
    }

    private String getTurn(final ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return resultSet.getString("turn");
        }
        return DATABASE_EMPTY_SYMBOL;
    }

    @Override
    public void removeGameState() {
        final String sql = "TRUNCATE game";
        try (final PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
