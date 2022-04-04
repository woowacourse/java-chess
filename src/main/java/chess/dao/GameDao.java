package chess.dao;

import chess.domain.Camp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GameDao {

    public void save() throws SQLException {
        Connection connection = DatabaseConnector.getConnection();
        String sql = "insert into game (no, white_turn) values (0,?)";
        if (isGameExist()) {
            sql = "update game set white_turn = ?";
        }

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setBoolean(1, Camp.BLACK.isNotTurn());
        statement.execute();
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean isGameExist() throws SQLException {
        Connection connection = DatabaseConnector.getConnection();
        final String sql = "select no from game";
        boolean gameExist;

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        gameExist = resultSet.next();
        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gameExist;
    }

    public boolean isWhiteTurn() throws SQLException {
        Connection connection = DatabaseConnector.getConnection();
        final String sql = "select white_turn from game";
        boolean whiteTurn;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        whiteTurn = resultSet.getBoolean("white_turn");
        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return whiteTurn;
    }
}
