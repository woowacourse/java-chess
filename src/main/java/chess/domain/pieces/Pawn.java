package chess.domain.pieces;

import chess.domain.board.Col;
import chess.domain.board.Position;
import chess.domain.board.Row;

public class Pawn extends Piece {

    private static final int MOVE_UP_TWO = 2;
    private static final int MOVE_DOWN_TWO = -2;
    private static final int MOVE_UP_ONE = 1;
    private static final int MOVE_DOWN_ONE = -1;
    private static final int MOVE_RIGHT = 1;
    private static final int MOVE_LEFT = -1;
    private static final int SAME_POSITION = 0;
    private static final int ROW = 1;
    private static final int COLUMN = 0;

    private boolean isFirstMove;

    public Pawn(final Team team) {
        super(team);
        this.isFirstMove = true;
    }

    @Override
    public void canMove(final Position source, final Position destination) {
        if (!validatePosition(source, destination)) {
            throw new IllegalArgumentException("Pawn의 움직임 범위가 올바르지 않습니다.");
        }
    }

    private boolean validatePosition(final Position source, final Position destination) {
        int subRow = destination.calculateDistanceOfRow(source);
        int subCol = destination.calculateDistanceOfCol(source);

        if (this.isFirstMove) {
            this.isFirstMove = false;
            return canMoveAtFirst(subRow, subCol);
        }
        return canMoveAfterFirst(subRow, subCol);
    }

    private boolean canMoveAtFirst(final int subRow, final int subCol) {
        if (isWhiteTeam()) {
            return isLowerPawnMoveAtFirst(subRow, subCol) || isLowerPawnAttack(subRow, subCol);
        }
        return isUpperPawnMoveAtFirst(subRow, subCol) || isUpperPawnAttack(subRow, subCol);
    }

    private boolean canMoveAfterFirst(final int subRow, final int subCol) {
        if (isWhiteTeam()) {
            return isLowerPawnMoveAfterFirst(subRow, subCol) || isLowerPawnAttack(subRow, subCol);
        }
        return isUpperPawnMoveAfterFirst(subRow, subCol) || isUpperPawnAttack(subRow, subCol);
    }

    private boolean isUpperPawnAttack(final int subRow, final int subCol) {
        return (subCol == MOVE_RIGHT && subRow == MOVE_DOWN_ONE) || (subCol == MOVE_LEFT && subRow == MOVE_DOWN_ONE);
    }

    private boolean isLowerPawnAttack(final int subRow, final int subCol) {
        return (subCol == MOVE_RIGHT && subRow == MOVE_UP_ONE) || (subCol == MOVE_LEFT && subRow == MOVE_UP_ONE);
    }

    private boolean isUpperPawnMoveAtFirst(final int subRow, final int subCol) {
        return subCol == SAME_POSITION && (subRow == MOVE_DOWN_ONE || subRow == MOVE_DOWN_TWO);
    }

    private boolean isLowerPawnMoveAtFirst(final int subRow, final int subCol) {
        return subCol == SAME_POSITION && (subRow == MOVE_UP_ONE || subRow == MOVE_UP_TWO);
    }

    private boolean isUpperPawnMoveAfterFirst(final int subRow, final int subCol) {
        return subCol == SAME_POSITION && subRow == MOVE_DOWN_ONE;
    }

    private boolean isLowerPawnMoveAfterFirst(final int subRow, final int subCol) {
        return subCol == SAME_POSITION && subRow == MOVE_UP_ONE;
    }
}
