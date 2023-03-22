package chess.game.state.running;

import chess.domain.Team;

public class BlackTurnState extends RunningState {
    public static final RunningState STATE = new BlackTurnState();

    @Override
    public Team getTurn() {
        return Team.BLACK;
    }

    @Override
    public RunningState changeTurn() {
        return WhiteTurnState.STATE;
    }
}
