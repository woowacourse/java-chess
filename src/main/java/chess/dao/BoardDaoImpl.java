package chess.dao;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import chess.domain.position.XAxis;
import chess.domain.position.YAxis;
import chess.dto.request.CreatePieceDto;
import chess.dto.request.DeletePieceDto;
import chess.dto.request.UpdatePiecePositionDto;
import chess.dto.response.BoardDto;
import chess.exception.DatabaseException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class BoardDaoImpl extends Dao implements BoardDao {
    private static final String TABLE_NAME = "board";

    public BoardDto getBoard(String gameId) {
        String query = String.format("SELECT x_axis, y_axis, piece_type, piece_color FROM %s WHERE game_id = ?",
                TABLE_NAME);

        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, gameId);
            ResultSet resultSet = preparedStatement.executeQuery();

            Map<Position, Piece> boardValue = new HashMap<>();
            while (resultSet.next()) {
                XAxis xAxis = XAxis.getByValue(resultSet.getString("x_axis"));
                YAxis yAxis = YAxis.getByValue(resultSet.getString("y_axis"));
                PieceType pieceType = PieceType.valueOf(resultSet.getString("piece_type"));
                PieceColor pieceColor = PieceColor.valueOf(resultSet.getString("piece_color"));

                boardValue.put(Position.of(xAxis, yAxis), new Piece(pieceType, pieceColor));
            }

            return BoardDto.from(boardValue);
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public void createPiece(CreatePieceDto createPieceDto) {
        String query = String.format(
                "INSERT INTO %s(game_id, x_axis, y_axis, piece_type, piece_color) VALUES(?, ?, ?, ?, ?)",
                TABLE_NAME);

        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, createPieceDto.getGameId());
            preparedStatement.setString(2, createPieceDto.getXAxisValueAsString());
            preparedStatement.setString(3, createPieceDto.getYAxisValueAsString());
            preparedStatement.setString(4, createPieceDto.getPieceTypeName());
            preparedStatement.setString(5, createPieceDto.getPieceColorName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public void deletePiece(DeletePieceDto deletePieceDto) {
        String query = String.format(
                "DELETE FROM %s WHERE game_id = ? AND x_axis = ? AND y_axis = ?", TABLE_NAME);

        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, deletePieceDto.getGameId());
            preparedStatement.setString(2, deletePieceDto.getXAxisValueAsString());
            preparedStatement.setString(3, deletePieceDto.getYAxisValueAsString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public void updatePiecePosition(UpdatePiecePositionDto updatePiecePositionDto) {
        String query = String.format(
                "UPDATE %s SET x_axis = ?, y_axis = ? WHERE x_axis = ? AND y_axis = ? AND game_id = ?", TABLE_NAME);

        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, updatePiecePositionDto.getToXAxisValueAsString());
            preparedStatement.setString(2, updatePiecePositionDto.getToYAxisValueAsString());
            preparedStatement.setString(3, updatePiecePositionDto.getFromXAxisValueAsString());
            preparedStatement.setString(4, updatePiecePositionDto.getFromYAxisValueAsString());
            preparedStatement.setString(5, updatePiecePositionDto.getGameId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
