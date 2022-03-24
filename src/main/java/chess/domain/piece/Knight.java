package chess.domain.piece;

import chess.domain.Camp;
import chess.domain.board.Position;

public final class Knight extends Piece {
    private static final int FIRST_MOVABLE_DISTANCE = 2;
    private static final int SECOND_MOVABLE_DISTANCE = 1;

    public Knight(Camp camp) {
        super(camp);
    }

    @Override
    public boolean canMove(Position beforePosition, Position afterPosition) {
        int columnDistance = beforePosition.columnDistance(afterPosition);
        int rowDistance = beforePosition.rowDistance(afterPosition);
        if (rowDistance == FIRST_MOVABLE_DISTANCE && columnDistance == SECOND_MOVABLE_DISTANCE) {
            return true;
        }
        return columnDistance == FIRST_MOVABLE_DISTANCE && rowDistance == SECOND_MOVABLE_DISTANCE;
    }

    @Override
    public boolean isBishop() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isKnight() {
        return true;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isQueen() {
        return false;
    }

    @Override
    public boolean isRook() {
        return false;
    }
}
