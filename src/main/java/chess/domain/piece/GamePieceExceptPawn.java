package chess.domain.piece;

import chess.domain.game.Side;

public abstract class GamePieceExceptPawn extends GamePiece {

    public GamePieceExceptPawn(Side side, String initial) {
        super(side, initial);
    }

    @Override
    public final void moved() {
    }

    @Override
    public final boolean isPawn() {
        return false;
    }
}
