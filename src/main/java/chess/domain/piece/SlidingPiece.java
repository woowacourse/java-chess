package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class SlidingPiece extends Piece {

    public SlidingPiece(Position position, String signature) {
        super(position, signature);
    }

    protected abstract List<Direction> findPossibleDirections();

    @Override
    public boolean isMovable(Piece piece) {
        return isInRange(piece.getPosition()) && isValidPosition(piece);
    }

    private boolean isInRange(Position targetPosition) {
        List<Position> inRangePosition = new ArrayList<>();
        addPossibleTargetPositions(targetPosition, inRangePosition);
        return inRangePosition.contains(targetPosition);
    }

    private void addPossibleTargetPositions(Position targetPosition, List<Position> inRangePosition) {
        for (Direction direction : findPossibleDirections()) {
            List<Position> directionPositions = IntStream.rangeClosed(1,
                            position.calculateStraightDistance(targetPosition))
                    .mapToObj(product -> Position.createNextPosition(position, direction, product))
                    .filter(Position::isValidPosition)
                    .collect(Collectors.toList());
            inRangePosition.addAll(directionPositions);
        }
    }

    private boolean isValidPosition(Piece piece) {
        return piece.isBlank() || piece.isEnemy(getSignature());
    }
}
