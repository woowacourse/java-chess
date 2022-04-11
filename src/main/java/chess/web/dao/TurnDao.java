package chess.web.dao;

import chess.domain.state.Turn;

public interface TurnDao {
    Turn findCurrentTurn();

    void updateTurn(Turn currentTurn, Turn turnToUpdate);
}
