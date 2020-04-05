package chess.repository;

import chess.domain.board.Position;
import chess.domain.piece.ChessPiece;
import chess.domain.piece.GamePiece;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class PositionDAO {

    private final RepositoryUtil repositoryUtil;

    public PositionDAO(RepositoryUtil repositoryUtil) {
        this.repositoryUtil = repositoryUtil;
    }

    public void addPosition(int boardId, Position position) throws SQLException {
        String query = "INSERT INTO position (board_id, position_name) VALUES (?, ?)";
        repositoryUtil.executeUpdate(query, Integer.toString(boardId), position.getName());
    }

    public Position findPositionByBoardIdAndName(int boardId, String name) throws SQLException {
        ResultSet resultSet = findByBoardIdAndName(boardId, name);
        if (!resultSet.next()) {
            throw new IllegalArgumentException(String.format("Board(id: %d)에 포지션 %s가 존재하지 않습니다.", boardId, name));
        }
        String founded = resultSet.getString("position_name");
        return Position.from(founded);
    }

    public int findIdByBoardIdAndName(int boardId, String name) throws SQLException {
        ResultSet resultSet = findByBoardIdAndName(boardId, name);
        if (!resultSet.next()) {
            throw new IllegalArgumentException(String.format("Board(id: %d)에 포지션 %s가 존재하지 않습니다.", boardId, name));
        }
        return resultSet.getInt("id");
    }

    public int insertIfNotExist(int boardId, Position position) throws SQLException {
        ResultSet resultSet = findByBoardIdAndName(boardId, position.getName());
        if (!resultSet.next()) {
            addPosition(boardId, position);
        }
        return findIdByBoardIdAndName(boardId, position.getName());
    }

    private ResultSet findByBoardIdAndName(int boardId, String name) throws SQLException {
        String query = "SELECT * FROM position WHERE board_id = ? AND position_name = ?";
        return repositoryUtil.executeQuery(query, Integer.toString(boardId), name);
    }

    public void updateByBoardIdAndName(int boardId, Position originalPosition, Position modifiedPosition) throws SQLException {
        String query = "UPDATE position SET position_name = ? WHERE board_id = ? AND position_name = ?";
        repositoryUtil.executeUpdate(query, modifiedPosition.getName(), Integer.toString(boardId), originalPosition.getName());
    }

    public void deleteByBoardIdAndName(int boardId, Position position) throws SQLException {
        String query = "DELETE FROM position WHERE board_id = ? AND position_name = ?";
        repositoryUtil.executeUpdate(query, Integer.toString(boardId), position.getName());
    }

    public Map<Position, GamePiece> findBoardContentByBoardId(int boardId) throws SQLException {
        Map<Position, GamePiece> boardContent = new HashMap<>();

        String query = "SELECT position.position_name, piece.piece_name FROM position, piece " +
                "WHERE position.board_id = ? AND position.id = piece.position_id";
        ResultSet resultSet = repositoryUtil.executeQuery(query, Integer.toString(boardId));

        while (resultSet.next()) {
            String position = resultSet.getString("position_name");
            String gamePiece = resultSet.getString("piece_name");
            boardContent.put(Position.from(position), ChessPiece.findGamePieceBy(gamePiece));
        }

        return boardContent;
    }
}
