package chess.turndecider;

import chess.domain.piece.Piece;

public interface State {

    boolean isSameColor(Piece sourcePiece);

    State nextState();
}
