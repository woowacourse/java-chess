package chess.dao;

import chess.database.DBConnection;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardDao {
    private String position;
    private String symbol;

    public BoardDao(String position, String symbol) {
        this.position = position;
        this.symbol = symbol;
    }

    public void save(BoardDao pieceDao, String roomId) {
        final Connection connection = DBConnection.getConnection();
        final String sql = "insert into board (position, symbol, room_id) values(?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, pieceDao.position);
            statement.setString(2, pieceDao.symbol);
            statement.setString(3, roomId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public BoardDao findByPosition(String position) {
        final Connection connection = DBConnection.getConnection();
        final String sql = "select position, symbol from board  where position = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, position);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return new BoardDao(resultSet.getString("position"), resultSet.getString("symbol"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<BoardDao> findAll(String roomId) {
        final Connection connection = DBConnection.getConnection();
        final String sql = "select * from board where room_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, roomId);
            ResultSet resultSet = statement.executeQuery();
            List<BoardDao> pieces = new ArrayList<>();
            while(resultSet.next()) {
                String position = resultSet.getString("position");
                String symbol = resultSet.getString("symbol");
                pieces.add(new BoardDao(position, symbol));
            }
            return pieces;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getPosition() {
        return position;
    }

    public String getSymbol() {
        return symbol;
    }

    public void saveAll(Map<Position, Piece> board) {
        final Connection connection = DBConnection.getConnection();
        final String sql = "insert into board (position, symbol, room_id) values(?, ?, 1)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            for (Position position : board.keySet()) {
                Piece piece = board.get(position);
                Position piecePosition = piece.getPosition();
                String positionToString = piecePosition.getPositionToString();
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
        final Connection connection = DBConnection.getConnection();
        final String sql = "delete from board where room_id = 1";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            int i = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
