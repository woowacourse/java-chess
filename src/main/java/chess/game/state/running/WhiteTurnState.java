package chess.game.state.running;

import chess.domain.Team;

public class WhiteTurnState extends RunningState {
    public static final RunningState STATE = new WhiteTurnState();

    @Override
    public Team getTurn() {
        return Team.WHITE;
    }

    @Override
    public RunningState changeTurn() {
        return BlackTurnState.STATE;
    }
}
