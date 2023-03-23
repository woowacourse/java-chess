package chess.domain.state;

import chess.domain.piece.Color;

public abstract class Finished implements ChessState {

    @Override
    public boolean isRunnable() {
        return false;
    }

    @Override
    public ChessState changeTurn() {
        throw new IllegalArgumentException();
    }

    @Override
    public boolean isInCorrectTurn(final Color color) {
        throw new IllegalArgumentException();
    }
}
