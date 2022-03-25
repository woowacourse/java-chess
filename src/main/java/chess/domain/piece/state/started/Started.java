package chess.domain.piece.state.started;

import chess.domain.piece.state.Dead;
import chess.domain.piece.state.State;

public abstract class Started implements State {
    @Override
    public State killed() {
        return new Dead();
    }
}
