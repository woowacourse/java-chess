package chess.turndecider;

import chess.piece.Piece;

public interface State {

    boolean isSameColor(Piece sourcePiece);

    State nextState();
}
