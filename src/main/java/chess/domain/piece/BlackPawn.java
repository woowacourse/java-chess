package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.util.Direction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlackPawn extends Pawn {
    private static final List<Direction> DIRECTIONS = new ArrayList<>(
            Arrays.asList(
                    Direction.SOUTH
//                    Direction.SE,
//                    Direction.SW
            )
    );

    public BlackPawn(char representation, Position position) {
        super(representation, position);
    }

    @Override
    public List<Position> getPossiblePositions() {
        List<Position> possiblePositions = new ArrayList<>();
        for (Direction direction : DIRECTIONS) {
            if (direction.move(position).getX() > 8 | direction.move(position).getX() < 1 | direction.move(position).getY() > 8 |
                    direction.move(position).getY() < 1) {
                break;
            }
            if (direction == Direction.SOUTH && position.getY() == 7) {
                Position movedPosition = direction.move(position);
                possiblePositions.add(movedPosition);
                possiblePositions.add(direction.move(movedPosition));
                continue;
            }
            possiblePositions.add(direction.move(position));
        }
        return possiblePositions;
    }
}
