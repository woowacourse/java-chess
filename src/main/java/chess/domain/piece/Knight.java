package chess.domain.piece;

import chess.domain.Team;
import java.util.List;
import java.util.stream.Collectors;

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

    public static Knight create(Team team, Position position) {
        if (team == Team.BLANK || team == null) {
            throw new IllegalArgumentException("기물은 팀이 있어야 합니다.");
        }
        if (team == Team.BLACK) {
            return createBlack(position);
        }
        return createWhite(position);
    }

    @Override
    public boolean isMovable(Piece piece) {
        return isInRange(piece.getPosition()) && isValidPosition(piece);
    }

    private boolean isInRange(Position targetPosition) {
        List<Position> inRangePosition = Direction.getKnightDirections()
                .stream()
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

    @Override
    public PieceType getType() {
        return PieceType.KNIGHT;
    }
}
