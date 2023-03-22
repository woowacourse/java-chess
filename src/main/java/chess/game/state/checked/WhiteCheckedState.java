package chess.game.state.checked;

import chess.domain.Team;
import chess.game.state.running.BlackTurnState;
import chess.game.state.running.RunningState;

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
