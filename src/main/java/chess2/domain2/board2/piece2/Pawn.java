package chess2.domain2.board2.piece2;

import chess2.domain2.board2.Position;

public final class Pawn extends Piece {

    public Pawn(Color color) {
        super(color, PieceType.PAWN);
    }

    @Override
    protected boolean isMovableRoute(Position from, Position to) {
        // TODO: replace with pawn move logic
        return type.isMovable(from, to);
    }

    @Override
    protected boolean isAttackableRoute(Position from, Position to) {
        // TODO: replace with pawn attack logic
        return type.isMovable(from, to);
    }
}
