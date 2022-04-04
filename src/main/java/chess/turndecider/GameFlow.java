package chess.turndecider;

import chess.domain.piece.Piece;

public interface GameFlow {

    boolean isCorrectTurn(Piece sourcePiece);

    void nextState(boolean isGameFinished);

    boolean isRunning();

    String currentStateName();
}
