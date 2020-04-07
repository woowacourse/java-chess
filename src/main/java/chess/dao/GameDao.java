package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import chess.domain.Game;
import chess.domain.piece.Side;

public class GameDao implements JdbcTemplateDao {

    public int add(final Game game) throws SQLException {
        String query = "insert into game (white, black) values (?, ?, ?, ?)";
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, game.getPlayerId(Side.WHITE));
        statement.setInt(2, game.getPlayerId(Side.BLACK));
        statement.executeUpdate();
        ResultSet resultSet = statement.getGeneratedKeys();
        if (resultSet.next()) {
            int gameId = resultSet.getInt(1);
            closeConnection(connection);
            return gameId;
        }
        closeConnection(connection);
        throw new SQLException();
    }

    public List<Integer> getAllGameId() throws SQLException {
        String query = "select id from game";
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        List<Integer> gameIds = new ArrayList<>();
        while (resultSet.next()) {
            gameIds.add(resultSet.getInt("id"));
        }
        return gameIds;
    }

    public void remove(final Game game) throws SQLException {
        String query = "delete from game where id = ?";
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, game.getId());
        statement.executeUpdate();
        closeConnection(connection);
    }
}
