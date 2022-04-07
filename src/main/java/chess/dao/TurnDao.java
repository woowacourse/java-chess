package chess.dao;

import chess.dto.TurnDto;

public interface TurnDao {

    TurnDto findTurn();

    void updateTurn(final String turn);

    void resetTurn();
}
