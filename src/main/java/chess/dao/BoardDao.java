package chess.dao;

import chess.Board;
import chess.Turn;

import java.util.Optional;

public interface BoardDao {

    Optional<Turn> findTurnById(Long id);

    void updateTurnById(Long id, String newTurn);

    Long save();

    Optional<Board> findById(Long id);
}
