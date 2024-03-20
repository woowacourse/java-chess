package chess.domain.piece;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import chess.domain.attribute.Color;
import chess.domain.attribute.Square;
import chess.domain.chessboard.attribute.Direction;

public class Pawn extends AbstractPawn {
    protected Pawn(final Color color) {
        super(color);
    }

    @Override
    public Set<Square> movableSquares(final Square currentSquare) {
        Set<Direction> directions = Direction.ofPawn();
        return directions.stream()
                .map(currentSquare::move)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toUnmodifiableSet());
    }
}
