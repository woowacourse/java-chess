package chess.domain.piece;

import chess.domain.attribute.Color;
import chess.domain.attribute.Square;
import chess.domain.chessboard.attribute.Direction;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class StartingPawn extends AbstractPawn {

    public StartingPawn(final Color color) {
        super(color);
    }

    @Override
    public Set<Square> movableSquaresFrom(final Square source) {
        Set<List<Direction>> directions = Direction.ofStartingPawn(color);
        return directions.stream()
                .map(direction -> movableSquare(direction, source))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toUnmodifiableSet());

    }
}
