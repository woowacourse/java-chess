package chess.repository;

import chess.manager.ChessManager;

import java.util.Optional;

public interface ChessRepository {
    Long save(ChessManager entity);

    Optional<ChessManager> findById(Long id);
}
