package chess.dao;

import chess.service.dto.ChessGameDto;
import chess.service.dto.StatusDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class GameDao {

    public void update(ChessGameDto dto) {
        String sql = "update game set status = ?, turn = ? where id = ?";
        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            JdbcUtil.setStringsToStatement(statement,
                    Map.of(1, dto.getStatus(), 2, dto.getTurn()));
            statement.setInt(3, dto.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ChessGameDto findById(int id) {
        String sql = "select name, status, turn from game where id = ?";
        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            return getChessGameDto(statement.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private ChessGameDto getChessGameDto(ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return null;
        }
        String name = resultSet.getString("name");
        String status = resultSet.getString("status");
        String turn = resultSet.getString("turn");
        return new ChessGameDto(name, status, turn);
    }

    public void updateStatus(StatusDto statusDto, int id) {
        String sql = "update game set status = ? where id = ?";
        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            JdbcUtil.setStringsToStatement(statement, Map.of(1, statusDto.getStatus()));
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
