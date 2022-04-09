package chess.dao;

import chess.service.dto.BoardDto;
import chess.service.dto.PieceWithSquareDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardDao {

    public void initBoard(String gameName) {
        String sql = "insert into board\n"
                + "select init.piece_type, init.piece_color, init.square, ? from init_board as init\n"
                + "on duplicate key update piece_type = init.piece_type, piece_color = init.piece_color";
        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, gameName);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public BoardDto getBoardByGameId(String gameName) {
        String sql = "select piece_type, piece_color, square from board where game_name = ?";
        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, gameName);
            ResultSet resultSet = statement.executeQuery();
            return getBoardDtoFromResultSet(resultSet);
        } catch (SQLException e) {
            return null;
        }
    }

    private BoardDto getBoardDtoFromResultSet(ResultSet resultSet) throws SQLException {
        List<PieceWithSquareDto> pieces = new ArrayList<>();
        while (resultSet.next()) {
            String color = resultSet.getString("piece_color");
            String type = resultSet.getString("piece_type");
            String square = resultSet.getString("square");
            pieces.add(new PieceWithSquareDto(square, type, color));
        }
        return new BoardDto(pieces);
    }

    public void remove(String gameName) {
        String sql = "delete from board where game_name = ?";
        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, gameName);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(PieceWithSquareDto piece) {
        String sql = "update board set piece_type = ?, piece_color = ? where square = ?";
        try (Connection connection = JdbcUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {
            JdbcUtil.setStringsToStatement(statement,
                    Map.of(1, piece.getType(), 2, piece.getColor(), 3, piece.getSquare()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
