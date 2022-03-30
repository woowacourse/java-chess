package chess.domain.command;

import chess.domain.board.Result;
import chess.domain.piece.Color;
import chess.domain.state.State;
import java.util.Map;

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

        Map<Color, Double> score = state.getScore();
        Result result = state.getResult();

        return new StatusResult(score, result);
    }
}
