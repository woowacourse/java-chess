package chess.domain;

import chess.domain.piece.Direction;
import chess.domain.piece.DirectionStrategy;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class MoveValidator {

    public static void validateStrategyContainsDirection(Direction currentDirection,
        DirectionStrategy directionStrategy) {
        if (!directionStrategy.containsDirection(currentDirection)) {
            throw new IllegalArgumentException("[ERROR] 이 말은 해당 방향으로 이동할 수 없습니다.");
        }
    }

    public static void validateMoveRange(int moveRange, int targetMove) {
        if (moveRange < targetMove) {
            throw new IllegalArgumentException("[ERROR] 이동할 수 있는 거리를 벗어났습니다.");
        }
    }

    public static void validatePawnCondition(Board board, Position target, Direction direction) {
        if (!board.hasPieceAt(target)) {
            MoveValidator.validateTargetIsBlank(direction);
            return;
        }
        if (Direction.isLinearDirection(direction)) {
            throw new IllegalArgumentException("[ERROR] 폰은 대각선 방향으로만 상대 말을 먹을 수 있습니다.");
        }
    }

    public static void validateTargetIsBlank(Direction direction) {
        if (Direction.isDiagonalDirection(direction)) {
            throw new IllegalArgumentException("[ERROR] 폰은 상대방 말이 없는 대각선 방향으로는 이동할 수 없습니다.");
        }
    }

    public static void validatePawnLocation(Position source) {
        if (!source.isLocatedAtStartLine()) {
            throw new IllegalArgumentException("[ERROR] 폰은 처음에만 두 칸 이동할 수 있습니다.");
        }
    }

    public static void validateDiagonalMove(Board board, Piece piece, Position target,
        int distance) {
        if (distance >= 2 || !board.hasPieceAt(target) || board.pieceAt(target)
            .isSameTeam(piece.getTeam())) {
            throw new IllegalArgumentException("[ERROR] 폰은 대각선에 상대팀의 말이 있는 경우 한 칸 이동할 수 있습니다.");
        }
    }

    public static void validateStraightMove(int distance) {
        if (distance > 2) {
            throw new IllegalArgumentException("[ERROR] 폰은 두 칸 이상 움직일 수 없습니다.");
        }
    }
}
