package model.piece;

import constant.ErrorCode;
import exception.InvalidMovingException;
import java.util.Set;
import model.Camp;
import model.position.Moving;
import model.position.Position;
import view.message.PieceType;

public class Queen extends Piece {

    public Queen(final Camp camp) {
        super(camp);
    }

    @Override
    public Set<Position> getMoveRoute(final Moving moving) {
        if (canMovable(moving)) {
            return moving.route();
        }
        throw new InvalidMovingException(ErrorCode.INVALID_MOVEMENT_RULE);
    }

    @Override
    protected boolean canMovable(final Moving moving) {
        if (moving.isNotMoved()) {
            return false;
        }
        return moving.isDiagonal() || moving.isVertical() || moving.isHorizontal();
    }

    @Override
    public String toString() {
        return PieceType.from(this).getValue();
    }
}
