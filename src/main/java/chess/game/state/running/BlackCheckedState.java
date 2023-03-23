package chess.game.state.running;

import chess.domain.Team;

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
