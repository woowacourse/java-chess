package model.piece;

import constant.ErrorCode;
import exception.InvalidMovingException;
import java.util.Set;
import model.Camp;
import model.position.Moving;
import model.position.Position;
import view.message.PieceType;

public class Knight extends Piece {

    public Knight(final Camp camp) {
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

        final int differenceRank = Math.abs(currentPosition.getRankIndex() - nextPosition.getRankIndex());
        final int differenceFile = Math.abs(currentPosition.getFileIndex() - nextPosition.getFileIndex());

        return differenceRank + differenceFile == 3 && differenceRank != 0 && differenceFile != 0;
    }

    @Override
    public String toString() {
        return PieceType.from(this).getValue();
    }
}
