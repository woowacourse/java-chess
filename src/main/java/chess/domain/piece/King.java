package chess.domain.piece;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import chess.domain.attribute.Color;
import chess.domain.attribute.Square;
import chess.domain.chessboard.attribute.Direction;

public class King extends UnslidingPiece {

    public King(final Color color) {
        super(color, PieceType.KING);
    }

    @Override
    public Set<Square> movableSquaresFrom(final Square source) {
        Set<Direction> directions = Direction.all();
        return directions.stream()
                .map(source::move)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toUnmodifiableSet());
    }
}
