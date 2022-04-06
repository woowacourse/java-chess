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
        final Connection connection = getConnection();
        final String sql = "select position, symbol, color from board";

        final List<BoardDto> chessDtos = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                chessDtos.add(
                        new BoardDto(
                                resultSet.getString("position"),
                                resultSet.getString("symbol"),
                                resultSet.getString("color")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chessDtos;
    }

    public void save(final List<BoardDto> boardDtos, final int GameId) {
        final Connection connection = getConnection();
        final String sql = "insert into board (position, symbol, color, game_id) values (?, ?, ?, ?)";

        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            for (BoardDto boardDto : boardDtos) {
                statement.setString(1, boardDto.getPosition());
                statement.setString(2, boardDto.getSymbol());
                statement.setString(3, boardDto.getColor());
                statement.setInt(4, GameId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(final BoardDto boardDto) {
        final Connection connection = getConnection();
        final String sql = "update board set symbol = (?), color = (?) where position = (?)";

        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, boardDto.getSymbol());
            statement.setString(2, boardDto.getColor());
            statement.setString(3, boardDto.getPosition());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
