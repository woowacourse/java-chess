package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.domain.Game;
import chess.domain.piece.Side;

public class GameDao implements JdbcTemplateDao {

    public static final String GAME_ID = "id";
    public static final String WHITE_ID = "white";
    public static final String BLACK_ID = "black";

    public int add(final Game game) throws SQLException {
        String query = "insert into game (white, black) values (?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setInt(1, game.getPlayerId(Side.WHITE));
            statement.setInt(2, game.getPlayerId(Side.BLACK));
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            throw new SQLException();
        }
    }

    public List<Integer> getAllGameId() throws SQLException {
        String query = "select id from game";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ) {
            ResultSet resultSet = statement.executeQuery();
            List<Integer> gameIds = new ArrayList<>();
            while (resultSet.next()) {
                gameIds.add(resultSet.getInt("id"));
            }
            return gameIds;
        }
    }

    public List<Map<String, Integer>> findGamesData() throws SQLException {
        String query = "select id, white, black from game";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ) {
            ResultSet resultSet = statement.executeQuery();
            List<Map<String, Integer>> games = new ArrayList<>();
            while (resultSet.next()) {
                Map<String, Integer> game = new HashMap<>();
                game.put(GAME_ID, resultSet.getInt("id"));
                game.put(WHITE_ID, resultSet.getInt("white"));
                game.put(BLACK_ID, resultSet.getInt("black"));
                games.add(game);
            }
            return games;
        }
    }

    public Map<String, Integer> findGameDataById(int gameId) throws SQLException {
        String query = "select id, white, black from game where id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setInt(1, gameId);
            ResultSet resultSet = statement.executeQuery();
            Map<String, Integer> game = new HashMap<>();
            if (resultSet.next()) {
                game.put(GAME_ID, resultSet.getInt("id"));
                game.put(WHITE_ID, resultSet.getInt("white"));
                game.put(BLACK_ID, resultSet.getInt("black"));
            }
            return game;
        }
    }

    public void remove(final Game game) throws SQLException {
        String query = "delete from game where id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setInt(1, game.getId());
            statement.executeUpdate();
        }
    }
}
