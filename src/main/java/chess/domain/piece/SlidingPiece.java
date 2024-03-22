package chess.domain.piece;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import chess.domain.attribute.Color;
import chess.domain.attribute.Square;
import chess.domain.chessboard.attribute.Direction;

public abstract class SlidingPiece extends Piece {

    protected SlidingPiece(final Color color, final PieceType pieceType) {
        super(color, pieceType);
    }

    protected Set<Square> movableSquaresOf(final Set<Direction> directions, final Square source) {
        Set<Square> squares = new HashSet<>();
        for (final Direction direction : directions) {
            squares.addAll(squaresOf(direction, source));
        }
        return Collections.unmodifiableSet(squares);
    }

    private Set<Square> squaresOf(final Direction direction, final Square source) {
        Set<Square> squares = new HashSet<>();
        Optional<Square> next = source.move(direction);
        while (next.isPresent()) {
            Square square = next.get();
            squares.add(square);
            next = square.move(direction);
        }
        return squares;
    }
}
