package chess.domain.piece;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import chess.domain.attribute.Color;
import chess.domain.attribute.Position;
import chess.domain.chessboard.attribute.Direction;

public class StartingPawn extends AbstractPawn {

    public StartingPawn(final Color color) {
        super(color);
    }

    @Override
    public Set<Position> movablePositionsFrom(final Position source) {
        Set<List<Direction>> directions = Direction.ofStartingPawn(color);
        return directions.stream()
                .map(direction -> movablePosition(direction, source))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toUnmodifiableSet());

    }
}
