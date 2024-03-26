package chess.domain.piece;

import static chess.domain.chessboard.attribute.Direction.DOWN_LEFT;
import static chess.domain.chessboard.attribute.Direction.DOWN_RIGHT;
import static chess.domain.chessboard.attribute.Direction.UP_LEFT;
import static chess.domain.chessboard.attribute.Direction.UP_RIGHT;

import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import chess.domain.chessboard.Chessboard;
import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Movement;
import chess.domain.piece.attribute.Position;

public abstract class AbstractPawn extends UnslidingPiece {

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

    protected Set<Position> movablePositions(
            final Chessboard chessboard,
            final Set<Movement> movements
    ) {
        return Stream.concat(
                possiblePositions(movements, chessboard::isEmpty).stream(),
                attackablePositions(chessboard).stream()
        ).collect(Collectors.toUnmodifiableSet());
    }

    protected Set<Position> attackablePositions(final Chessboard chessboard) {
        return possiblePositions(
                selectByColor(color(), POSSIBLE_ATTACKS_WHITE, POSSIBLE_ATTACKS_BLACK),
                position -> isAttackable(chessboard.squareIn(position))
        );
    }

    private Set<Position> possiblePositions(final Set<Movement> movements, final Predicate<Position> predicate) {
        return movements.stream()
                .map(movement -> position().after(movement))
                .flatMap(Optional::stream)
                .filter(predicate)
                .collect(Collectors.toUnmodifiableSet());
    }
}
