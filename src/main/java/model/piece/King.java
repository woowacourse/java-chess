package model.piece;

import constant.ErrorCode;
import exception.InvalidMovingException;
import java.util.Set;
import model.Camp;
import model.position.Moving;
import model.position.Position;
import view.message.PieceType;

public class King extends Piece {

    public King(final Camp camp) {
        super(camp);
    }

    @Override
    public Set<Position> getMoveRoute(final Moving moving) {
        if (canMovable(moving)) {
            return Set.of();
        }
        throw new InvalidMovingException(ErrorCode.INVALID_MOVEMENT_RULE);
    }

    @Override
    protected boolean canMovable(final Moving moving) {
        if (moving.isNotMoved()) {
            return false;
        }
        final Position currentPosition = moving.getCurrentPosition();
        final Position nextPosition = moving.getNextPosition();

        final int currentRank = currentPosition.getRankIndex();
        final int currentFile = currentPosition.getFileIndex();

        final int nextRank = nextPosition.getRankIndex();
        final int nextFile = nextPosition.getFileIndex();

        return Math.abs(nextRank - currentRank) <= 1 && Math.abs(nextFile - currentFile) <= 1;
    }

    @Override
    public String toString() {
        return PieceType.from(this).getValue();
    }
}
