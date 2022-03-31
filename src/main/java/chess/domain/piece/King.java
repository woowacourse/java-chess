package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    private static final String WHITE_SIGNATURE = "k";
    private static final String BLACK_SIGNATURE = "K";
    private static final double SCORE = 0.0;

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
        List<Position> possiblePositionInDirection = new ArrayList<>();
        for (Direction direction : Direction.getEightStraightDirections()) {
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

    private boolean isValidPosition(Piece piece) {
        return piece.isBlank() || piece.isEnemy(getSignature());
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
