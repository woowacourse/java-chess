package chess.game.state.checked;

import chess.domain.Team;
import chess.game.state.running.RunningState;
import chess.game.state.running.WhiteTurnState;

public class BlackCheckedState extends CheckedState {
    public static final CheckedState STATE = new BlackCheckedState();

    private BlackCheckedState() {
    }

    @Override
    public Team getTurn() {
        return Team.BLACK;
    }

    @Override
    public RunningState changeTurn() {
        return WhiteTurnState.STATE;
    }
}
