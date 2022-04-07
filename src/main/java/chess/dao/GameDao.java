package chess.dao;

import chess.domain.Camp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GameDao {

    public void saveTo(String databaseName) throws SQLException {
        Connection connection = DatabaseConnector.getConnectionWith(databaseName);
        final String sql = chooseSaveSql(databaseName);

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setBoolean(1, Camp.BLACK.isNotTurn());
        statement.execute();
        DatabaseConnector.close(connection, statement);
    }

    private String chooseSaveSql(String databaseName) throws SQLException {
        String sql = "insert into game (no, white_turn) values (0,?)";
        if (isGameExistIn(databaseName)) {
            sql = "update game set white_turn = ?";
        }
        return sql;
    }

    private boolean isGameExistIn(String databaseName) throws SQLException {
        Connection connection = DatabaseConnector.getConnectionWith(databaseName);
        final String sql = "select no from game";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        boolean gameExist = resultSet.next();
        DatabaseConnector.close(connection, statement, resultSet);
        return gameExist;
    }

    public boolean isWhiteTurnIn(String databaseName) throws SQLException {
        Connection connection = DatabaseConnector.getConnectionWith(databaseName);
        final String sql = "select white_turn from game";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        boolean whiteTurn = resultSet.getBoolean("white_turn");
        DatabaseConnector.close(connection, statement, resultSet);
        return whiteTurn;
    }
}
