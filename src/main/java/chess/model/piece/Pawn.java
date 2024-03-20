package chess.model.piece;

import chess.model.Position;

public class Pawn extends Piece {

    public Pawn(PieceType pieceType) {
        super(pieceType);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        int rowDifference = calculateRowDifference(source, target);
        int columnDifference = calculateColumnDifference(source, target);
        if (type.isWhite()) {
            boolean isRowUp = rowDifference == -1;
            boolean isColumnNeutral = columnDifference == 0;
            return isRowUp && isColumnNeutral;
        }
        boolean isRowDown = rowDifference == 1;
        boolean isColumnNeutral = columnDifference == 0;
        return isRowDown && isColumnNeutral;
    }
}
