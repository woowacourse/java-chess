package chess.domain.piece;

import static chess.domain.piece.Direction.NORTH;
import static chess.domain.piece.Direction.NORTH_NORTH;
import static chess.domain.piece.Direction.SOUTH_SOUTH;
import static chess.domain.piece.Direction.pullNorthDirections;
import static chess.domain.piece.Team.WHITE;

import chess.domain.position.Position;
import chess.domain.position.Row;

public class WhitePawn extends Pawn {

    public WhitePawn() {
        super(WHITE, pullNorthDirections());
    }

    @Override
    public void checkValidFirstMove(Position from, Direction direction) {
        if ((direction == NORTH_NORTH && !isWhiteStart(from)) || direction == SOUTH_SOUTH) {
            throw new IllegalArgumentException("위로 두칸은 white Pawn 이 첫수일 경우만 가능합니다.");
        }
    }

    private boolean isWhiteStart(Position position) {
        return super.isSameTeam(WHITE) && position.isSameRow(Row.TWO);
    }

    @Override
    public Direction findDirection(Position from, Position to) {
        Direction direction = from.findDirection(to, this);

        if (direction == NORTH_NORTH) {
            return NORTH;
        }

        return direction;
    }
}
