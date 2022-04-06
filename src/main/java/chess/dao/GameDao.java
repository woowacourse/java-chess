package chess.dao;

import chess.service.ChessGameDto;
import chess.service.StatusDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameDao {

    public void update(ChessGameDto dto, int gameId) {
        String sql = "update game set status = ?, turn = ? where id = ?";
        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, dto.getStatus());
            statement.setString(2, dto.getTurn());
            statement.setInt(3, gameId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ChessGameDto findByName(String name) {
        String sql = "select id, status, turn from game where name = ?";
        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return new ChessGameDto(resultSet.getInt("id"), resultSet.getString("status"),
                    resultSet.getString("turn"));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateStatus(StatusDto statusDto, int gameId) {
        String sql = "update game set status = ? where id = ?";
        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, statusDto.getStatus());
            statement.setInt(2, gameId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
