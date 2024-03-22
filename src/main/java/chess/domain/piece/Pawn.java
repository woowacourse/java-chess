package chess.domain.piece;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import chess.domain.attribute.Color;
import chess.domain.attribute.Position;
import chess.domain.chessboard.attribute.Direction;

public class Pawn extends AbstractPawn {

    public Pawn(final Color color) {
        super(color);
    }

    @Override
    public Set<Position> movablePositionsFrom(final Position source) {
        Set<Direction> directions = Direction.ofPawn(color);
        return directions.stream()
                .map(source::moveTo)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toUnmodifiableSet());
    }
}
