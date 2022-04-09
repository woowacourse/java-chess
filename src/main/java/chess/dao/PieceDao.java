package chess.dao;

import chess.model.piece.Color;
import chess.model.piece.Piece;
import chess.model.piece.PieceType;
import chess.model.square.File;
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

    public Piece save(Piece piece, int squareId) {
        return connectionManager.executeQuery(connection -> {
            final String sql = "INSERT INTO piece (type, color, square_id) VALUES(?, ?, ?)";
            final PreparedStatement preparedStatement = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, piece.name());
            preparedStatement.setString(2, piece.color().name());
            preparedStatement.setInt(3, squareId);
            preparedStatement.executeUpdate();
            final ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (!resultSet.next()) {
                throw new IllegalArgumentException("피스를 찾지 못했습니다." + piece.name());
            }
            return PieceType.getPiece(
                    piece.name(),
                    resultSet.getInt(1),
                    piece.color(),
                    squareId);
        });
    }

    public Piece findBySquareId(int squareId) {
        return connectionManager.executeQuery(connection -> {
            final String sql = "SELECT * FROM piece WHERE square_id=?";
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
                    resultSet.getInt("square_id"));
        });
    }

    public int updatePieceSquareId(int originSquareId, int newSquareId) {
        return connectionManager.executeQuery(connection -> {
            final String sql = "UPDATE piece SET square_id=? WHERE square_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, newSquareId);
            preparedStatement.setInt(2, originSquareId);
            return preparedStatement.executeUpdate();
        });
    }

    public int deletePieceBySquareId(int squareId) {
        return connectionManager.executeQuery(connection -> {
            final String sql = "DELETE FROM piece WHERE square_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, squareId);
            return preparedStatement.executeUpdate();
        });
    }

    public List<Piece> getAllPiecesByBoardId(int boardId) {
        return connectionManager.executeQuery(connection -> {
            final String sql =
                    "SELECT pi.id, pi.type, pi.color, pi.square_id FROM piece pi "
                            + "JOIN square po ON pi.square_id=po.id "
                            + "JOIN board nb ON po.board_id=nb.id WHERE nb.id=?";
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

    public int countPawnsOnSameColumn(int roomId, File column, Color color) {
        return connectionManager.executeQuery(connection -> {
            final String sql = "SELECT COUNT(*) AS total_count FROM piece pi " +
                    "JOIN square s ON pi.square_id = s.id " +
                    "WHERE s.square_file=? AND s.board_id=? AND pi.type='p' AND pi.color=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, column.value());
            preparedStatement.setInt(2, roomId);
            preparedStatement.setString(3, color.name());
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new IllegalArgumentException("쿼리가 잘못됐습니다.");
            }

            return resultSet.getInt("total_count");
        });
    }
}
