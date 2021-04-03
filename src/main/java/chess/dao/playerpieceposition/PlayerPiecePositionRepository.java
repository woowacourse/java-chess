package chess.dao.playerpieceposition;

import chess.dao.entity.GamePiecePositionEntity;
import chess.dao.entity.PiecePositionEntity;
import chess.domain.piece.Piece;
import chess.domain.position.PiecePosition;
import chess.domain.position.Position;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface PlayerPiecePositionRepository {
    void save(Long playerId, PiecePosition piecePosition) throws SQLException;

    Map<Position, Piece> findAllByGameId(Long gameId) throws SQLException;

    List<PiecePositionEntity> findAllByPlayerId(Long playerId) throws SQLException;

    GamePiecePositionEntity findGamePiecePositionByGameIdAndPositionId(Long gameId, Long positionId) throws SQLException;

    void updatePiecePosition(GamePiecePositionEntity gamePiecePositionEntity) throws SQLException;

    void removePiecePositionOfGame(GamePiecePositionEntity gamePiecePositionEntity) throws SQLException;

    void removeAllByPlayer(Long playerId) throws SQLException;

    void removeAll() throws SQLException;
}
