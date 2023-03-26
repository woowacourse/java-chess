package chess.dao.chess;

import chess.entity.ChessGameEntity;

import java.util.Optional;

public interface ChessGameDao {
    Optional<ChessGameEntity> findByUserId(final Long userId);
}
