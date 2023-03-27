package chess.dao.chess;

import chess.domain.chess.CampType;
import chess.entity.ChessGameEntity;

import java.util.Optional;

public interface ChessGameDao {
    Optional<ChessGameEntity> findByUserId(final Long userId);

    Long save(final ChessGameEntity chessGameEntity);

    void updateCurrentCampById(Long id, CampType currentCamp);
}
