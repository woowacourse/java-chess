package chess.web.dao;

import chess.domain.board.Color;
import chess.domain.board.Piece;
import chess.domain.board.PieceFactory;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

public class PieceDao {

    private final JdbcContext jdbcContext = new JdbcContext();

    public void savePieces(Map<Position, Piece> board) {
        String sql = "insert into piece (color, piece_type, position_column, position_row) values (?, ?, ?, ?)";

        jdbcContext.executeBatch(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (Position position : board.keySet()) {
                Piece piece = board.get(position);
                preparedStatement.setString(1, piece.getColor().name());
                preparedStatement.setString(2, piece.getType());
                preparedStatement.setString(3, position.getColumn().name());
                preparedStatement.setString(4, position.getRow().name());
                preparedStatement.addBatch();
            }
            return preparedStatement;
        });
    }

    public Piece findPieceByPosition(Position position) {
        String sql = "select color, piece_type from piece where position_column = ? and position_row = ?";

        return jdbcContext.queryForObject(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, position.getColumn().name());
            preparedStatement.setString(2, position.getRow().name());
            return preparedStatement;
        }, resultSet -> {
            if (resultSet.next()) {
                return PieceFactory.generate(
                        resultSet.getString("piece_type"),
                        Color.valueOf(resultSet.getString("color")));
            }
            throw new IllegalStateException("해당 위치에 Piece가 존재하지 않습니다");
        });
    }

    public Map<Position, Piece> findAll() {
        String sql = "select color, piece_type, position_column, position_row from piece";
        return jdbcContext.queryForObject(connection -> connection.prepareStatement(sql), piecesMapper());
    }

    public void removeAll() {
        String sql = "delete from piece";
        jdbcContext.executeUpdate(connection -> connection.prepareStatement(sql));
    }

    private RowMapper<Map<Position, Piece>> piecesMapper() {
        return resultSet -> {
            Map<Position, Piece> result = new HashMap<>();
            while (resultSet.next()) {
                result.put(
                        new Position(
                                Column.valueOf(resultSet.getString("position_column")),
                                Row.valueOf(resultSet.getString("position_row"))),
                        PieceFactory.generate(
                                resultSet.getString("piece_type"),
                                Color.valueOf(resultSet.getString("color"))));
            }
            return result;
        };
    }

    public void deletePiece(Position position) {
        String sql = "delete from piece where position_column = ? and position_row = ?";

        jdbcContext.executeUpdate(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, position.getColumn().name());
            preparedStatement.setString(2, position.getRow().name());
            return preparedStatement;
        });
    }
}
