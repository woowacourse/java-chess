package chess.domain.piece;

import java.util.List;
import java.util.stream.Collectors;

public class King extends Piece {

    private static final String WHITE_SIGNATURE = "k";
    private static final String BLACK_SIGNATURE = "K";

    private King(Position position, String signature) {
        super(position, signature);
    }

    public static King createWhite(Position position) {
        return new King(position, WHITE_SIGNATURE);
    }

    public static King createBlack(Position position) {
        return new King(position, BLACK_SIGNATURE);
    }

    @Override
    public boolean isMovable(Piece piece) {
        return isInRange(piece.getPosition()) && isValidPosition(piece);
    }

    private boolean isInRange(Position targetPosition) {
        List<Position> inRangePosition = Direction.getEightStraightDirections()
                .stream()
                .filter(direction -> Position.isValidPosition(Position.createNextPosition(position, direction)))
                .map(direction -> Position.createNextPosition(position, direction))
                .collect(Collectors.toList());

        return inRangePosition.contains(targetPosition);
    }

    private boolean isValidPosition(Piece piece) {
        return piece.isBlank() || piece.isEnemy(getSignature());
    }
}
