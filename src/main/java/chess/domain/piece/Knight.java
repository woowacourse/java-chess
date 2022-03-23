package chess.domain.piece;

import java.util.List;
import java.util.stream.Collectors;

public class Knight extends Piece {

    private static final String WHITE_SIGNATURE = "n";
    private static final String BLACK_SIGNATURE = "N";

    private Knight(Position position, String signature) {
        super(position, signature);
    }

    public static Knight createWhite(Position position) {
        return new Knight(position, WHITE_SIGNATURE);
    }

    public static Knight createBlack(Position position) {
        return new Knight(position, BLACK_SIGNATURE);
    }

    @Override
    public boolean isMovable(Piece piece) {
        return isInRange(piece.getPosition()) && isValidPosition(piece);
    }

    private boolean isInRange(Position targetPosition) {
        List<Position> inRangePosition = Direction.getKnightDirections()
                .stream()
                .filter(direction -> Position.isValidPosition(
                        position.getX() + direction.getXDegree(),
                        position.getY() +direction.getYDegree()))
                .map(direction -> new Position(
                        position.getX() + direction.getXDegree(),
                        position.getY() + direction.getYDegree()))
                .collect(Collectors.toList());

        return inRangePosition.contains(targetPosition);
    }

    private boolean isValidPosition(Piece piece) {
        return piece.isBlank() || piece.isEnemy(getSignature());
    }
}
