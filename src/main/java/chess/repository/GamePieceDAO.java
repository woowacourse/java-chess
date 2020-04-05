package chess.repository;

import chess.domain.piece.ChessPiece;
import chess.domain.piece.GamePiece;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GamePieceDAO {

    private final RepositoryUtil repositoryUtil;

    public GamePieceDAO(RepositoryUtil repositoryUtil) {
        this.repositoryUtil = repositoryUtil;
    }

    public void addGamePiece(int positionId, GamePiece gamePiece) throws SQLException {
        String query = "INSERT INTO piece (position_id, piece_name) VALUES (?, ?)";
        repositoryUtil.executeUpdate(query, Integer.toString(positionId), gamePiece.getName());
    }

    public GamePiece findGamePieceByPositionId(int positionId) throws SQLException {
        ResultSet resultSet = findByPositionId(positionId);
        if (!resultSet.next()) {
            throw new IllegalArgumentException(String.format("포지션(id: %d)에 해당하는 기물이 없습니다.", positionId));
        }
        String pieceName = resultSet.getString("piece_name");
        return ChessPiece.findGamePieceBy(pieceName);
    }

    private ResultSet findByPositionId(int positionId) throws SQLException {
        String query = "SELECT * FROM piece WHERE position_id = ?";
        return repositoryUtil.executeQuery(query, Integer.toString(positionId));
    }

    public void insertOrUpdateIfExist(int positionId, GamePiece gamePiece) throws SQLException {
        ResultSet resultSet = findByPositionId(positionId);
        if (!resultSet.next()) {
            addGamePiece(positionId, gamePiece);
            return;
        }
        updateByPositionId(positionId, gamePiece);
    }

    public void updateByPositionId(int positionId, GamePiece gamePiece) throws SQLException {
        String query = "UPDATE piece SET piece_name = ? WHERE position_id = ?";
        repositoryUtil.executeUpdate(query, gamePiece.getName(), Integer.toString(positionId));
    }

    public void deleteByPositionId(int positionId) throws SQLException {
        String query = "DELETE FROM piece WHERE position_id = ?";
        repositoryUtil.executeUpdate(query, Integer.toString(positionId));
    }
}
