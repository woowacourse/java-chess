package chess.game.state.running;

import chess.domain.Team;

public class WhiteCheckedState extends CheckedState {
    public static final CheckedState STATE = new WhiteCheckedState();

    private WhiteCheckedState() {
    }

    @Override
    public Team getTurn() {
        return Team.WHITE;
    }

    @Override
    public RunningState changeTurn() {
        return BlackTurnState.STATE;
    }
}
