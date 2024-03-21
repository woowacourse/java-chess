package chess.domain.piece;

import java.util.Collections;
import java.util.List;
import chess.domain.board.Coordinate;
import chess.domain.board.Direction;

public class Rook extends AbstractPiece {

    private static final List<Direction> POSSIBLE_DIRECTIONS = List.of(
            Direction.UP,
            Direction.DOWN,
            Direction.LEFT,
            Direction.RIGHT
    );

    public Rook(Team team) {
        super(PieceType.ROOK, team);
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
