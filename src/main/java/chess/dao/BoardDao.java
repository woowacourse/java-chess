package chess.dao;

import chess.dao.exception.DeleteQueryException;
import chess.dao.exception.InsertQueryException;
import chess.dao.exception.SelectQueryException;
import chess.dao.exception.UpdateQueryException;
import chess.database.DBConnection;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.dto.PieceDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardDao {
    final Connection connection = DBConnection.getConnection();

    public List<PieceDto> findAll(Long roomId) {
        final String sql = "select * from board where room_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, roomId);
            ResultSet resultSet = statement.executeQuery();
            List<PieceDto> pieces = new ArrayList<>();
            while (resultSet.next()) {
                String position = resultSet.getString("position");
                String symbol = resultSet.getString("symbol");
                pieces.add(new PieceDto(position, symbol));
            }
            return pieces;
        } catch (SQLException e) {
            throw new SelectQueryException();
        }
    }

    public void saveAll(Map<Position, Piece> board) {
        final String sql = "insert into board (position, symbol, room_id) values(?, ?, 1)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            for (Position position : board.keySet()) {
                Piece piece = board.get(position);
                String positionToString = position.getPositionToString();
                String symbol = piece.getSymbol();
                statement.setString(1, positionToString);
                statement.setString(2, symbol);
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            throw new InsertQueryException();
        }
    }

    public void delete(int roomId) {
        final String sql = "delete from board where room_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, roomId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DeleteQueryException();
        }
    }

    public void updatePosition(String position, String symbol) {
        final String sql = "update board set symbol = ? where position = ?";
        try {
            PreparedStatement statement1 = connection.prepareStatement(sql);
            statement1.setString(1, symbol);
            statement1.setString(2, position);
            statement1.executeUpdate();
        } catch (SQLException e) {
            throw new UpdateQueryException();
        }
    }
}
