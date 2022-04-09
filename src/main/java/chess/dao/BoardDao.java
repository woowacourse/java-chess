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

public class BoardDao {

    private final ConnectionManager connectionManager;

    public BoardDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public Board save(Board board) {
        return connectionManager.executeQuery(connection -> {
            final String sql = "INSERT INTO board (room_title) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, board.getTitle());
            preparedStatement.executeUpdate();
            final MemberDao memberDao = new MemberDao(new ConnectionManager());
            final ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (!generatedKeys.next()) {
                throw new IllegalArgumentException("보드가 없습니다. 방 제목: " + board.getTitle());
            }
            return new Board(
                    generatedKeys.getInt(1),
                    board.getTitle(),
                    memberDao.getAllByBoardId(generatedKeys.getInt(1)));
        });
    }

    public int deleteAll() {
        return connectionManager.executeQuery(connection -> {
            String sql = "DELETE FROM board";
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            return preparedStatement.executeUpdate();
        });
    }

    public int deleteById(int id) {
        return connectionManager.executeQuery(connection -> {
            String sql = "DELETE FROM board WHERE id=?";
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate();
        });
    }

    public Board getById(int id) {
        return connectionManager.executeQuery(connection -> {
            final String sql = "SELETE * FROM board WHERE id=?";
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();
            final MemberDao memberDao = new MemberDao(new ConnectionManager());
            if (!resultSet.next()) {
                throw new IllegalArgumentException("그런 보드 없습니다. 방 id: " + id);
            }
            return new Board(
                    resultSet.getInt("id"),
                    resultSet.getString("room_title"),
                    memberDao.getAllByBoardId(resultSet.getInt("id"))
            );
        });
    }

    public List<Board> findAll() {
        return connectionManager.executeQuery(connection -> {
            final String sql = "SELECT * FROM board";
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            final ResultSet resultSet = preparedStatement.executeQuery();
            final MemberDao memberDao = new MemberDao(connectionManager);
            List<Board> boards = new ArrayList<>();
            while (resultSet.next()) {
                boards.add(new Board(
                        resultSet.getInt("id"),
                        resultSet.getString("room_title"),
                        memberDao.getAllByBoardId(resultSet.getInt("id")))
                );
            }
            return boards;
        });
    }

    public Board init(Board board, Map<Square, Piece> startingPieces) {
        return connectionManager.executeQuery(connection -> {
            final Board savedBoard = save(board);
            final SquareDao squareDao = new SquareDao(connectionManager);
            final PieceDao pieceDao = new PieceDao(connectionManager);
            final MemberDao memberDao = new MemberDao(connectionManager);
            squareDao.saveAllSquare(savedBoard.getId());
            for (Square square : startingPieces.keySet()) {
                int squareId = squareDao.getSquareIdBySquare(square, savedBoard.getId());
                pieceDao.save(startingPieces.get(square), squareId);
            }
            memberDao.saveAll(board.getMembers(), savedBoard.getId());
            return savedBoard;
        });
    }
}
