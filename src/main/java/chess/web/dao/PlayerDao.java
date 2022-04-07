package chess.web.dao;

import chess.web.DBConnector;
import chess.web.dto.PlayerDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class PlayerDao {

    public void save(String name) {
        final Connection connection = DBConnector.getConnection();
        final String sql = "insert into player (name) values (?)";

        try (final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<PlayerDto> find(String name) {
        final Connection connection = DBConnector.getConnection();
        final String sql = "select * from player where name = ?";
        Optional<PlayerDto> playerDto = Optional.ofNullable(null);
        try (final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                playerDto = Optional.of(new PlayerDto(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("win"),
                        resultSet.getInt("draw"),
                        resultSet.getInt("lose")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return playerDto;
    }
}
