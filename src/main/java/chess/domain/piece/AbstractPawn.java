package chess.domain.piece;

import static chess.domain.chessboard.attribute.Direction.DOWN_LEFT;
import static chess.domain.chessboard.attribute.Direction.DOWN_RIGHT;
import static chess.domain.chessboard.attribute.Direction.UP_LEFT;
import static chess.domain.chessboard.attribute.Direction.UP_RIGHT;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import chess.domain.chessboard.Chessboard;
import chess.domain.chessboard.attribute.Square;
import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Movement;
import chess.domain.piece.attribute.Position;

public abstract class AbstractPawn extends UnslidingPiece {

    private interface PositionConsumer {
        void accept(Chessboard chessboard, Position position, Set<Position> positions);
    }

    protected static final Set<Movement> POSSIBLE_ATTACKS_WHITE = Set.of(
            Movement.of(UP_LEFT),
            Movement.of(UP_RIGHT)
    );
    protected static final Set<Movement> POSSIBLE_ATTACKS_BLACK = Set.of(
            Movement.of(DOWN_LEFT),
            Movement.of(DOWN_RIGHT)
    );

    protected AbstractPawn(final Color color, final Position position) {
        super(color, position);
    }

    @Override
    protected Set<Position> movablePositions(final Chessboard chessboard, final Collection<Movement> movements) {
        return possiblePositions(chessboard, movements, this::addIfEmpty);
    }

    protected Set<Position> attackablePositions(final Chessboard chessboard, final Collection<Movement> movements) {
        return possiblePositions(chessboard, movements, this::addIfAttackable);
    }

    private Set<Position> possiblePositions(
            final Chessboard chessboard,
            final Collection<Movement> movements,
            final PositionConsumer consumer
    ) {
        Set<Position> positions = new HashSet<>();
        movements.forEach(movement -> {
            Optional<Position> possiblePosition = position().after(movement);
            possiblePosition.ifPresent(position -> consumer.accept(chessboard, position, positions));
        });
        return positions;
    }

    private void addIfEmpty(
            final Chessboard chessboard,
            final Position position,
            final Set<Position> possiblePositions
    ) {
        Square square = chessboard.squareIn(position);
        if (square.isEmpty()) {
            possiblePositions.add(position);
        }
    }

    private void addIfAttackable(
            final Chessboard chessboard,
            final Position position,
            final Set<Position> possiblePositions
    ) {
        Square square = chessboard.squareIn(position);
        if (isAttackable(square)) {
            possiblePositions.add(position);
        }
    }
}
