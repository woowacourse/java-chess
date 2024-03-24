package chess.domain.piece;

import chess.domain.attribute.Color;
import chess.domain.attribute.Square;
import chess.domain.chessboard.attribute.Direction;
import java.util.Collection;
import java.util.Optional;

public abstract class UnslidingPiece extends Piece {
    protected UnslidingPiece(final Color color, final PieceType pieceType, final Square square) {
        super(color, pieceType, square);
    }

    protected Optional<Square> movableSquare(final Collection<Direction> directions, final Square source) {
        return directions.stream()
                .reduce(Optional.of(source),
                        (square, direction) -> square.flatMap(presentSquare -> presentSquare.move(direction)),
                        (current, next) -> next);
    }
}
