package chess.domain.piece;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Movement;
import chess.domain.piece.attribute.Position;

public abstract class UnslidingPiece extends Piece {

    protected UnslidingPiece(final Color color, final Position position) {
        super(color, position);
    }

    protected Set<Position> possiblePositionsAfter(final Collection<Movement> movements) {
        return movements.stream()
                .map(position()::after)
                .flatMap(Optional::stream)
                .collect(Collectors.toUnmodifiableSet());
    }
}
