package chess.dao;

import chess.dto.BoardDto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDao {

    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public List<BoardDto> findAll() {
        final String sql = "select position, symbol, color from board";
        final List<BoardDto> boardDtos = new ArrayList<>();

        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    boardDtos.add(new BoardDto(
                            resultSet.getString("position"),
                            resultSet.getString("symbol"),
                            resultSet.getString("color")));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return boardDtos;
    }

    public void save(final List<BoardDto> boardDtos, final int gameId) {
        final String sql = "insert into board (position, symbol, color, game_id) values (?, ?, ?, ?)";

        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            for (BoardDto boardDto : boardDtos) {
                statement.setString(1, boardDto.getPosition());
                statement.setString(2, boardDto.getSymbol());
                statement.setString(3, boardDto.getColor());
                statement.setInt(4, gameId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void update(final BoardDto boardDto) {
        final String sql = "update board set symbol = (?), color = (?) where position = (?)";

        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, boardDto.getSymbol());
            statement.setString(2, boardDto.getColor());
            statement.setString(3, boardDto.getPosition());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
