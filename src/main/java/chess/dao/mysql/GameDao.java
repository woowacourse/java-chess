package chess.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import chess.dao.dto.GameDto;

public class GameDao {

    private static final GameDao GAME_DAO = new GameDao(new MysqlConnector());

    private final MysqlConnector mysqlConnector;

    public GameDao(final MysqlConnector mysqlConnector) {
        this.mysqlConnector = mysqlConnector;
    }

    public static GameDao getInstance() {
        return GAME_DAO;
    }

    public Long save(final GameDto gameDto) {
        final Connection connection = mysqlConnector.getConnection();

        Long gameId = null;
        try {
            final String sql = "INSERT INTO Game (player_id1, player_id2, finished, turn_color) VALUES (?,?,?,?)";
            final PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, gameDto.getPlayer_id1());
            preparedStatement.setLong(2, gameDto.getPlayer_id2());
            preparedStatement.setBoolean(3, gameDto.getFinished());
            preparedStatement.setString(4, gameDto.getCurrentTurnColor());
            preparedStatement.executeUpdate();

            final ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                gameId = (long) resultSet.getInt(1);
            }
            mysqlConnector.closeConnection(connection, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gameId;
    }

    public GameDto findById(final Long id) {
        final Connection connection = mysqlConnector.getConnection();

        GameDto gameDto = null;
        try {
            final String sql = "SELECT id, player_id1, player_id2, finished, turn_color FROM Game where id=?";
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            final ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }

            gameDto = new GameDto(
                    resultSet.getLong("id"),
                    resultSet.getLong("player_id1"),
                    resultSet.getLong("player_id2"),
                    resultSet.getBoolean("finished"),
                    resultSet.getString("turn_color"));

            mysqlConnector.closeConnection(connection, preparedStatement, resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gameDto;
    }

    public void update(final GameDto gameDto) {
        final Connection connection = mysqlConnector.getConnection();
        try {
            final String sql = "UPDATE Game SET finished=?, turn_color=? WHERE id=?";
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBoolean(1, gameDto.getFinished());
            preparedStatement.setString(2, gameDto.getCurrentTurnColor());
            preparedStatement.setLong(3, gameDto.getId());
            preparedStatement.executeUpdate();
            mysqlConnector.closeConnection(connection, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
