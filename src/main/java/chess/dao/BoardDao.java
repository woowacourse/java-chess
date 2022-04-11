package chess.dao;

import static chess.dao.DBConnector.getConnection;

import chess.dto.BoardDto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDao {

    public List<BoardDto> findByGameId(int gameId) {
        final String sql = "select symbol, team, position from board where game_id = (?)";
        final List<BoardDto> boardDtos = new ArrayList<>();

        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, gameId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    boardDtos.add(new BoardDto(
                            resultSet.getString("symbol"),
                            resultSet.getString("team"),
                            resultSet.getString("position")));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return boardDtos;
    }

    public void save(List<BoardDto> boardDtos, int gameId) {
        final String sql = "insert into board (symbol, team, position, game_id) values (?, ?, ?, ?)";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            for (BoardDto boardDto : boardDtos) {
                statement.setString(1, boardDto.getSymbol());
                statement.setString(2, boardDto.getTeam());
                statement.setString(3, boardDto.getPosition());
                statement.setInt(4, gameId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void update(BoardDto boardDto, int gameId) {
        final String sql = "update board set symbol = (?), team = (?) where position = (?) and game_id = (?)";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, boardDto.getSymbol());
            statement.setString(2, boardDto.getTeam());
            statement.setString(3, boardDto.getPosition());
            statement.setInt(4, gameId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
