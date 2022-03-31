package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    private static final String WHITE_SIGNATURE = "n";
    private static final String BLACK_SIGNATURE = "N";
    private static final double SCORE = 2.5;

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
        List<Position> possiblePositionInDirection = new ArrayList<>();
        for (Direction direction : List.of(Direction.NNE, Direction.NNW, Direction.SSE, Direction.SSW,
                Direction.EEN, Direction.EES, Direction.WWN, Direction.WWS)) {
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
    public boolean isKnight() {
        return true;
    }
}
