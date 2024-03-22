package chess.domain.piece;

import java.util.Collection;
import java.util.Optional;

import chess.domain.attribute.Color;
import chess.domain.attribute.Position;
import chess.domain.chessboard.attribute.Direction;

public abstract class UnslidingPiece extends Piece {

    protected UnslidingPiece(final Color color, final PieceType pieceType) {
        super(color, pieceType);
    }

    protected Optional<Position> movablePosition(final Collection<Direction> directions, final Position source) {
        return directions.stream()
                .reduce(Optional.of(source),
                        (position, direction) -> position.flatMap(presentPosition -> presentPosition.moveTo(direction)),
                        (current, next) -> next);
    }
}
