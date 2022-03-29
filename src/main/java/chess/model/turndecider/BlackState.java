package chess.model.turndecider;

import chess.model.PieceColor;
import chess.model.piece.Piece;

public class BlackState implements State {

    private final PieceColor black = PieceColor.BLACK;

    @Override
    public boolean isSameColor(Piece Piece) {
        return Piece.isSameColor(black);
    }

    @Override
    public State nextState() {
        return new WhiteState();
    }
}
