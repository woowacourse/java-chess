package chess.domain.state;

import chess.domain.piece.Pieces;
import chess.domain.piece.info.Color;

public class BlackTurn extends Running {
    public BlackTurn(Pieces pieces) {
        this(pieces, Color.BLACK);
    }

    public BlackTurn(Pieces pieces, Color color) {
        super(pieces, color);
    }

    @Override
    public State next() {
        if (pieces.isAliveAllKings()) {
            return new WhiteTurn(pieces);
        }
        return new End(pieces, color());
    }
}
