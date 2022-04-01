package chess.domain.piece.pawn;

import chess.domain.piece.Color;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public final class WhitePawn extends Pawn {

    private static final Direction DIRECTION = Direction.NORTH;

    public static final List<Position> WHITE_INIT_LOCATIONS = List.of(
            Position.of("a2"), Position.of("b2"), Position.of("c2"), Position.of("d2"),
            Position.of("e2"), Position.of("f2"), Position.of("g2"), Position.of("h2"));

    public WhitePawn() {
        super(Color.WHITE);
    }

    @Override
    public Map<Direction, List<Position>> getMovablePositions(final Position position) {
        return super.getMovablePositionsByDirection(position, DIRECTION);
    }

    @Override
    public Direction getPawnDirection() {
        return DIRECTION;
    }
}
