package chess.domain.piece;

import chess.domain.attribute.Color;
import chess.domain.attribute.Square;
import chess.domain.chessboard.attribute.Direction;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public abstract class SlidingPiece extends Piece {
    protected SlidingPiece(final Color color, final PieceType pieceType, final Square square) {
        super(color, pieceType, square);
    }

    protected Set<Square> movableSquaresOf(final Set<Direction> directions, final Square source) {
        Set<Square> squares = new HashSet<>();
        for (final Direction direction : directions) {
            Optional<Square> next = source.move(direction);
            while (next.isPresent()) {
                Square square = next.get();
                squares.add(square);
                next = square.move(direction);
            }
        }
        return Collections.unmodifiableSet(squares);
    }
}
