package chess.domain.piece.state.started;

import chess.domain.piece.state.Dead;
import chess.domain.piece.state.PieceState;

public abstract class Started implements PieceState {
    @Override
    public PieceState killed() {
        return new Dead();
    }
}
