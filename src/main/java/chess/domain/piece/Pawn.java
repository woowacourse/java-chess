package chess.domain.piece;

import java.util.List;
import java.util.stream.Collectors;

public class Pawn extends Piece {

    private static final String WHITE_SIGNATURE = "p";
    private static final String BLACK_SIGNATURE = "P";
    private static final int FIRST_MOVE_DISTANCE = 2;

    private Pawn(Position position, String signature) {
        super(position, signature);
    }

    public static Pawn createWhite(Position position) {
        return new Pawn(position, WHITE_SIGNATURE);
    }

    public static Pawn createBlack(Position position) {
        return new Pawn(position, BLACK_SIGNATURE);
    }

    @Override
    public boolean isMovable(Piece piece) {
        if (isBlack() && isEnemy(piece.getSignature())) {
            return isInRange(piece.getPosition(), Direction.getBlackPawnAttackDirections());
        }
        if (!isBlack() && isEnemy(piece.getSignature())) {
            return isInRange(piece.getPosition(), Direction.getWhitePawnAttackDirections());
        }
        if (isBlack() && piece.isBlank()) {
            return isInStraightRange(piece.getPosition(), Direction.SOUTH);
        }
        if (!isBlack() && piece.isBlank()) {
            return isInStraightRange(piece.getPosition(), Direction.NORTH);
        }
        return false;
    }

    private boolean isInRange(Position targetPosition, List<Direction> directions) {
        List<Position> inRangePosition = directions
                .stream()
                .filter(direction -> Position.isValidPosition(Position.createNextPosition(position, direction)))
                .map(direction -> Position.createNextPosition(position, direction))
                .collect(Collectors.toList());

        return inRangePosition.contains(targetPosition);
    }

    private boolean isInStraightRange(Position targetPosition, Direction direction) {
        if (isFirstTurn) {
            return List.of(Position.createNextPosition(position, direction),
                            Position.createNextPosition(position, direction, FIRST_MOVE_DISTANCE))
                    .contains(targetPosition);
        }
        return Position.createNextPosition(position, direction).equals(targetPosition);
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public double getScore() {
        return 1;
    }
}
