package chess.piece;

import chess.board.Position;

public class MovedPawn extends Piece {

    private static final int MAX_UNIT_MOVE = 1;

    public MovedPawn(Color color) {
        super(PieceType.PAWN, color, MAX_UNIT_MOVE);
    }

    @Override
    public boolean isMovable(Position source, Position destination) {
        return false;
    }

    @Override
    public boolean isAttackable(Position source, Position destination) {
        return false;
    }
}
