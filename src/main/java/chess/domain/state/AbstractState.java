package chess.domain.state;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;

public abstract class AbstractState implements State {

    protected Color color;

    protected AbstractState(Color color) {
        this.color = color;
    }

    @Override
    public boolean isSameColor(Piece piece) {
        return piece.isSameColor(color);
    }

}
