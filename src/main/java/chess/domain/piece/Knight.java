package chess.domain.piece;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        List<Position> inRangePosition = Stream.of(Direction.NNE, Direction.NNW, Direction.SSE, Direction.SSW,
                        Direction.EEN, Direction.EES, Direction.WWN, Direction.WWS)
                .filter(direction -> Position.isValidPosition(position.createNextPosition(direction)))
                .map(direction -> position.createNextPosition(direction))
                .collect(Collectors.toList());

        return inRangePosition.contains(targetPosition);
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
