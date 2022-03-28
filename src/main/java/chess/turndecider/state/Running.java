package chess.turndecider.state;

import chess.domain.piece.Piece;

public abstract class Running implements State{

    @Override
    public boolean isSameColor(Piece sourcePiece) {
        return false;
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
