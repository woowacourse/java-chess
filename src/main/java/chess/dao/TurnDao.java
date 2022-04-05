package chess.dao;

import chess.domain.state.Turn;
import java.util.Optional;

public interface TurnDao {

    Optional<Turn> findCurrentTurn();

    void updateTurn(Turn currentTurn, Turn turn);
}
