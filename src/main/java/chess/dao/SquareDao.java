package chess.dao;

import chess.model.square.File;
import chess.model.square.Rank;
import chess.model.square.Square;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SquareDao {

    private final ConnectionManager connectionManager;

    public SquareDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public Square save(Square square) {
        return connectionManager.executeQuery(connection -> {
            final String sql = "insert into square (square_file, square_rank, board_id) values (?, ?, ?)";
            final PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
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

    public Square getBySquare(Square square) {
        return connectionManager.executeQuery(connection -> {
            final ResultSet resultSet = findSquare(square.getFile(), square.getRank(), square.getBoardId(), connection);
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

    public int getSquareIdBySquare(Square square) {
        return getBySquare(square).getId();
    }

//    public List<Position> getPaths(List<Position> positions, int roomId) {
//        List<Position> realPositions = new ArrayList<>();
//        for (Position position : positions) {
//            realPositions.add(getByColumnAndRowAndBoardId(position.getColumn(), position.getRow(), roomId));
//        }
//        return realPositions;
//    }

//    public Map<Position, Piece> findAllPositionsAndPieces(int boardId) {
//        return connectionManager.executeQuery(connection -> {
//            final String sql = "select po.id as po_id, po.position_column, po.position_row, po.board_id, " +
//                    "pi.id as pi_id, pi.type, pi.color, pi.position_id " +
//                    "from position po " +
//                    "inner join piece pi on po.id = pi.position_id " +
//                    "where board_id=?";
//
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, boardId);
//            final ResultSet resultSet = preparedStatement.executeQuery();
//            Map<Position, Piece> existPiecesWithPosition = new HashMap<>();
//            while (resultSet.next()) {
//                existPiecesWithPosition.put(makePosition(resultSet), makePiece(resultSet));
//            }
//            return existPiecesWithPosition;
//        });
//    }
}
