package chess.dao;

import chess.domain.game.Board;
import chess.domain.pieces.Color;
import chess.domain.pieces.Piece;
import chess.domain.position.Position;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            final String sql = "INSERT INTO board (room_title, turn) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, board.getRoomTitle());
            preparedStatement.setString(2, board.getTurn().name());
            preparedStatement.executeUpdate();
            final ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (!generatedKeys.next()) {
                throw new IllegalArgumentException("보드를 찾을 수 없습니다.");
            }
            return new Board(generatedKeys.getInt(1), board.getRoomTitle(), board.getTurn());
        });
    }

    public Board getById(int id) {
        return connectionManager.executeQuery(connection -> {
            final String sql = "SELECT * FROM board WHERE id=?";
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new IllegalArgumentException("보드를 찾을 수 없습니다.");
            }
            return makeBoard(resultSet, new MemberDao(connectionManager));
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
                boards.add(makeBoard(resultSet, new MemberDao(connectionManager)));
            }
            return boards;
        });
    }

    public Board init(Board board, Map<Position, Piece> initialize) {
        return connectionManager.executeQuery(connection -> {
            final Board savedBoard = save(board);
            final PositionDao positionDao = new PositionDao(connectionManager);
            final PieceDao pieceDao = new PieceDao(connectionManager);
            final MemberDao memberDao = new MemberDao(connectionManager);
            positionDao.saveAllPosition(savedBoard.getId());
            for (Position position : initialize.keySet()) {
                int lastPositionId = positionDao.getPositionIdByColumnAndRowAndBoardId(position.getColumn(), position.getRow(), savedBoard.getId());
                final Piece piece = initialize.get(position);
                pieceDao.save(new Piece(piece.getColor(), piece.getType(), lastPositionId));
            }
            memberDao.saveAll(board.getMembers(), savedBoard.getId());
            return savedBoard;
        });
    }

    public int deleteById(int id) {
        return connectionManager.executeQuery(connection -> {
            String sql = "DELETE FROM board where id=?";
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate();
        });
    }

    public void deleteAll() {
        connectionManager.executeQuery(connection -> {
            String sql = "DELETE FROM board";
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            return preparedStatement.executeUpdate();
        });
    }

    public void updateTurn(Color color, int boardId) {
        connectionManager.executeQuery(connection -> {
            String sql = "UPDATE board SET turn=? WHERE id=?";
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, color.name());
            preparedStatement.setInt(2, boardId);
            return preparedStatement.executeUpdate();
        });
    }

    private Board makeBoard(ResultSet resultSet, MemberDao memberDao) throws SQLException {
        return new Board(
                resultSet.getInt("id"),
                resultSet.getString("room_title"),
                Color.findColor(resultSet.getString("turn")),
                memberDao.getAllByBoardId(resultSet.getInt("id")));
    }
}
