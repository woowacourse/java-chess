package chess.domain.piece;

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

    protected Set<Position> movablePositions(final Chessboard chessboard, final Set<Movement> movements) {
        Set<Position> positions = new HashSet<>();
        for (final Movement movement : movements) {
            movablePosition(chessboard, movement).ifPresent(positions::add);
        }
        return positions;
    }

    private Optional<Position> movablePosition(final Chessboard chessboard, final Movement movement) {
        Optional<Position> possiblePosition = position().after(movement);
        if (possiblePosition.isEmpty()) {
            return Optional.empty();
        }
        Position position = possiblePosition.get();
        Square square = chessboard.squareIn(position);
        if (square.isEmpty() || isAttackable(square)) {
            return Optional.of(position);
        }
        return Optional.empty();
    }
}
