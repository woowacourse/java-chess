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

    protected Set<Position> possiblePositions(final Chessboard chessboard, final Collection<Movement> movements) {
        Set<Position> positions = new HashSet<>();
        movements.forEach(movement -> addPositionIfPresent(chessboard, movement, positions));
        return positions;
    }

    private void addPositionIfPresent(
            final Chessboard chessboard,
            final Movement movement,
            final Set<Position> possiblePositions
    ) {
        Optional<Position> possiblePosition = position().after(movement);
        possiblePosition.ifPresent(position -> addIfEmptyOrAttackable(chessboard, position, possiblePositions));
    }

    private void addIfEmptyOrAttackable(
            final Chessboard chessboard,
            final Position position,
            final Set<Position> possiblePositions
    ) {
        Square square = chessboard.squareIn(position);
        if (addIfEmpty(position, square, possiblePositions)) {
            return;
        }
        addIfAttackable(position, square, possiblePositions);
    }

    private boolean addIfEmpty(
            final Position position,
            final Square square,
            final Set<Position> possiblePositions
    ) {
        if (square.isEmpty()) {
            possiblePositions.add(position);
            return true;
        }
        return false;
    }

    private void addIfAttackable(
            final Position position,
            final Square square,
            final Set<Position> possiblePositions
    ) {
        Piece other = square.piece();
        if (color() != other.color()) {
            possiblePositions.add(position);
        }
    }
}
