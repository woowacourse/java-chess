package chess.turndecider;

import chess.piece.Piece;

public interface TurnDecider {

    boolean isCorrectTurn(Piece sourcePiece);

    void nextState();
}
