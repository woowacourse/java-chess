package chess.domain.state.turn;

import chess.domain.Team;

public final class BlackTurn extends Running {

    BlackTurn() {
        super(Team.BLACK);
    }

    @Override
    protected State next() {
        return new WhiteTurn();
    }
}
