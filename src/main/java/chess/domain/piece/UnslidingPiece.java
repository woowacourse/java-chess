package chess.domain.piece;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import chess.domain.attribute.Color;
import chess.domain.attribute.Square;
import chess.domain.chessboard.attribute.Direction;

public abstract class UnslidingPiece extends Piece {
    protected UnslidingPiece(final Color color, final PieceType pieceType) {
        super(color, pieceType);
    }

    protected Set<Square> movableSquaresOf(final Set<Direction> directions, final Square source) {
        return directions.stream()
                .map(source::move)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toUnmodifiableSet());
    }
}
