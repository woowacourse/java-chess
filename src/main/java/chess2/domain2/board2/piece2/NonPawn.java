package chess2.domain2.board2.piece2;

import chess2.domain2.board2.Position;

public final class NonPawn extends Piece {

    public NonPawn(Color color, PieceType type) {
        super(color, type);
    }

    @Override
    public boolean canMove(Position from, Position to) {
        return type.isMovable(from, to);
    }

    @Override
    protected boolean isAttackableRoute(Position from, Position to) {
        return type.isMovable(from, to);
    }
}
