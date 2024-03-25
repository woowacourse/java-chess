package chess.model.piece;

import chess.model.Position;

public class Pawn extends Piece {

    private static final int NONE_MOVE = 0;
    private static final int HORIZONTAL_MOVE = 1;
    private static final int UP_SINGLE_MOVE = -1;
    private static final int UP_DOUBLE_MOVE = -2;
    private static final int DOWN_SINGLE_MOVE = 1;
    private static final int DOWN_DOUBLE_MOVE = 2;
    private static final int WHITE_INITIAL_ROW = 6;
    private static final int BLACK_INITIAL_ROW = 1;

    public Pawn(PieceType pieceType, Color color) {
        super(pieceType, color);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        int rowDifference = calculateRowDifference(source, target);
        int columnDifference = calculateColumnDifference(source, target);
        if (color.isWhite()) {
            boolean isRowUp = rowDifference == UP_SINGLE_MOVE;
            boolean isColumnNeutral = columnDifference == NONE_MOVE;
            boolean isInitialMove = rowDifference == UP_DOUBLE_MOVE && source.getRow() == WHITE_INITIAL_ROW;
            return isColumnNeutral && (isRowUp || isInitialMove);
        }
        if (color.isBlack()) {
            boolean isRowDown = rowDifference == DOWN_SINGLE_MOVE;
            boolean isColumnNeutral = columnDifference == NONE_MOVE;
            boolean isInitialMove = rowDifference == DOWN_DOUBLE_MOVE && source.getRow() == BLACK_INITIAL_ROW;
            return isColumnNeutral && (isRowDown || isInitialMove);
        }
        return false;
    }

    @Override
    public boolean canAttack(Position source, Position target) {
        int rowDifference = calculateRowDifference(source, target);
        int columnDifference = calculateColumnDifference(source, target);
        if (color.isWhite()) {
            boolean isRowUp = rowDifference == UP_SINGLE_MOVE;
            boolean isColumnHorizontal = Math.abs(columnDifference) == HORIZONTAL_MOVE;
            return isRowUp && isColumnHorizontal;
        }
        if (color.isBlack()) {
            boolean isRowDown = rowDifference == DOWN_SINGLE_MOVE;
            boolean isColumnHorizontal = Math.abs(columnDifference) == HORIZONTAL_MOVE;
            return isRowDown && isColumnHorizontal;
        }
        return false;
    }

    @Override
    public boolean canJump() {
        return false;
    }
}
