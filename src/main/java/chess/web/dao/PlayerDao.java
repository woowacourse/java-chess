package chess.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import chess.domain.game.state.Player;
import chess.web.utils.DBConnector;

public class PlayerDao {
    private final Connection connection = DBConnector.getConnection();

    public int save(Player player) {
        final String sql = "insert into player (name) values (?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, player.name());
            statement.executeUpdate();
            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                return result.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Player find() {
        final String sql = "select name from player";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return Player.getName(resultSet.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Player.White;
    }

    public void remove(int id) {
        final String sql = "delete from player where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
