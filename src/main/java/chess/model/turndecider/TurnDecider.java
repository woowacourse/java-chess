package chess.model.turndecider;

import chess.model.piece.Piece;

public interface TurnDecider {

    boolean isTurnOf(Piece Piece);

    void nextState();
}
