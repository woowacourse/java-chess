package chess.domain.state.turn;

import chess.domain.Team;

public final class BlackTurn extends Running {

    public BlackTurn() {
        super(Team.BLACK);
    }

    @Override
    protected State next() {
        return new WhiteTurn();
    }

    @Override
    public String toString() {
        return "BlackTurn";
    }
}
