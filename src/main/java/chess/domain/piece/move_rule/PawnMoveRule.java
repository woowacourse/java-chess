package chess.domain.piece.move_rule;

import chess.domain.Direction;
import chess.domain.piece.Color;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.Collections;
import java.util.List;

public class PawnMoveRule implements MoveRule {
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
    public List<Position> move(Position currentPosition, Position nextPosition) {
        Position forwardOnePosition = currentPosition.move(Direction.ZERO, direction);

        if (isTwoForwardMove(currentPosition, nextPosition)) {
            return List.of(forwardOnePosition);
        }
        if (!currentPosition.isNear(nextPosition)) {
            throw new IllegalArgumentException("폰이 갈 수 없는 위치입니다. 거리가 멉니다.");
        }
        if (!forwardOnePosition.isSameRank(nextPosition)) {
            throw new IllegalArgumentException("해당 진영의 폰이 갈 수 없는 방향입니다.");
        }
        return Collections.emptyList();
    }

    private boolean isTwoForwardMove(Position currentPosition, Position nextPosition) {
        return isInitialPosition(currentPosition) && isTwoSquareForwardMove(currentPosition, nextPosition);
    }

    @Override
    public PieceType pieceType() {
        return PieceType.PAWN;
    }

    @Override
    public boolean isPawnMove() {
        return true;
    }

    private boolean isInitialPosition(Position currentPosition) {
        if (direction.equals(Direction.PLUS) && currentPosition.isSameRank(WHITE_PAWN_INIT_RANK)) {
            return true;
        }
        if (direction.equals(Direction.MINUS) && currentPosition.isSameRank(BLACK_PAWN_INIT_RANK)) {
            return true;
        }
        return false;
    }

    private boolean isTwoSquareForwardMove(Position currentPosition, Position nextPosition) {
        Position forwardTwoPosition = currentPosition.move(Direction.ZERO, direction).move(Direction.ZERO, direction);
        if (forwardTwoPosition.equals(nextPosition)) {
            return true;
        }
        return false;
    }
}
