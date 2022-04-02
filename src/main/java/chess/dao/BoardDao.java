package chess.dao;

import chess.Board;

public interface BoardDao {

    String getCurrentTurnById(Long id);

    void updateTurnById(Long id, String newTurn);
}
