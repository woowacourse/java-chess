package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.util.Direction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Knight extends Piece {
    private static final List<Direction> DIRECTIONS = new ArrayList<>(
            Arrays.asList(
                    Direction.NNE,
                    Direction.EEN,
                    Direction.EES,
                    Direction.SSE,
                    Direction.SSW,
                    Direction.WWS,
                    Direction.WWN,
                    Direction.NNW
            )
    );

    public Knight(char representation, Team team, Position position) {
        super(representation, team, position);
    }

    @Override
    public List<Position> getPossiblePositions(Board board) {
        List<Position> possiblePositions = new ArrayList<>();
        for (Direction direction : DIRECTIONS) {
            if (direction.move(position).getX() > 8 | direction.move(position).getX() < 1 | direction.move(position).getY() > 8 |
                    direction.move(position).getY() < 1) {
                break;
            }
            possiblePositions.add(direction.move(position));
        }
        return possiblePositions;
    }
}
