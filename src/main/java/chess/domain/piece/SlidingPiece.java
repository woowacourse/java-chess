package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;

public abstract class SlidingPiece extends Piece {

    public SlidingPiece(Position position, String signature) {
        super(position, signature);
    }

    protected abstract List<Direction> findPossibleDirections();

    @Override
    public boolean isMovable(Piece piece) {
        return isInRange(piece.getPosition(), findPossibleDirections()) && isValidPosition(piece);
    }

    private boolean isInRange(Position targetPosition, List<Direction> directions) {
        List<Position> inRangePosition = new ArrayList<>();
        for (Direction direction : directions) {
            inRangePosition.addAll(
                    findPossiblePositionInDirection(position.calculateStraightDistance(targetPosition), direction));
        }
        return inRangePosition.contains(targetPosition);
    }

    private List<Position> findPossiblePositionInDirection(int distance, Direction direction) {
        List<Position> possiblePositionInDirection = new ArrayList<>();
        for (int product = 1; product <= distance; product++) {
            addPossiblePosition(direction, possiblePositionInDirection, product);
        }
        return possiblePositionInDirection;
    }

    private void addPossiblePosition(Direction direction, List<Position> possiblePositionInDirection, int product) {
        try {
            possiblePositionInDirection.add(position.createNextPosition(direction, product));
        } catch (IllegalArgumentException ignored) {
        }
    }

    private boolean isValidPosition(Piece piece) {
        return piece.isBlank() || piece.isEnemy(getSignature());
    }
}
