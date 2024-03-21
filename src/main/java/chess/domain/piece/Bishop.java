package chess.domain.piece;

import java.util.Collections;
import java.util.List;
import chess.domain.board.Coordinate;
import chess.domain.board.Direction;

public class Bishop extends AbstractPiece {

    private final static List<Direction> POSSIBLE_DIRECTIONS = List.of(
            Direction.LEFT_DOWN,
            Direction.LEFT_UP,
            Direction.RIGHT_DOWN,
            Direction.RIGHT_UP
    );

    public Bishop(Team team) {
        super(PieceType.BISHOP, team);
    }

    @Override
    public List<Coordinate> findMovablePath(Coordinate start, Coordinate destination) {
        return POSSIBLE_DIRECTIONS.stream()
                .map(possibleDirection -> possibleDirection.createPath(start))
                .filter(coordinates -> coordinates.contains(destination))
                .findFirst()
                .orElse(Collections.emptyList());
    }
}
