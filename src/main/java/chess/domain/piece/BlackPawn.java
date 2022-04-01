package chess.domain.piece;

import static chess.domain.piece.Direction.NORTH_NORTH;
import static chess.domain.piece.Direction.SOUTH;
import static chess.domain.piece.Direction.SOUTH_SOUTH;
import static chess.domain.piece.Direction.pullSouthDirections;
import static chess.domain.piece.Team.*;

import chess.domain.position.Position;
import chess.domain.position.Row;


public class BlackPawn extends Pawn {

    public BlackPawn() {
        super(BLACK, pullSouthDirections());
    }

    @Override
    public void checkValidFirstMove(Position from, Direction direction) {
        if ((direction == SOUTH_SOUTH && !isBlackStart(from)) || direction == NORTH_NORTH) {
            throw new IllegalArgumentException("아래로 두칸은 black Pawn 이 첫수일 경우만 가능합니다.");
        }
    }

    private boolean isBlackStart(Position position) {
        return super.isSameTeam(BLACK) && position.isSameRow(Row.SEVEN);
    }

    @Override
    public Direction findDirection(Position from, Position to) {
        Direction direction = from.findDirection(to, this);

        if (direction == SOUTH_SOUTH) {
            return SOUTH;
        }

        return direction;
    }
}
