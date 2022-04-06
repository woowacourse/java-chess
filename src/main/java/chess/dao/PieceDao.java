package chess.dao;

import chess.model.piece.Color;
import chess.model.piece.Piece;
import chess.model.piece.PieceType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PieceDao {

    private final ConnectionManager connectionManager;

    public PieceDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public Piece save(Piece piece) {
        return connectionManager.executeQuery(connection -> {
            final String sql = "insert into piece (type, color, square_id) values (?, ?, ?)";
            final PreparedStatement preparedStatement = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, piece.name());
            preparedStatement.setString(2, piece.color().name());
            preparedStatement.setInt(3, piece.getSquareId());
            preparedStatement.executeUpdate();
            final ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (!resultSet.next()) {
                throw new IllegalArgumentException("피스를 찾지 못했습니다.");
            }
            return PieceType.getPiece(
                    piece.name(),
                    resultSet.getInt(1),
                    piece.color(),
                    piece.getSquareId());
        });
    }

    public Piece findBySquareId(int squareId) {
        return connectionManager.executeQuery(connection -> {
            final String sql = "select * from piece where Square_id=?";
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, squareId);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new IllegalArgumentException("기물을 찾지 못했습니다.");

            }
            return PieceType.getPiece(
                    resultSet.getString("type"),
                    resultSet.getInt("id"),
                    Color.findColor(resultSet.getString("color")),
                    resultSet.getInt("Square_id"));
        });
    }

    public int updatePieceSquareId(int originSquareId, int newSquareId) {
        return connectionManager.executeQuery(connection -> {
            final String sql = "update piece set square_id=? where square_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, newSquareId);
            preparedStatement.setInt(2, originSquareId);
            return preparedStatement.executeUpdate();
        });
    }

    public int deletePieceBySquareId(int squareId) {
        return connectionManager.executeQuery(connection -> {
            final String sql = "delete from piece where square_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, squareId);
            return preparedStatement.executeUpdate();
        });
    }

    public List<Piece> getAllPiecesByBoardId(int boardId) {
        return connectionManager.executeQuery(connection -> {
            final String sql =
                    "select pi.id, pi.type, pi.color, pi.square_id from piece pi join square po on pi.square_id=po.id "
                            +
                            "join board nb on po.board_id=nb.id where nb.id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, boardId);
            final ResultSet resultSet = preparedStatement.executeQuery();
            List<Piece> pieces = new ArrayList<>();
            while (resultSet.next()) {
                pieces.add(
                        PieceType.getPiece(resultSet.getString("type"),
                                resultSet.getInt("id"),
                                Color.findColor(resultSet.getString("color")),
                                resultSet.getInt("square_id")
                        ));
            }
            return pieces;
        });
    }
}
