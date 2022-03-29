package chess.model.turndecider;

import chess.model.PieceColor;
import chess.model.piece.Piece;

public class WhiteState implements State {

    private final PieceColor white = PieceColor.WHITE;

    @Override
    public boolean isSameColor(Piece Piece) {
        return Piece.isSameColor(white);
    }

    @Override
    public State nextState() {
        return new BlackState();
    }
}
