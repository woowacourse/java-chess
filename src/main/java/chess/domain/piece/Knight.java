package chess.domain.piece;

import chess.domain.attribute.Color;
import chess.domain.attribute.Square;
import chess.domain.chessboard.attribute.Direction;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Knight extends UnslidingPiece {
    public Knight(final Color color, final Square square) {
        super(color, PieceType.KNIGHT, square);
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
