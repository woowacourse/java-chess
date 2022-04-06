package chess.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;

import chess.dao.dto.GameDto;
import chess.dao.dto.GameUpdateDto;

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

    public Map<Long, Boolean> findIdAndFinished() {
        final Connection connection = mysqlConnector.getConnection();

        try {
            final String sql = "SELECT id, finished FROM Game order by id desc";
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            final ResultSet resultSet = preparedStatement.executeQuery();

            final Map<Long, Boolean> gameStates = new LinkedHashMap<>();
            while (resultSet.next()) {
                final Long gameId = resultSet.getLong("id");
                final Boolean finished = resultSet.getBoolean("finished");
                gameStates.put(gameId, finished);
            }
            mysqlConnector.closeConnection(connection, preparedStatement, resultSet);
            return gameStates;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(final GameUpdateDto gameUpdateDto) {
        final Connection connection = mysqlConnector.getConnection();
        try {
            final String sql = "UPDATE Game SET finished=?, turn_color=? WHERE id=?";
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBoolean(1, gameUpdateDto.getFinished());
            preparedStatement.setString(2, gameUpdateDto.getCurrentTurnColor());
            preparedStatement.setLong(3, gameUpdateDto.getId());
            preparedStatement.executeUpdate();
            mysqlConnector.closeConnection(connection, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(final long id) {
        final Connection connection = mysqlConnector.getConnection();
        try {
            final String sql = "DELETE FROM Game WHERE id=?";
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            mysqlConnector.closeConnection(connection, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
