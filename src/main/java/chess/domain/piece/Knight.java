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

    public void move(Piece piece) {
        if (isInRange(piece) && isValidPosition(piece)) {
            position = piece.getPosition();
            return;
        }
        throw new IllegalArgumentException("이동이 불가능한 위치입니다.");
    }

    private boolean isInRange(Piece piece) {
        Position targetPosition = piece.getPosition();
        List<Position> inRangePosition = Direction.getKnightDirections()
                .stream()
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
