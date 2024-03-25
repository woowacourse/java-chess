package chess.domain.piece;

import chess.domain.piece.character.Character;
import chess.domain.piece.character.Team;

public class WhitePawn extends AbstractPawn {
    private static final int MOVE_ROW_DIFFERENCE = 1;
    private static final int FIRST_MOVE_MAX_ROW_DIFFERENCE = 2;

    public WhitePawn() {
        super(Team.WHITE);
    }

    private WhitePawn(Character character, boolean hasMoved) {
        super(character, hasMoved);
    }

    @Override
    public Piece move() {
        if (isMoved) {
            return this;
        }
        return new WhitePawn(character, true);
    }

    @Override
    protected boolean isMovable(int rowDifference, int columnDifference) {
        if (columnDifference != 0) {
            return false;
        }
        return rowDifference == MOVE_ROW_DIFFERENCE
                || (!isMoved && rowDifference == FIRST_MOVE_MAX_ROW_DIFFERENCE);
    }

    @Override
    protected boolean isAttackable(int rowDifference, int columnDifference) {
        return rowDifference == MOVE_ROW_DIFFERENCE
                && Math.abs(columnDifference) == ATTACK_ABSOLUTE_COLUMN_DIFFERENCE;
    }
}
