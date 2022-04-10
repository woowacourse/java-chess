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
        final String updateTurnSql = "update turn set team = ?";
        try {
            final PreparedStatement updateStatement = connection.prepareStatement(updateTurnSql);
            updateStatement.setString(1, team);
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    private void checkTurnExistence(Connection connection) {
//        final String getTurnSql = "select * from turn";
//
//        try {
//            final PreparedStatement statement = connection.prepareStatement(getTurnSql);
//            final ResultSet resultSet = statement.executeQuery();
//            if (!resultSet.next()) {
//               
//                PreparedStatement preparedStatement = connection.prepareStatement(insertTurnSql);
//                preparedStatement.executeQuery();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public Team getTurnTeam() {
        final Connection connection = getConnection();
        final String getTurnTeamSql = "select * from turn";
        Team team = null;
        try {
            final PreparedStatement statement = connection.prepareStatement(getTurnTeamSql);
            final ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                final String insertTurnSql = "insert into turn(team) values ('none')";
                PreparedStatement preparedStatement = connection.prepareStatement(insertTurnSql);
                preparedStatement.executeUpdate();
                return Team.NONE;
            }
            team = Team.of(resultSet.getString("team"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return team;
    }
}
