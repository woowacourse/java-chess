package chess.web.dao;

import chess.domain.Color;
import chess.web.DBConnector;
import chess.web.dto.GameStateDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardDao {

    private static final String ERROR_DB_FAILED = "[ERROR] DB 연결에 문제가 발생했습니다.";

    public void save(int userId, GameStateDto gameStateDto) {
        final String sql = "insert into board (player_id, turn) values (?, ?)";

        try (final Connection connection = DBConnector.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.setString(2, gameStateDto.getTurn());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(ERROR_DB_FAILED);
        }
    }

    public Color getTurn(int boardId) {
        final String sql = "select turn from board where id = ?";
        Color color = Color.WHITE;

        try (final Connection connection = DBConnector.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, boardId);
            try (final ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    color = Color.valueOf(resultSet.getString("turn").toUpperCase());
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(ERROR_DB_FAILED);
        }
        return color;
    }

    public int getBoardIdByPlayer(int plyerId) {
        final String sql = "select id from board where player_id = ?";
        int id = 0;

        try (final Connection connection = DBConnector.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, plyerId);
            try (final ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    id = resultSet.getInt("id");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(ERROR_DB_FAILED);
        }
        return id;
    }

    public void update(int boardId, GameStateDto gameStateDto) {
        final String sql = "update board set turn = ? where id = ?";

        try (final Connection connection = DBConnector.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, gameStateDto.getTurn());
            statement.setInt(2, boardId);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(ERROR_DB_FAILED);
        }
    }

}
