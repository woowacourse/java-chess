package chess.turndecider;

import chess.piece.Piece;

public interface TurnDecider {

    void setState(State state);

    boolean isCorrectTurn(Piece sourcePiece);
}
