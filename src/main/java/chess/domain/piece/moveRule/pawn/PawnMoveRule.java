package chess.domain.piece.moveRule.pawn;

import chess.domain.Direction;
import chess.domain.piece.Color;
import chess.domain.piece.PieceType;
import chess.domain.piece.moveRule.MoveRule;
import chess.domain.position.Position;
import chess.domain.position.Rank;

public class PawnMoveRule implements MoveRule {
    public static final int TWO_SQUARE = 2;
    public static final int ONE_SQUARE = 1;
    private static final Rank WHITE_PAWN_INIT_RANK = Rank.RANK2;
    private static final Rank BLACK_PAWN_INIT_RANK = Rank.RANK7;
    private final Direction direction;

    private PawnMoveRule(Direction direction) {
        this.direction = direction;
    }

    public static PawnMoveRule of(Color color) {
        if (color == Color.BLACK) {
            return new PawnMoveRule(Direction.MINUS);
        }
        return new PawnMoveRule(Direction.PLUS);
    }

    @Override
    public void validateMovement(Position currentPosition, Position nextPosition) {
        validateMoveToEmpty(nextPosition);
        if (isInitPawn(currentPosition) && isForwardDistanceMove(currentPosition, nextPosition, TWO_SQUARE)) {
            return;
        }
        if (isForwardDistanceMove(currentPosition, nextPosition, ONE_SQUARE)) {
            return;
        }
        if (isDiagonalMove(currentPosition, nextPosition)) {
            return;
        }
        throw new IllegalArgumentException("기물이 이동할 수 없는 위치입니다.");
    }

    private boolean isInitPawn(Position currentPosition) {
        if (direction.equals(Direction.PLUS) && currentPosition.isSameRank(WHITE_PAWN_INIT_RANK)) {
            return true;
        }
        return direction.equals(Direction.MINUS) && currentPosition.isSameRank(BLACK_PAWN_INIT_RANK);
    }

    private boolean isForwardDistanceMove(Position currentPosition, Position nextPosition, int distance) {
        Position forwardPosition = currentPosition.moveRank(direction, distance);
        return forwardPosition.equals(nextPosition);
    }

    private boolean isDiagonalMove(Position currentPosition, Position nextPosition) {
        Position leftDiagonal = currentPosition.move(Direction.MINUS, direction);
        Position rightDiagonal = currentPosition.move(Direction.PLUS, direction);
        return leftDiagonal.equals(nextPosition) || rightDiagonal.equals(nextPosition);
    }

    @Override
    public PieceType pieceType() {
        return PieceType.PAWN;
    }
}
