package chess.model.piece;

import chess.model.Position;

public class King extends Piece {

    private static final int MAX_MOVE_RANGE = 1;

    public King(PieceType pieceType, Color color) {
        super(pieceType, color);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        int rowDifference = Math.abs(calculateRowDifference(source, target));
        int columnDifference = Math.abs(calculateColumnDifference(source, target));
        return rowDifference <= MAX_MOVE_RANGE && columnDifference <= MAX_MOVE_RANGE;
    }

    @Override
    public boolean canAttack(Position source, Position target) {
        return canMove(source, target);
    }

    @Override
    public boolean canJump() {
        return false;
    }
}
