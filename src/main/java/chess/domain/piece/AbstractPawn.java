package chess.domain.piece;

import static chess.domain.chessboard.attribute.Direction.UP_LEFT;
import static chess.domain.chessboard.attribute.Direction.UP_RIGHT;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import chess.domain.chessboard.Chessboard;
import chess.domain.chessboard.attribute.Square;
import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Movement;
import chess.domain.piece.attribute.Position;

public abstract class AbstractPawn extends UnslidingPiece {

    protected static final Set<Movement> POSSIBLE_ATTACKS = Set.of(
            Movement.of(UP_LEFT),
            Movement.of(UP_RIGHT)
    );

    protected AbstractPawn(final Color color, final Position position) {
        super(color, position);
    }

    protected Set<Position> attackablePositions(final Chessboard chessboard) {
        Set<Position> attackablePositions = new HashSet<>();
        POSSIBLE_ATTACKS.forEach(movement -> addPositionIfPresent(chessboard, movement, attackablePositions));
        return attackablePositions;
    }

    private void addPositionIfPresent(
            final Chessboard chessboard,
            final Movement movement,
            final Set<Position> possiblePositions
    ) {
        Optional<Position> possiblePosition = position().after(movement);
        possiblePosition.ifPresent(position -> addIfAttackable(chessboard, position, possiblePositions));
    }

    private void addIfAttackable(
            final Chessboard chessboard,
            final Position position,
            final Set<Position> possiblePositions
    ) {
        Square square = chessboard.squareIn(position);
        Piece other = square.piece();
        if (color() != other.color()) {
            possiblePositions.add(position);
        }
    }
}
