package chess.game.state.checked;

import chess.domain.Team;
import chess.game.state.running.RunningState;

public abstract class CheckedState extends RunningState {

    public static CheckedState createCheckedState(Team team) {
        if (team == Team.WHITE) {
            return WhiteCheckedState.STATE;
        }
        if (team == Team.BLACK) {
            return BlackCheckedState.STATE;
        }
        throw new IllegalArgumentException(INVALID_TEAM_EXCEPTION_MESSAGE);
    }

    @Override
    public boolean isChecked() {
        return true;
    }
}
