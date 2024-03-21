package chess.model.piece;

import chess.model.Position;

public class King extends Piece {

    private static final int MAX_MOVE_RANGE = 1;

    public King(PieceType pieceType) {
        super(pieceType);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        int rowDifference = Math.abs(calculateRowDifference(source, target));
        int columnDifference = Math.abs(calculateColumnDifference(source, target));
        return rowDifference <= MAX_MOVE_RANGE && columnDifference <= MAX_MOVE_RANGE;
    }
}
