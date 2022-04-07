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

    public void initBoard(BoardDto boardDto, String gameName) {
        for (Entry<String, PieceDto> entry : boardDto.getPieces().entrySet()) {
            insert(entry.getValue(), entry.getKey(), gameName);
        }
    }

    public void insert(PieceDto pieceDto, String square, String gameName) {
        String sql = "insert into board (piece_type, piece_color, square, game_name)\n"
                + "values (?, ?, ?, ?) on duplicate key update piece_type = ?, piece_color =?";
        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, pieceDto.getType());
            statement.setString(2, pieceDto.getColor());
            statement.setString(3, square);
            statement.setString(4, gameName);
            statement.setString(5, pieceDto.getType());
            statement.setString(6, pieceDto.getColor());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void move(String squareFrom, String squareTo, String gameName) {
        String moveSql = "update board set piece_type = (select a.piece_type from\n"
                + "            (select piece_type from board where square = ? and game_name = ?) a)\n"
                + "            , piece_color = (select b.piece_color from\n"
                + "            (select piece_color from board where square = ? and game_name = ?) b)\n"
                + "            where square = ? and game_name = ?";
        String removeSql = "update board set piece_color = 'nothing', piece_type = 'empty' "
                + "where square = ? and game_name = ?;";
        try(Connection connection = JdbcUtil.getConnection();
        PreparedStatement moveStatement = connection.prepareStatement(moveSql);
        PreparedStatement removeStatement = connection.prepareStatement(removeSql)) {
            moveStatement.setString(1, squareFrom);
            moveStatement.setString(2, gameName);
            moveStatement.setString(3, squareFrom);
            moveStatement.setString(4, gameName);
            moveStatement.setString(5, squareTo);
            moveStatement.setString(6, gameName);
            removeStatement.setString(1, squareFrom);
            removeStatement.setString(2, gameName);
            moveStatement.executeUpdate();
            removeStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public BoardDto getBoardByGameId(String gameName) {
        String sql = "select piece_type, piece_color, square from board where game_name = ?";
        try(Connection connection = JdbcUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, gameName);
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
}
