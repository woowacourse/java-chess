package chess.model.turndecider;

import chess.model.piece.Piece;
import chess.vo.PieceColor;

public class WhiteState implements State {

    private final PieceColor pieceColor = PieceColor.WHITE;

    @Override
    public boolean isSameColor(Piece sourcePiece) {
        return sourcePiece.isSameColor(pieceColor);
    }

    @Override
    public State nextState() {
        return new BlackState();
    }
}
