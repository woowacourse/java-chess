package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import chess.Game;
import chess.model.Turn;

public class GameDaoImpl implements GameDao {

    private static final String URL = "jdbc:mysql://localhost:13306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    private final Game game;

    public GameDaoImpl(Game game) {
        this.game = game;
    }

    @Override
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void save() {
        Connection connection = getConnection();
        deleteById(getId());
        String sql = "insert into game (id, id_white_player, id_black_player, turn) values (?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, game.getId());
            statement.setString(2, game.getIdWhitePlayer());
            statement.setString(3, game.getIdBlackPlayer());
            statement.setString(4, game.getTurn().toString());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        Connection connection = getConnection();
        new BoardDaoImpl().deleteById(getId());
        String sql = "delete from game where id = " + "'" + id + "'";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getId() {
        return game.getId();
    }

    @Override
    public List<String> findById(int id) {
        Connection connection = getConnection();
        String sql = "select id_white_player, id_black_player from game where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            List<String> result = new ArrayList<>(2);

            while (resultSet.next()) {
                result.add(resultSet.getString("id_white_player"));
                result.add(resultSet.getString("id_black_player"));
            }
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String findTurnById(int id) {
        Connection connection = getConnection();
        String sql = "select turn from game where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("turn");
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void nextTurn() {
        game.nextTurn();
    }
}
