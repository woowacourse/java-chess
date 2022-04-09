package chess.dao;

import chess.model.Board;
import chess.model.piece.Piece;
import chess.model.square.Square;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChessBoardDao implements BoardDao<Board> {

    private final ConnectionManager connectionManager;

    public ChessBoardDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public Board save(Board board) {
        return connectionManager.executeQuery(connection -> {
            final String sql = "INSERT INTO board (room_title) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, board.getTitle());
            preparedStatement.executeUpdate();
            final ChessMemberDao chessMemberDao = new ChessMemberDao(new ConnectionManager());
            final ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (!generatedKeys.next()) {
                throw new IllegalArgumentException("보드가 없습니다. 방 제목: " + board.getTitle());
            }
            return new Board(
                    generatedKeys.getInt(1),
                    board.getTitle(),
                    chessMemberDao.getAllByBoardId(generatedKeys.getInt(1)));
        });
    }

    @Override
    public int deleteAll() {
        return connectionManager.executeQuery(connection -> {
            String sql = "DELETE FROM board";
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            return preparedStatement.executeUpdate();
        });
    }

    @Override
    public int deleteById(int id) {
        return connectionManager.executeQuery(connection -> {
            String sql = "DELETE FROM board WHERE id=?";
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate();
        });
    }

    @Override
    public Board getById(int id) {
        return connectionManager.executeQuery(connection -> {
            final String sql = "SELECT * FROM board WHERE id=?";
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();
            final ChessMemberDao chessMemberDao = new ChessMemberDao(new ConnectionManager());
            if (!resultSet.next()) {
                throw new IllegalArgumentException("그런 보드 없습니다. 방 id: " + id);
            }
            return new Board(
                    resultSet.getInt("id"),
                    resultSet.getString("room_title"),
                    chessMemberDao.getAllByBoardId(resultSet.getInt("id"))
            );
        });
    }

    @Override
    public List<Board> findAll() {
        return connectionManager.executeQuery(connection -> {
            final String sql = "SELECT * FROM board";
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            final ResultSet resultSet = preparedStatement.executeQuery();
            final ChessMemberDao chessMemberDao = new ChessMemberDao(connectionManager);
            List<Board> boards = new ArrayList<>();
            while (resultSet.next()) {
                boards.add(new Board(
                        resultSet.getInt("id"),
                        resultSet.getString("room_title"),
                        chessMemberDao.getAllByBoardId(resultSet.getInt("id")))
                );
            }
            return boards;
        });
    }

    @Override
    public Board init(Board board, Map<Square, Piece> startingPieces) {
        return connectionManager.executeQuery(connection -> {
            final Board savedBoard = save(board);
            final ChessSquareDao chessSquareDao = new ChessSquareDao(connectionManager);
            final ChessPieceDao chessPieceDao = new ChessPieceDao(connectionManager);
            final ChessMemberDao chessMemberDao = new ChessMemberDao(connectionManager);
            chessSquareDao.saveAllSquare(savedBoard.getId());
            for (Square square : startingPieces.keySet()) {
                int squareId = chessSquareDao.getSquareIdBySquare(square, savedBoard.getId());
                chessPieceDao.save(startingPieces.get(square), squareId);
            }
            chessMemberDao.saveAll(board.getMembers(), savedBoard.getId());
            return savedBoard;
        });
    }
}
