package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.grid.Grid;
import chess.domain.position.Position;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Rook extends Piece {
    private static final char NAME_WHEN_BLACK = 'R';
    private static final char NAME_WHEN_WHITE = 'r';
    private static final int LINE_COUNT = 8;

    public Rook(final Boolean isBlack, final char x, final char y) {
        super(isBlack, x, y);
    }

    @Override
    List<Position> extractMovablePositions() {
        return Direction.linearDirection()
                .stream()
                .flatMap(direction -> IntStream
                        .rangeClosed(1, LINE_COUNT)
                        .mapToObj(index -> getPosition().moved(
                                direction.getXDegree() * index,
                                direction.getYDegree() * index
                        ))
                        .filter(position -> !isOutOfRange(position) && Grid.isOccupied(position)))
                .collect(Collectors.toList());
    }

    @Override
    char getName() {
        if (isBlack()) {
            return NAME_WHEN_BLACK;
        }
        return NAME_WHEN_WHITE;
    }
}