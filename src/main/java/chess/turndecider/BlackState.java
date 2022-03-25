package chess.turndecider;

import chess.PieceColor;
import chess.piece.Piece;

public class BlackState implements State{

    private final PieceColor pieceColor = PieceColor.BLACK;

    @Override
    public boolean isSameColor(Piece sourcePiece) {
        return sourcePiece.isSameColor(pieceColor);
    }

    @Override
    public State nextState() {
        return new WhiteState();
    }
}
