package chess.domain.command;

import chess.domain.board.ScoreResult;
import chess.domain.state.State;

public class Status extends Start {

    Status(State state) {
        super(state);
    }

    @Override
    public boolean isStatus() {
        return true;
    }

    @Override
    public StatusResult getStatus() {

        ScoreResult score = state.getScore();

        return new StatusResult(score);
    }
}
