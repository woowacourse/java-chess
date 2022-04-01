package chess.turndecider.state;

import chess.domain.piece.Piece;

public interface State {

    boolean isSameColor(Piece sourcePiece);

    State nextState(boolean isGameFinished);

    boolean isRunning();
}
