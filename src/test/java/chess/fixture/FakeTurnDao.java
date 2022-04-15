package chess.fixture;

import chess.domain.state.Turn;
import chess.dao.TurnDao;

public class FakeTurnDao implements TurnDao {

    private Turn currentTurn = Turn.WHITE_TURN;

    @Override
    public Turn findCurrentTurn() {
        return currentTurn;
    }

    @Override
    public void updateTurn(final Turn currentTurn, final Turn turnToUpdate) {
        this.currentTurn = turnToUpdate;
    }
}
