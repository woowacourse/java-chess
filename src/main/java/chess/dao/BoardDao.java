package chess.dao;

import chess.dto.CreatePieceDto;
import chess.dto.DeletePieceDto;
import chess.dto.UpdatePiecePositionDto;
import chess.exception.DatabaseException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BoardDao extends Dao {
    private static final String TABLE_NAME = "board";

    public void createPiece(CreatePieceDto createPieceDto) {
        String query = String.format(
                "INSERT INTO %s(game_id, x_axis, y_axis, piece_type, piece_color) VALUES(?, ?, ?, ?, ?)",
                TABLE_NAME);

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, createPieceDto.getGameId());
            preparedStatement.setString(2, createPieceDto.getXAxisValueAsString());
            preparedStatement.setString(3, createPieceDto.getYAxisValueAsString());
            preparedStatement.setString(4, createPieceDto.getPieceTypeName());
            preparedStatement.setString(5, createPieceDto.getPieceColorName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException();
        }
    }

    public void deletePiece(DeletePieceDto deletePieceDto) {
        String query = String.format(
                "DELETE FROM %s WHERE game_id = ? AND x_axis = ? AND y_axis = ?", TABLE_NAME);

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, deletePieceDto.getGameId());
            preparedStatement.setString(2, deletePieceDto.getXAxisValueAsString());
            preparedStatement.setString(3, deletePieceDto.getYAxisValueAsString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException();
        }
    }

    public void updatePiecePosition(UpdatePiecePositionDto updatePiecePositionDto) {
        String query = String.format(
                "UPDATE %s SET x_axis = ?, y_axis = ? WHERE x_axis = ? AND y_axis = ?", TABLE_NAME);

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, updatePiecePositionDto.getToXAxisValueAsString());
            preparedStatement.setString(2, updatePiecePositionDto.getToYAxisValueAsString());
            preparedStatement.setString(3, updatePiecePositionDto.getFromXAxisValueAsString());
            preparedStatement.setString(4, updatePiecePositionDto.getFromYAxisValueAsString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException();
        }
    }
}
