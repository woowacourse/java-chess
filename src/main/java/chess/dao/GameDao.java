package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import chess.domain.Game;
import chess.domain.piece.Side;
import chess.domain.player.Player;

public class GameDao implements JdbcTemplateDao {
    public int add(final Game game) throws SQLException {
        String query = "insert into game (white, black) values (?, ?);";
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, game.getPlayerId(Side.WHITE));
        statement.setInt(2, game.getPlayerId(Side.BLACK));
        statement.executeUpdate();
        ResultSet resultSet = statement.getGeneratedKeys();
        if (resultSet.next()) {
            int gameId = resultSet.getInt(1);
            game.setId(gameId);
            closeConnection(connection);
            return gameId;
        }
        closeConnection(connection);
        throw new SQLException();
    }

    public boolean isEmpty() throws SQLException {
        String query = "select count(*) from game";
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            closeConnection(connection);
            return resultSet.getInt(1) == 0;
        }
        closeConnection(connection);
        throw new SQLException();
    }

    public Game find(final int id) throws SQLException {
        MoveDao moveDao = new MoveDao();
        String query = "select * from game where id = ?";
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            PlayerDao playerDao = new PlayerDao();
            Player white = playerDao.getPlayerById(resultSet.getInt("white"));
            Player black = playerDao.getPlayerById(resultSet.getInt("black"));
            Game game = new Game(white, black);
            game.setId(id);
            moveDao.getMoves(game).forEach(move -> game.move(move.getFrom(), move.getTo()));
            updateScores(game);
            closeConnection(connection);
            return game;
        }
        closeConnection(connection);
        return null;
    }

    public void remove(final Game game) throws SQLException {
        MoveDao moveDao = new MoveDao();
        String query = "delete from game where id = ?";
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, game.getId());
        moveDao.reset(game);
        statement.executeUpdate();
        closeConnection(connection);
    }

    public double getScore(final Game game, final Side side) throws SQLException {
        String query = "select white_score, black_score from game where id = ?";
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, game.getId());
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next() && side == Side.WHITE) {
            return resultSet.getDouble("white_score");
        }
        if (side == Side.BLACK) {
            return resultSet.getDouble("black_score");
        }
        throw new SQLException();
    }

    public Map<Integer, Map<Side, Player>> getPlayerContexts() throws SQLException {
        PlayerDao playerDao = new PlayerDao();
        String query = "select id, white, black from game";
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        Map<Integer, Map<Side, Player>> playerContexts = new HashMap<>();
        while (resultSet.next()) {
            Map<Side, Player> playerContext = new HashMap<>();
            playerContext.put(Side.WHITE, playerDao.getPlayerById(resultSet.getInt("white")));
            playerContext.put(Side.BLACK, playerDao.getPlayerById(resultSet.getInt("black")));
            playerContexts.put(resultSet.getInt("id"), playerContext);
        }
        closeConnection(connection);
        return playerContexts;
    }

    public Map<Integer, Map<Side, Double>> getScoreContexts() throws SQLException {
        String query = "select id, white_score, black_score from game";
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        Map<Integer, Map<Side, Double>> scoreContexts = new HashMap<>();
        while (resultSet.next()) {
            Map<Side, Double> scoreContext = new HashMap<>();
            scoreContext.put(Side.WHITE, resultSet.getDouble("white_score"));
            scoreContext.put(Side.BLACK, resultSet.getDouble("black_score"));
            scoreContexts.put(resultSet.getInt("id"), scoreContext);
        }
        closeConnection(connection);
        return scoreContexts;
    }

    public void updateScores(final Game game) throws SQLException {
        String query = "update game set white_score = ?, black_score = ? where id = ?";
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setDouble(1, game.getScoreOf(Side.WHITE));
        statement.setDouble(2, game.getScoreOf(Side.BLACK));
        statement.setDouble(3, game.getId());
        statement.executeUpdate();
        closeConnection(connection);
    }
}
