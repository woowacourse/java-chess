package chess.model.turndecider;

import chess.model.piece.Piece;

public interface State {

    boolean isSameColor(Piece Piece);

    State nextState();
}
