package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.util.Direction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WhitePawn extends Pawn {
    private static final List<Direction> DIRECTIONS = new ArrayList<>(
            Arrays.asList(
                    Direction.NORTH
//                    Direction.NE,
//                    Direction.NW
            )
    );

    public WhitePawn(char representation, Position position) {
        super(representation, position);
    }


    @Override
    public List<Position> getPossiblePositions() {
        List<Position> possiblePositions = new ArrayList<>();
        if (representation == 'p') {
            for (Direction direction : DIRECTIONS) {
                if (direction.move(position).getX() > 8 | direction.move(position).getX() < 1 | direction.move(position).getY() > 8 |
                        direction.move(position).getY() < 1) {
                    break;
                }
                if (direction == Direction.NORTH && position.getY() == 2) {
                    Position movedPosition = direction.move(position);
                    possiblePositions.add(movedPosition);
                    possiblePositions.add(direction.move(movedPosition));
                    continue;
                }
                possiblePositions.add(direction.move(position));
            }
        }
        return possiblePositions;
    }
}
