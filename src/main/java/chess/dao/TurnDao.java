package chess.dao;

import chess.domain.piece.Team;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TurnDao {
    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";


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

    public void saveTurn(String team) {
        final Connection connection = getConnection();
        final String insertTurnSql = "update turn set team = ?";
        try {
            final PreparedStatement updateStatement = connection.prepareStatement(insertTurnSql);
            updateStatement.setString(1, team);
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Team getTurnTeam() {
        final Connection connection = getConnection();
        final String getTurnTeamSql = "select * from turn";
        Team team = null;
        try {
            final PreparedStatement statement = connection.prepareStatement(getTurnTeamSql);
            final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                team = Team.of(resultSet.getString("team"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return team;
    }
}
