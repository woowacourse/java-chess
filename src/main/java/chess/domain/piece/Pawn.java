package chess.domain.piece;

import java.util.List;
import java.util.stream.Collectors;

public class Pawn extends Piece {

    private static final String WHITE_SIGNATURE = "p";
    private static final String BLACK_SIGNATURE = "P";
    private static final int FIRST_MOVE_DISTANCE = 2;
    private static final double SCORE = 1.0;

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
        if (piece.isBlank()) {
            return isBlankTarget(piece);
        }
        if (isEnemy(piece.getSignature())) {
            return isEnemyTarget(piece);
        }
        return false;
    }

    private boolean isBlankTarget(Piece piece) {
        if (isBlack()) {
            return isInStraightRange(piece.getPosition(), Direction.SOUTH);
        }
        return isInStraightRange(piece.getPosition(), Direction.NORTH);
    }

    private boolean isInStraightRange(Position targetPosition, Direction direction) {
        if (isFirstTurn) {
            return List.of(Position.createNextPosition(position, direction),
                            Position.createNextPosition(position, direction, FIRST_MOVE_DISTANCE))
                    .contains(targetPosition);
        }
        return Position.createNextPosition(position, direction).equals(targetPosition);
    }

    private boolean isEnemyTarget(Piece piece) {
        if (isBlack()) {
            return isInRange(piece.getPosition(), Direction.getBlackPawnAttackDirections());
        }
        return isInRange(piece.getPosition(), Direction.getWhitePawnAttackDirections());
    }

    private boolean isInRange(Position targetPosition, List<Direction> directions) {
        List<Position> inRangePosition = directions
                .stream()
                .filter(direction -> Position.isValidPosition(Position.createNextPosition(position, direction)))
                .map(direction -> Position.createNextPosition(position, direction))
                .collect(Collectors.toList());

        return inRangePosition.contains(targetPosition);
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
