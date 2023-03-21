package chess.domain.strategy;

import chess.domain.board.Position;
import java.util.List;

public enum PawnDirection {
    WHITE_PAWN_MOVE(0, 1, false),
    WHITE_PAWN_DOUBLE_MOVE(0, 2, false),
    WHITE_PAWN_RIGHT_DIAGONAL_ATTACK(1, 1, true),
    WHITE_PAWN_LEFT_DIAGONAL_ATTACK(-1, 1, true),

    BLACK_PAWN_MOVE(0, -1, false),
    BLACK_PAWN_DOUBLE_MOVE(0, -2, false),
    BLACK_PAWN_RIGHT_DIAGONAL_ATTACK(1, -1, true),
    BLACK_PAWN_LEFT_DIAGONAL_ATTACK(-1, -1, true),
    ;

    final int col;
    final int row;
    final boolean isAttack;

    PawnDirection(final int col, final int row, final boolean isAttack) {
        this.col = col;
        this.row = row;
        this.isAttack = isAttack;
    }

    public boolean isSameDirection(final Position source, final Position destination) {
        List<Integer> moveDirection = generateDirection(source, destination);
        return this.col == moveDirection.get(0) && this.row == moveDirection.get(1);
    }

    public boolean isAttackMove(boolean isAttack) {
        return this.isAttack == isAttack;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    private List<Integer> generateDirection(final Position source, final Position destination) {
        int subCol = destination.calculateDistanceOfCol(source);
        int subRow = destination.calculateDistanceOfRow(source);
        return List.of(subCol, subRow);
    }
}
