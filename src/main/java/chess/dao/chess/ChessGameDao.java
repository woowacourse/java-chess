package chess.dao.chess;

import chess.domain.chess.CampType;
import chess.entity.ChessGameEntity;

import java.util.Optional;

public interface ChessGameDao {
    Optional<ChessGameEntity> findByUserId(final long userId);

    Long insert(final ChessGameEntity chessGameEntity);

    void updateCurrentCampById(long id, CampType currentCamp);

    void deleteByUserId(long userId);
}
