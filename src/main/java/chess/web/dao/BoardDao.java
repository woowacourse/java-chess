package chess.web.dao;

import chess.board.Board;
import chess.board.Turn;

import java.util.Optional;

public interface BoardDao {

    Optional<Turn> findTurnById(Long id);

    void updateTurnById(Long id, String newTurn);

    Long save();

    Optional<Board> findById(Long id);

    void deleteById(Long id);
}
