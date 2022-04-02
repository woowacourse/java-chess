package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Pawn extends Piece {

    public static final List<Position> BLACK_INIT_LOCATIONS = List.of(
            Position.of("a7"), Position.of("b7"), Position.of("c7"), Position.of("d7"),
            Position.of("e7"), Position.of("f7"), Position.of("g7"), Position.of("h7"));
    public static final List<Position> WHITE_INIT_LOCATIONS = List.of(
            Position.of("a2"), Position.of("b2"), Position.of("c2"), Position.of("d2"),
            Position.of("e2"), Position.of("f2"), Position.of("g2"), Position.of("h2"));

    public Pawn(Color color) {
        super(color, PieceType.PAWN);
    }

    @Override
    public Map<Direction, List<Position>> getMovablePositions(Position position) {
        Map<Direction, List<Position>> movable = new EnumMap<>(Direction.class);
        movable.put(Direction.pawnDirection(color), new ArrayList<>());
        putMovablePositions(movable, position);
        return movable;
    }

    private void putMovablePositions(Map<Direction, List<Position>> movable, Position position) {
        Position nextPosition = position.toDirection(Direction.pawnDirection(color));
        if (!hasMovablePosition(position, nextPosition)) {
            return;
        }
        movable.get(Direction.pawnDirection(color)).add(nextPosition);
        if (isFirstMove(position)) {
            putMovablePositions(movable, nextPosition);
        }
    }

    private boolean hasMovablePosition(Position position, Position nextPosition) {
        return nextPosition != position;
    }

    private boolean isFirstMove(Position position) {
        return isBlackFirstMovePawn(position) || isWhiteFirstMovePawn(position);
    }

    private boolean isBlackFirstMovePawn(Position position) {
        return color == Color.BLACK && BLACK_INIT_LOCATIONS.contains(position);
    }

    private boolean isWhiteFirstMovePawn(Position position) {
        return color == Color.WHITE && WHITE_INIT_LOCATIONS.contains(position);
    }
}
