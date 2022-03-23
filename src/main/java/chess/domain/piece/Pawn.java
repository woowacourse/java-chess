package chess.domain.piece;

import java.util.List;
import java.util.stream.Collectors;

public class Pawn extends Piece {

    private static final String WHITE_SIGNATURE = "p";
    private static final String BLACK_SIGNATURE = "P";

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
        if (isBlackPawn() && piece.isEnemy(getSignature())) {
            return isInRange(piece.getPosition(), Direction.getBlackPawnAttackDirections());
        }
        if (!isBlackPawn() && piece.isEnemy(getSignature())) {
            return isInRange(piece.getPosition(), Direction.getWhitePawnAttackDirections());
        }
        if (isBlackPawn() && piece.isBlank()) {
            return isInStraightRange(piece.getPosition(), Direction.SOUTH);
        }
        if (!isBlackPawn() && piece.isBlank()) {
            return isInStraightRange(piece.getPosition(), Direction.NORTH);
        }
        return false;
    }

    private boolean isInRange(Position targetPosition, List<Direction> directions) {
        List<Position> inRangePosition = directions
                .stream()
                .filter(direction -> Position.isValidPosition(
                        position.getX() + direction.getXDegree(),
                        position.getY() + direction.getYDegree()))
                .map(direction -> new Position(
                        position.getX() + direction.getXDegree(),
                        position.getY() + direction.getYDegree()))
                .collect(Collectors.toList());

        return inRangePosition.contains(targetPosition);
    }

    private boolean isInStraightRange(Position targetPosition, Direction direction) {
        if (isFirstTurn) {
            return List.of(new Position(
                                    position.getX() + direction.getXDegree(),
                                    position.getY() + direction.getYDegree()),
                            new Position(
                                    position.getX() + direction.getXDegree() * 2,
                                    position.getY() + direction.getYDegree() * 2))
                    .contains(targetPosition);
        }
        return new Position(
                position.getX() + direction.getXDegree(),
                position.getY() + direction.getYDegree()).equals(targetPosition);
    }

    private boolean isBlackPawn() {
        return getSignature().equals(BLACK_SIGNATURE);
    }
}
