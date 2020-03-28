package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.exception.InvalidMovementException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PawnStrategy implements MoveStrategy {

    private final List<Direction> directions = Collections.singletonList(Direction.N);
    private final List<Direction> killDirections = Arrays.asList(Direction.NE, Direction.NW);

    @Override
    public List<Position> findMovePath(Position source, Position target) {
        for (Direction direction : directions) {
            Position position = source.destinationOf(direction).orElse(null);
            if (target.equals(position)) {
                return Collections.emptyList();
            }
            List<Position> positions = new ArrayList<>();
            if (ChessPiece.PAWN.getOriginalPositions().contains(source)) {
                positions = Collections.singletonList(position);
                position = position.destinationOf(direction).orElse(null);
            }
            if (target.equals(position)) {
                return positions;
            }
        }

        throw new InvalidMovementException();
    }

    @Override
    public List<Position> findKillPath(Position source, Position target) {
        for (Direction direction : killDirections) {
            Position position = source.destinationOf(direction).orElse(null);
            if (target.equals(position)) {
                return Collections.emptyList();
            }
        }

        throw new InvalidMovementException();
    }
}