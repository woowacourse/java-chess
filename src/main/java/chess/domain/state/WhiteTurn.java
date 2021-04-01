package chess.domain.state;

import chess.domain.piece.Pieces;
import chess.domain.piece.info.Color;

public class WhiteTurn extends Running {
    public WhiteTurn(Pieces pieces) {
        this(pieces, Color.WHITE);
    }

    public WhiteTurn(Pieces pieces, Color color) {
        super(pieces, color);
    }

    @Override
    public State next() {
        return new BlackTurn(pieces);
    }
}
