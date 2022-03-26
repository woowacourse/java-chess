package chess.model.turndecider;

import chess.model.piece.Piece;

public interface TurnDecider {

    boolean isCorrectTurn(Piece sourcePiece);

    void nextState();
}
