package chess.domain.piece;

import chess.domain.board.Board;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Queen extends Piece {

    private static final String WHITE_SIGNATURE = "q";
    private static final String BLACK_SIGNATURE = "Q";

    private Queen(Position position, String signature) {
        super(position, signature);
    }

    public static Queen createWhite(Position position) {
        return new Queen(position, WHITE_SIGNATURE);
    }

    public static Queen createBlack(Position position) {
        return new Queen(position, BLACK_SIGNATURE);
    }

    @Override
    public boolean isMovable(Piece piece) {
        return isInRange(piece.getPosition()) && isValidPosition(piece);
    }

    private boolean isInRange(Position targetPosition) {
        List<Position> inRangePosition = new ArrayList<>();

        for (Direction direction : Direction.getEightStraightDirections()) {
            List<Position> directionPositions = IntStream.rangeClosed(1, Board.RANK_CAPACITY)
                    .mapToObj(product -> new Position(
                            position.getX() + direction.getXDegree() * product,
                            position.getY() + direction.getYDegree() * product))
                    .filter(position -> Position.isValidPosition(position.getX(), position.getY()))
                    .collect(Collectors.toList());
            inRangePosition.addAll(directionPositions);
        }

        return inRangePosition.contains(targetPosition);
    }

    private boolean isValidPosition(Piece piece) {
        return piece.isBlank() || piece.isEnemy(getSignature());
    }
}
