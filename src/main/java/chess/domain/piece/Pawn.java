package chess.domain.piece;

import chess.domain.attribute.Color;
import chess.domain.attribute.Square;
import chess.domain.chessboard.attribute.Direction;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Pawn extends AbstractPawn {
    public Pawn(final Color color, final Square square) {
        super(color, square);
    }

    @Override
    public Set<Square> movableSquaresFrom(final Square source) {
        Set<Direction> directions = Direction.ofPawn(color);
        return directions.stream()
                .map(source::move)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toUnmodifiableSet());
    }
}
