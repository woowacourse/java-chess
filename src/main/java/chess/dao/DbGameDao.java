package chess.dao;

import chess.domain.Team;
import chess.dto.GameInformationDto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DbGameDao implements GameDao {
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

    @Override
    public void saveGame(GameInformationDto gameInformationDto) {
        final Connection connection = getConnection();
        final String sql = "insert into game (id, turn) values (?, ?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, gameInformationDto.getId());
            statement.setString(2, teamToName.get(gameInformationDto.getTurn()));
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public GameInformationDto getGameData(int gameId) {
        final Connection connection = getConnection();
        final String sql = "select * from game where id = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, gameId);
            final ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return GameInformationDto.of(resultSet.getInt("id"),
                    nameToTeam.get(resultSet.getString("turn")));
        } catch (SQLException e) {
            e.printStackTrace();;
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateGameData(int gameId, GameInformationDto gameInformationDto) {
        final Connection connection = getConnection();
        final String sql = "update game set turn = ? where id = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, teamToName.get(gameInformationDto.getTurn()));
            statement.setInt(2, gameId);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteGameData(int gameId) {
        final Connection connection = getConnection();
        final String sql = "delete from game where id = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, gameId);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();;
            throw new RuntimeException(e);
        }
    }
}
