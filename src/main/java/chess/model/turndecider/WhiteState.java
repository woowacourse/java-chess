package chess.model.turndecider;

import chess.model.piece.Piece;
import chess.vo.PieceColor;

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
