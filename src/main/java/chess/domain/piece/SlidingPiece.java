package chess.domain.piece;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import chess.domain.chessboard.Chessboard;
import chess.domain.chessboard.attribute.Direction;
import chess.domain.chessboard.attribute.Square;
import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Position;

public abstract class SlidingPiece extends Piece {

    protected SlidingPiece(final Color color, final Position position) {
        super(color, position);
    }

    protected Set<Position> movablePositions(final Chessboard chessboard, final Set<Direction> directions) {
        Set<Position> positions = new HashSet<>();
        for (Direction direction : directions) {
            positions.addAll(movablePositionsOf(chessboard, direction, position()));
        }
        return positions;
    }

    private Set<Position> movablePositionsOf(
            final Chessboard chessboard,
            final Direction direction,
            final Position position
    ) {
        Optional<Position> possiblePosition = position.moveTo(direction);
        if (possiblePosition.isEmpty()) {
            return Set.of();
        }
        Position nextPosition = possiblePosition.get();
        return concatMovablePositions(chessboard, direction, nextPosition);
    }

    private Set<Position> concatMovablePositions(
            final Chessboard chessboard,
            final Direction direction,
            final Position position
    ) {
        Square square = chessboard.squareIn(position);
        if (square.isEmpty()) {
            Set<Position> positions = new HashSet<>(movablePositionsOf(chessboard, direction, position));
            positions.add(position);
            return positions;
        }
        if (isAttackable(square)) {
            return Set.of(position);
        }
        return Set.of();
    }
}
