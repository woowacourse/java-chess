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
            boolean isInitialMove = rowDifference == -2 && source.getRow() == 6;
            return isColumnNeutral && (isRowUp || isInitialMove);
        }
        if (type.isBlack()) {
            boolean isRowDown = rowDifference == 1;
            boolean isColumnNeutral = columnDifference == 0;
            boolean isInitialMove = rowDifference == 2 && source.getRow() == 1;
            return isColumnNeutral && (isRowDown || isInitialMove);
        }
        return false;
    }

    public boolean canAttack(Position source, Position target) {
        int rowDifference = calculateRowDifference(source, target);
        int columnDifference = calculateColumnDifference(source, target);
        if (type.isWhite()) {
            boolean isRowUp = rowDifference == -1;
            boolean isColumnDiagonal = Math.abs(columnDifference) == 1;
            return isRowUp && isColumnDiagonal;
        }
        if (type.isBlack()) {
            boolean isRowDown = rowDifference == 1;
            boolean isColumnDiagonal = Math.abs(columnDifference) == 1;
            return isRowDown && isColumnDiagonal;
        }
        return false;
    }
}
