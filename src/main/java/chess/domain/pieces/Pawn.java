package chess.domain.pieces;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.exception.PieceMessage;

public class Pawn extends Piece {

    private static final int MOVE_UP_TWO = 2;
    private static final int MOVE_DOWN_TWO = -2;
    private static final int MOVE_UP_ONE = 1;
    private static final int MOVE_DOWN_ONE = -1;
    private static final int MOVE_RIGHT = 1;
    private static final int MOVE_LEFT = -1;
    private static final int SAME_POSITION = 0;
    private static final double SCORE_OF_PAWN = 1;
    private static final double HALF_SCORE_OF_PAWN = 0.5;

    private boolean isFirstMove;

    public Pawn(final Name name) {
        super(name);
        this.score = new Score(SCORE_OF_PAWN);
        this.isFirstMove = true;
    }

    @Override
    public void canMove(final Position start, final Position end) {
        if (!validatePosition(start, end)) {
            throw new IllegalArgumentException(PieceMessage.PAWN_INVALID_MOVE.getMessage());
        }
    }

    private boolean validatePosition(final Position start, final Position end) {
        int subRow = Row.subPositionFromArrivePosition(start.getRow(), end.getRow());
        int subCol = Column.subPositionFromArrivePosition(start.getCol(), end.getCol());

        if (this.isFirstMove) {
            this.isFirstMove = false;
            return canMoveAtFirst(subRow, subCol);
        }
        return canMoveAfterFirst(subRow, subCol);
    }

    private boolean canMoveAtFirst(final int subRow, final int subCol) {
        if (isNameLowerCase()) {
            return isLowerPawnMoveAtFirst(subRow, subCol) || isLowerPawnAttack(subRow, subCol);
        }
        return isUpperPawnMoveAtFirst(subRow, subCol) || isUpperPawnAttack(subRow, subCol);
    }

    private boolean canMoveAfterFirst(final int subRow, final int subCol) {
        if (isNameLowerCase()) {
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

    @Override
    public boolean isPawn() {
        return this.name.isPawn();
    }

    @Override
    public void updateScoreByOtherPawnsBeingWithSameColumn() {
        this.score = new Score(HALF_SCORE_OF_PAWN);
    }
}
