package chess.domain.piece;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import chess.domain.attribute.Color;
import chess.domain.attribute.Position;
import chess.domain.chessboard.attribute.Direction;

public abstract class SlidingPiece extends Piece {

    protected SlidingPiece(final Color color, final PieceType pieceType) {
        super(color, pieceType);
    }

    protected Set<Position> movablePositionsOf(final Set<Direction> directions, final Position source) {
        Set<Position> positions = new HashSet<>();
        for (final Direction direction : directions) {
            positions.addAll(positionsOf(direction, source));
        }
        return Collections.unmodifiableSet(positions);
    }

    private Set<Position> positionsOf(final Direction direction, final Position source) {
        Set<Position> positions = new HashSet<>();
        Optional<Position> next = source.moveTo(direction);
        while (next.isPresent()) {
            Position position = next.get();
            positions.add(position);
            next = position.moveTo(direction);
        }
        return positions;
    }
}
