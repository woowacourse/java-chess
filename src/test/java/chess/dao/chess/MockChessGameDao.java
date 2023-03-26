package chess.dao.chess;

import chess.entity.ChessGameEntity;

import java.util.Optional;

public class MockChessGameDao implements ChessGameDao {
    @Override
    public Optional<ChessGameEntity> findByUserId(final Long userId) {
        if (userId == 1L) {
            return Optional.of(new ChessGameEntity(1L, "WHITE", 1L));
        }
        return Optional.empty();
    }
}
