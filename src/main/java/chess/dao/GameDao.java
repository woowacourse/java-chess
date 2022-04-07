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
        String sql = "update game set status = ?, turn = ? where name = ?";
        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            JdbcUtil.setStringsToStatement(statement,
                    Map.of(1, dto.getStatus(), 2, dto.getTurn(), 3, dto.getName()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ChessGameDto findByName(String name) {
        String sql = "select name, status, turn from game where name = ?";
        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
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

    public void updateStatus(StatusDto statusDto, String name) {
        String sql = "update game set status = ? where name = ?";
        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            JdbcUtil.setStringsToStatement(statement, Map.of(1, statusDto.getStatus(), 2, name));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
