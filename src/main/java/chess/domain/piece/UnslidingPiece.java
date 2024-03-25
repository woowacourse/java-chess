package chess.domain.piece;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import chess.domain.chessboard.Chessboard;
import chess.domain.chessboard.attribute.Square;
import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Movement;
import chess.domain.piece.attribute.Position;

public abstract class UnslidingPiece extends Piece {

    protected UnslidingPiece(final Color color, final Position position) {
        super(color, position);
    }

    protected Set<Position> movablePositions(final Chessboard chessboard, final Collection<Movement> movements) {
        Set<Position> positions = new HashSet<>();
        movements.forEach(movement -> addPositionIfPresent(chessboard, movement, positions));
        return positions;
    }

    private void addPositionIfPresent(
            final Chessboard chessboard,
            final Movement movement,
            final Set<Position> positions
    ) {
        Optional<Position> possiblePosition = position().after(movement);
        possiblePosition.ifPresent(position -> addIfEmptyOrAttackable(chessboard, position, positions));
    }

    private void addIfEmptyOrAttackable(
            final Chessboard chessboard,
            final Position position,
            final Set<Position> positions
    ) {
        Square square = chessboard.squareIn(position);
        if (addIfEmpty(position, square, positions)) {
            return;
        }
        addIfAttackable(position, square, positions);
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
        Piece other = square.piece();
        if (color() != other.color()) {
            positions.add(position);
        }
    }
}
