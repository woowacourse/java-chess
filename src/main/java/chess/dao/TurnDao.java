package chess.dao;

import chess.web.dto.TurnDto;

public interface TurnDao {

    void save(TurnDto turnDto);

    TurnDto findLastTurn();

    boolean isFirstTurn();
}
