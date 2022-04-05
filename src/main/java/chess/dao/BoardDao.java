package chess.dao;

import chess.database.DBConnection;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class BoardDao {
    final Connection connection = DBConnection.getConnection();

    public Map<String, String> findAll(String roomId) {
        final String sql = "select * from board where room_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, roomId);
            ResultSet resultSet = statement.executeQuery();
            Map<String, String> board = new HashMap<>();
            while (resultSet.next()) {
                String position = resultSet.getString("position");
                String symbol = resultSet.getString("symbol");
                board.put(position, symbol);
            }
            return board;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
            e.printStackTrace();
        }
    }

    public void delete() {
        final String sql = "delete from board where room_id = 1";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePosition(String position, String symbol) {
        final String sql1 = "update board set symbol = ? where position = ?";
        try {
            PreparedStatement statement1 = connection.prepareStatement(sql1);
            statement1.setString(1, symbol);
            statement1.setString(2, position);
            statement1.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
