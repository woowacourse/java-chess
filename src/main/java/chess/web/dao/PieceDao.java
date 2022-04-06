package chess.web.dao;

import chess.domain.board.Color;
import chess.domain.board.Piece;
import chess.domain.board.PieceFactory;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class PieceDao {

    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    public Connection getConnection() {

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new IllegalStateException("connection 획득 실패");
        }
        return connection;
    }

    public void savePieces(Map<Position, Piece> board) {
        String sql = "insert into piece (color, piece_type, position_column, position_row) values (?, ?, ?, ?)";

        Connection connection = getConnection();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            for (Position position : board.keySet()) {
                Piece piece = board.get(position);
                statement.setString(1, piece.getColor().name());
                statement.setString(2, piece.getType());
                statement.setString(3, position.getColumn().name());
                statement.setString(4, position.getRow().name());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            throw new IllegalStateException("sql 실행 실패", e.getCause());
        }
    }

    public void removeAll() {
        String sql = "delete from piece";

        Connection connection = getConnection();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("sql 실행 실패", e.getCause());
        }
    }

    public Map<Position, Piece> findAll() {
        String sql = "select color, piece_type, position_column, position_row from piece";
        Map<Position, Piece> result = new HashMap<>();

        Connection connection = getConnection();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeQuery();
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                String color = resultSet.getString("color");
                String type = resultSet.getString("piece_type");
                String column = resultSet.getString("position_column");
                String row = resultSet.getString("position_row");

                Piece piece = PieceFactory.generate(type, Color.valueOf(color));
                Position position = new Position(Column.valueOf(column), Row.valueOf(row));
                result.put(position, piece);
            }
        } catch (SQLException e) {
            throw new IllegalStateException("sql 실행 실패", e.getCause());
        }
        return result;
    }
}
