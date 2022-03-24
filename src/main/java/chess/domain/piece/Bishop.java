package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Bishop extends Piece {

    private static final String WHITE_SIGNATURE = "b";
    private static final String BLACK_SIGNATURE = "B";

    private Bishop(Position position, String signature) {
        super(position, signature);
    }

    public static Bishop createWhite(Position position) {
        return new Bishop(position, WHITE_SIGNATURE);
    }

    public static Bishop createBlack(Position position) {
        return new Bishop(position, BLACK_SIGNATURE);
    }

    @Override
    public boolean isMovable(Piece piece) {
        return isInRange(piece.getPosition()) && isValidPosition(piece);
    }

    private boolean isInRange(Position targetPosition) {
        List<Position> inRangePosition = new ArrayList<>();

        for (Direction direction : Direction.getDiagonalDirections()) {
            List<Position> directionPositions = IntStream
                    .rangeClosed(1, Position.calculateStraightDistance(getPosition(), targetPosition))
                    .mapToObj(product -> Position.createNextPosition(position, direction, product))
                    .filter(Position::isValidPosition)
                    .collect(Collectors.toList());
            inRangePosition.addAll(directionPositions);
        }

        return inRangePosition.contains(targetPosition);
    }

    private boolean isValidPosition(Piece piece) {
        return piece.isBlank() || piece.isEnemy(getSignature());
    }
}
