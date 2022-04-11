package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import chess.Game;

public class GameDaoImpl implements GameDao {

    @Override
    public void save(Game game) {
        Connection connection = JdbcConnection.getConnection();
        deleteById(game.getId());
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
        Connection connection = JdbcConnection.getConnection();
        new BoardDaoImpl().deleteById(id);
        String sql = "delete from game where id = " + "'" + id + "'";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String findTurnById(int id) {
        Connection connection = JdbcConnection.getConnection();
        String sql = "select turn from game where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("turn");
            }
            return "WHITE";

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int findIdByPlayerNames(String idPlayerWhite, String idPlayerBlack) {
        Connection connection = JdbcConnection.getConnection();
        String sql = "select id from game where id_white_player = ? and id_black_player = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, idPlayerWhite);
            statement.setString(2, idPlayerBlack);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("id");
            }

            return assignNewId();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private int assignNewId() {
        Connection connection = JdbcConnection.getConnection();
        String sql = "select id from game order by id desc LIMIT 1";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("id") + 1;
            }

            return 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
