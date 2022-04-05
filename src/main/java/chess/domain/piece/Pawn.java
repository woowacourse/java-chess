package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    private static final String WHITE_SIGNATURE = "p";
    private static final String BLACK_SIGNATURE = "P";
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
            return isInStraightRange(piece.getPosition(), Direction.SOUTH, Direction.SS);
        }
        return isInStraightRange(piece.getPosition(), Direction.NORTH, Direction.NN);
    }

    private boolean isInStraightRange(Position targetPosition, Direction direction, Direction firstMoveDirection) {
        if (isFirstTurn) {
            return isInRange(targetPosition, List.of(direction, firstMoveDirection));
        }
        return isInRange(targetPosition, List.of(direction));
    }

    private boolean isEnemyTarget(Piece piece) {
        if (isBlack()) {
            return isInRange(piece.getPosition(), List.of(Direction.SOUTHWEST, Direction.SOUTHEAST));
        }
        return isInRange(piece.getPosition(), List.of(Direction.NORTHWEST, Direction.NORTHEAST));
    }

    private boolean isInRange(Position targetPosition, List<Direction> directions) {
        List<Position> possiblePositionInDirection = new ArrayList<>();
        for (Direction direction : directions) {
            addPossiblePosition(direction, possiblePositionInDirection);
        }
        return possiblePositionInDirection.contains(targetPosition);
    }

    private void addPossiblePosition(Direction direction, List<Position> possiblePositionInDirection) {
        try {
            possiblePositionInDirection.add(position.createNextPosition(direction));
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public boolean isPawn() {
        return true;
    }


    @Override
    public String getName() {
        String name = "pawn";
        if (isBlack()) {
            return name + "_black";
        }
        return name + "_white";
    }
}
