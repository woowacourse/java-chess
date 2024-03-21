package chess.domain.piece;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import chess.domain.attribute.Color;
import chess.domain.attribute.Square;
import chess.domain.chessboard.attribute.Direction;

public class Knight extends UnslidingPiece {
    public Knight(final Color color) {
        super(color, PieceType.KNIGHT);
    }

    @Override
    public Set<Square> movableSquaresFrom(final Square source) {
        Set<Set<Direction>> directions = Direction.ofKnight();
        return directions.stream()
                .map(direction -> movableSquare(direction, source))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toUnmodifiableSet());
    }
}
