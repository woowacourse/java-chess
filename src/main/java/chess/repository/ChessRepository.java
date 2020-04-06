package chess.repository;

import chess.repository.entity.ChessEntity;

import java.util.List;
import java.util.Optional;

public interface ChessRepository {
    ChessEntity save(ChessEntity entity);

    Optional<ChessEntity> findById(Long id);

    Long update(ChessEntity entity);

    List<ChessEntity> findAll();
}
