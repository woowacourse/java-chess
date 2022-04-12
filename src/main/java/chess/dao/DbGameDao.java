package chess.dao;

import chess.domain.Team;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DbGameDao {
    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private static final Map<String, Team> nameToTeam = new HashMap<>();
    private static final Map<Team, String> teamToName = new HashMap<>();

    static {
        nameToTeam.put("white", Team.WHITE);
        nameToTeam.put("black", Team.BLACK);

        teamToName.put(Team.WHITE, "white");
        teamToName.put(Team.BLACK, "black");
    }

    public Connection getConnection() {
        loadDriver();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public void saveGame(int gameId, String turn) {
        final Connection connection = getConnection();
        final String sql = "insert into game (id, turn) values (?, ?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, gameId);
            statement.setString(2, turn);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String getTurn(int gameId) {
        final Connection connection = getConnection();
        final String sql = "select turn from game where id = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, gameId);
            final ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return "";
            }
            return resultSet.getString("turn");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void updateGameData(int gameId, String turn) {
        final Connection connection = getConnection();
        final String sql = "update game set turn = ? where id = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, turn);
            statement.setInt(2, gameId);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void deleteGameData(int gameId) {
        final Connection connection = getConnection();
        final String sql = "delete from game where id = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, gameId);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
