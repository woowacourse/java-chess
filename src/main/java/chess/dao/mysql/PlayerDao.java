package chess.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import chess.dao.dto.PlayerDto;

public class PlayerDao {

    private static final PlayerDao PLAYER_DAO = new PlayerDao(new MysqlConnector());

    private final MysqlConnector mysqlConnector;

    public PlayerDao(final MysqlConnector mysqlConnector) {
        this.mysqlConnector = mysqlConnector;
    }

    public static PlayerDao getInstance() {
        return PLAYER_DAO;
    }

    public Long save(final PlayerDto playerDto) {
        final Connection connection = mysqlConnector.getConnection();

        Long playerId = null;
        try {
            final String sql = "INSERT INTO Player (color, pieces) VALUES (?,?)";
            final PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, playerDto.getColorName());
            preparedStatement.setString(2, playerDto.getPieces());
            preparedStatement.executeUpdate();

            final ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                playerId = (long) resultSet.getInt(1);
            }
            mysqlConnector.closeConnection(connection, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playerId;
    }

    public PlayerDto findById(final Long id) {
        final Connection connection = mysqlConnector.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            final String sql = "SELECT id, color, pieces FROM Player where id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return new PlayerDto(
                    resultSet.getLong("id"),
                    resultSet.getString("color"),
                    resultSet.getString("pieces")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mysqlConnector.closeConnection(connection, preparedStatement, resultSet);
        return null;
    }
}
