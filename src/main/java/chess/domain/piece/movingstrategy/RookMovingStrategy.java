package chess.domain.piece.movingstrategy;

import chess.domain.piece.PieceColor;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.Set;

public class RookMovingStrategy extends MovingStrategy {

    private final Set<Direction> POSSIBLE_DIRECTIONS = Set.of(
            Direction.HORIZONTAL_LEFT,
            Direction.HORIZONTAL_RIGHT,
            Direction.VERTICAL_UP,
            Direction.VERTICAL_DOWN
    );

    @Override
    boolean isPossibleStep(Position from, Position to, PieceColor pieceColor) {
        return true;
    }

    @Override
    boolean isPossibleDirection(Position from, Position to, PieceColor pieceColor) {
        Direction direction = Direction.of(from, to);
        return POSSIBLE_DIRECTIONS.contains(direction);
    }
}
