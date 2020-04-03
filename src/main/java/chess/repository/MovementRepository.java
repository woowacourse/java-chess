package chess.repository;

import chess.repository.entity.Movement;

import java.util.List;

public interface MovementRepository {

    Movement save(Movement entity);

    List<Movement> findAllByChessId(Long chessId);
}
