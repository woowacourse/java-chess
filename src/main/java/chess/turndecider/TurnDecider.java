package chess.turndecider;

import chess.domain.piece.Piece;

public interface TurnDecider {

    boolean isCorrectTurn(Piece sourcePiece);

    void nextState(boolean isGameFinished);

    boolean isRunning();
}
