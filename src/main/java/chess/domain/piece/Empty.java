package chess.domain.piece;

import chess.domain.position.Position;

public final class Empty extends Piece {

    public Empty() {
        super(Camp.NEUTRAL, PieceSymbol.EMPTY);
    }

    @Override
    public boolean isMovable(Position from, Position to) {
        return false;
    }
}
