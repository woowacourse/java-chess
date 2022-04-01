package chess.domain.piece.pawn;

import chess.domain.piece.Color;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public final class BlackPawn extends Pawn {

    private static final Direction DIRECTION = Direction.SOUTH;

    public static final List<Position> BLACK_INIT_LOCATIONS = List.of(
            Position.of("a7"), Position.of("b7"), Position.of("c7"), Position.of("d7"),
            Position.of("e7"), Position.of("f7"), Position.of("g7"), Position.of("h7"));

    public BlackPawn() {
        super(Color.BLACK);
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
