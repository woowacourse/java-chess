package chess.dao;

import chess.service.dto.ChessGameDto;
import chess.service.dto.StatusDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameDao {

    public void update(ChessGameDto dto) {
        String sql = "update game set status = ?, turn = ? where id = ?";
        new StatementExecutor(JdbcUtil.getConnection(), sql)
                .setString(dto.getStatus())
                .setString(dto.getTurn())
                .setInt(dto.getId())
                .executeUpdate();
    }

    public ChessGameDto findById(int id) {
        String sql = "select id, name, status, turn from game where id = ?";
        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return getChessGameDto(resultSet);
        } catch (SQLException e) {
            throw new DaoException("체스 게임을 찾지 못했습니다.", e);
        }
    }

    private ChessGameDto getChessGameDto(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String status = resultSet.getString("status");
        String turn = resultSet.getString("turn");
        return new ChessGameDto(id, name, status, turn);
    }

    public void updateStatus(StatusDto statusDto, int id) {
        String sql = "update game set status = ? where id = ?";
        new StatementExecutor(JdbcUtil.getConnection(), sql)
                .setString(statusDto.getStatus())
                .setInt(id)
                .executeUpdate();
    }

    public GamesDto findAll() {
        String sql = "select id, name, status, turn from game";
        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            List<ChessGameDto> games = new ArrayList<>();
            while (resultSet.next()) {
                games.add(getChessGameDto(resultSet));
            }
            return new GamesDto(games);
        } catch (SQLException e) {
            throw new DaoException("체스 게임을 찾지 못했습니다.", e);
        }
    }

    public void createGame(String name) {
        String sql = "insert into game set name = ?";
        new StatementExecutor(JdbcUtil.getConnection(), sql)
                .setString(name)
                .executeUpdate();
    }
}
