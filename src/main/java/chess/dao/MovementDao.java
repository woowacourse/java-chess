package chess.dao;

import chess.entity.Movement;

import java.util.List;

public interface MovementDao {

    Movement save(Movement entity);

    List<Movement> findAllByChessId(Long chessId);

    void deleteAll();
}
