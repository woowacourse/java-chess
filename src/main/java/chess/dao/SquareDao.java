package chess.dao;

import chess.model.piece.Color;
import chess.model.piece.Piece;
import chess.model.piece.PieceType;
import chess.model.square.File;
import chess.model.square.Rank;
import chess.model.square.Square;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SquareDao {

    private final ConnectionManager connectionManager;

    public SquareDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public Square save(Square square) {
        return connectionManager.executeQuery(connection -> {
            final String sql = "insert into square (square_file, square_rank, board_id) values (?, ?, ?)";
            final PreparedStatement preparedStatement = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, square.getFile().value());
            preparedStatement.setInt(2, square.getRank().value());
            preparedStatement.setInt(3, square.getBoardId());
            preparedStatement.executeUpdate();
            final ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (!generatedKeys.next()) {
                throw new SQLException("실행에 오류가 생겼습니다.");
            }
            return new Square(generatedKeys.getInt(1), square.getFile(), square.getRank(), square.getBoardId());
        });
    }

    public Square getBySquareAndBoardId(Square square, int boardId) {
        return connectionManager.executeQuery(connection -> {
            final ResultSet resultSet = findSquare(square.getFile(), square.getRank(), boardId, connection);
            return new Square(
                    resultSet.getInt("id"),
                    File.findFile(resultSet.getInt("square_file")),
                    Rank.findRank(resultSet.getInt("square_rank")),
                    resultSet.getInt("board_id")
            );
        });
    }

    private ResultSet findSquare(File file, Rank rank, int boardId, Connection connection) throws SQLException {
        final String sql = "select s.id, s.square_file, s.square_rank, s.board_id " +
                "from square as s " +
                "where s.square_file=? and s.square_rank=? and s.board_id=?";
        final PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, file.value());
        preparedStatement.setInt(2, rank.value());
        preparedStatement.setInt(3, boardId);
        final ResultSet resultSet = preparedStatement.executeQuery();
        if (!resultSet.next()) {
            throw new IllegalArgumentException("위치가 존재하지 않습니다.");
        }
        return resultSet;
    }

    public int saveAllSquare(int boardId) {
        return connectionManager.executeQuery(connection -> {
            final String sql = "insert into square (square_file, square_rank, board_id) values (?, ?, ?)";
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (File file : File.values()) {
                for (Rank rank : Rank.values()) {
                    preparedStatement.setInt(1, file.value());
                    preparedStatement.setInt(2, rank.value());
                    preparedStatement.setInt(3, boardId);
                    preparedStatement.addBatch();
                    preparedStatement.clearParameters();
                }
            }
            return preparedStatement.executeBatch().length;
        });
    }

    public int getSquareIdBySquare(Square square, int boardId) {
        return getBySquareAndBoardId(square, boardId).getId();
    }

    public Map<Square, Piece> findAllSquaresAndPieces(int boardId) {
        return connectionManager.executeQuery(connection -> {
            final String sql = "select po.id as po_id, po.square_file, po.square_rank, po.board_id, " +
                    "pi.id as pi_id, pi.type, pi.color, pi.square_id " +
                    "from square po " +
                    "inner join piece pi on po.id = pi.square_id " +
                    "where board_id=?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, boardId);
            final ResultSet resultSet = preparedStatement.executeQuery();
            Map<Square, Piece> existPiecesWithSquare = new HashMap<>();
            while (resultSet.next()) {
                existPiecesWithSquare.put(makeSquare(resultSet), makePiece(resultSet));
            }
            return existPiecesWithSquare;
        });
    }

    private Piece makePiece(ResultSet resultSet) throws SQLException {
        return PieceType.getPiece(resultSet.getString("type"),
                resultSet.getInt("pi_id"),
                Color.findColor(resultSet.getString("color")),
                resultSet.getInt("square_id"));
    }

    private Square makeSquare(ResultSet resultSet) throws SQLException {
        return new Square(resultSet.getInt("po_id"),
                File.findFile(resultSet.getInt("square_file")),
                Rank.findRank(resultSet.getInt("square_rank")),
                resultSet.getInt("board_id"));
    }

    public List<Square> getPaths(List<Square> squares, int roomId) {
        List<Square> realSquares = new ArrayList<>();
        for (Square square : squares) {
            realSquares.add(getBySquareAndBoardId(square, roomId));
        }
        return realSquares;
    }
}
