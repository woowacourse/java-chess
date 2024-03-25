package chess.domain.piece;

import static chess.domain.chessboard.attribute.Direction.DOWN;
import static chess.domain.chessboard.attribute.Direction.UP;

import java.util.HashSet;
import java.util.Set;

import chess.domain.chessboard.Chessboard;
import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Movement;
import chess.domain.piece.attribute.Position;

public class Pawn extends AbstractPawn {

    private static final Set<Movement> POSSIBLE_MOVEMENTS_WHITE = Set.of(Movement.of(UP));
    private static final Set<Movement> POSSIBLE_MOVEMENTS_BLACK = Set.of(Movement.of(DOWN));

    public Pawn(final Color color, final Position position) {
        super(color, position);
    }

    private static Set<Movement> possibleMovementsByColor(final Color color) {
        if (color.isBlack()) {
            return new HashSet<>(POSSIBLE_MOVEMENTS_BLACK);
        }
        return new HashSet<>(POSSIBLE_MOVEMENTS_WHITE);
    }

    private static Set<Movement> possibleAttacksByColor(final Color color) {
        if (color.isBlack()) {
            return new HashSet<>(POSSIBLE_ATTACKS_BLACK);
        }
        return new HashSet<>(POSSIBLE_ATTACKS_WHITE);
    }

    @Override
    public Piece move(final Chessboard chessboard, final Position target) {
        Set<Position> possiblePositions = new HashSet<>();
        possiblePositions.addAll(attackablePositions(chessboard, possibleAttacksByColor(color())));
        possiblePositions.addAll(movablePositions(chessboard, possibleMovementsByColor(color())));
        validateTarget(possiblePositions, target);
        return new Pawn(color(), target);
    }
}
