package chess.dao;

import chess.service.BoardDto;
import chess.service.PieceDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class BoardDao {

    public void initBoard(BoardDto boardDto, int gameId) {
        for (Entry<String, PieceDto> entry : boardDto.getPieces().entrySet()) {
            insert(entry.getValue(), entry.getKey(), gameId);
        }
    }

    public void insert(PieceDto pieceDto, String square, int gameId) {
        String sql = "replace into board (piece_type, piece_color, square, game_id)\n"
                + "values (?, ?, ?, ?)";
        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, pieceDto.getType());
            statement.setString(2, pieceDto.getColor());
            statement.setString(3, square);
            statement.setInt(4, gameId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void move(String squareFrom, String squareTo, int gameId) {
        String moveSql = "update board set piece_type = (select a.piece_type from\n"
                + "            (select piece_type from board where square = ? and game_id = ?) a)\n"
                + "            , piece_color = (select b.piece_color from\n"
                + "            (select piece_color from board where square = ? and game_id = ?) b)\n"
                + "            where square = ? and game_id = ?";
        String removeSql = "update board set piece_color = 'nothing', piece_type = 'empty' "
                + "where square = ? and game_id = ?;";
        try(Connection connection = JdbcUtil.getConnection();
        PreparedStatement moveStatement = connection.prepareStatement(moveSql);
        PreparedStatement removeStatement = connection.prepareStatement(removeSql)) {
            moveStatement.setString(1, squareFrom);
            moveStatement.setInt(2, gameId);
            moveStatement.setString(3, squareFrom);
            moveStatement.setInt(4, gameId);
            moveStatement.setString(5, squareTo);
            moveStatement.setInt(6, gameId);
            removeStatement.setString(1, squareFrom);
            removeStatement.setInt(2, gameId);
            moveStatement.executeUpdate();
            removeStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public BoardDto getBoardByGameId(int gameId) {
        String sql = "select piece_type, piece_color, square from board where game_id = ?";
        try(Connection connection = JdbcUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, gameId);
            ResultSet resultSet = statement.executeQuery();
            return getBoardDtoFromResultSet(resultSet);
        } catch (SQLException e) {
            return null;
        }
    }

    private BoardDto getBoardDtoFromResultSet(ResultSet resultSet) throws SQLException{
        Map<String, PieceDto> pieces = new HashMap<>();
        while (resultSet.next()) {
            String color = resultSet.getString("piece_color");
            String type = resultSet.getString("piece_type");
            String square = resultSet.getString("square");
            pieces.put(square, new PieceDto(type, color));
        }
        return new BoardDto(pieces);
    }
}
