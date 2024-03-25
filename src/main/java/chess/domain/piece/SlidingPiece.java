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
        directions.forEach(direction -> positions.addAll(possiblePositionsTo(chessboard, direction)));
        return positions;
    }

    private Set<Position> possiblePositionsTo(final Chessboard chessboard, final Direction direction) {
        Set<Position> positions = new HashSet<>();
        addIfEmptyOrAttackable(chessboard, direction, position(), positions);
        return positions;
    }

    private void addIfEmptyOrAttackable(
            final Chessboard chessboard,
            final Direction direction,
            final Position position,
            final Set<Position> positions
    ) {
        Optional<Position> possiblePosition = position.moveTo(direction);
        if (possiblePosition.isEmpty()) {
            return;
        }
        Position nextPosition = possiblePosition.get();
        Square square = chessboard.squareIn(nextPosition);
        if (addIfEmpty(nextPosition, square, positions)) {
            addIfEmptyOrAttackable(chessboard, direction, nextPosition, positions);
        }
        addIfAttackable(nextPosition, square, positions);
    }

    private boolean addIfEmpty(
            final Position position,
            final Square square,
            final Set<Position> positions
    ) {
        if (square.isEmpty()) {
            positions.add(position);
            return true;
        }
        return false;
    }

    private void addIfAttackable(
            final Position position,
            final Square square,
            final Set<Position> positions
    ) {
        if (square.isEmpty()) {
            return;
        }
        Piece other = square.piece();
        if (color() != other.color()) {
            positions.add(position);
        }
    }
}
