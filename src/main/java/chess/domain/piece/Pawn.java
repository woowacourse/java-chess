package chess.domain.piece;

import chess.domain.Team;
import java.util.List;
import java.util.stream.Collectors;

public class Pawn extends Piece {

    private static final String WHITE_SIGNATURE = "p";
    private static final String BLACK_SIGNATURE = "P";
    private static final int FIRST_MOVE_DISTANCE = 2;
    private static final double SCORE = 1.0;

    private boolean isFirstTurn;

    private Pawn(Position position, String signature, boolean isFirstTurn) {
        super(position, signature);
        this.isFirstTurn = isFirstTurn;
    }

    public static Pawn createWhite(Position position, boolean isFirstTurn) {
        return new Pawn(position, WHITE_SIGNATURE, isFirstTurn);
    }

    public static Pawn createBlack(Position position, boolean isFirstTurn) {
        return new Pawn(position, BLACK_SIGNATURE, isFirstTurn);
    }

    public static Pawn create(Team team, Position position, boolean isFirstTurn) {
        if (team == Team.BLANK || team == null) {
            throw new IllegalArgumentException("기물은 팀이 있어야 합니다.");
        }
        if (team == Team.BLACK) {
            return createBlack(position, isFirstTurn);
        }
        return createWhite(position, isFirstTurn);
    }

    @Override
    public boolean isMovable(Piece piece) {
        if (piece.isBlank()) {
            return isBlankTarget(piece);
        }
        if (isEnemy(piece.getSignature())) {
            return isEnemyTarget(piece);
        }
        return false;
    }

    private boolean isBlankTarget(Piece piece) {
        if (isBlack()) {
            return isInStraightRange(piece.getPosition(), Direction.SOUTH);
        }
        return isInStraightRange(piece.getPosition(), Direction.NORTH);
    }

    private boolean isInStraightRange(Position targetPosition, Direction direction) {
        if (isFirstTurn) {
            return List.of(position.createNextPosition(direction),
                            position.createNextPosition(direction, FIRST_MOVE_DISTANCE))
                    .contains(targetPosition);
        }
        return position.createNextPosition(direction).equals(targetPosition);
    }

    private boolean isEnemyTarget(Piece piece) {
        if (isBlack()) {
            return isInRange(piece.getPosition(), Direction.getBlackPawnAttackDirections());
        }
        return isInRange(piece.getPosition(), Direction.getWhitePawnAttackDirections());
    }

    private boolean isInRange(Position targetPosition, List<Direction> directions) {
        List<Position> inRangePosition = directions
                .stream()
                .filter(direction -> Position.isValidPosition(position.createNextPosition(direction)))
                .map(direction -> position.createNextPosition(direction))
                .collect(Collectors.toList());

        return inRangePosition.contains(targetPosition);
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public void updatePosition(Position position) {
        super.updatePosition(position);
        this.isFirstTurn = false;
    }

    @Override
    public PieceType getType() {
        return PieceType.PAWN;
    }

    @Override
    public boolean isFirstTurn() {
        return isFirstTurn;
    }
}
