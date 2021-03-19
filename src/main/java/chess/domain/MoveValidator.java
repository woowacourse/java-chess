package chess.domain;

import chess.domain.piece.Piece;

public class MoveValidator {

    public static void validatePawnLocation(Position source) {
        if (!source.isLocatedAtStartLine()) {
            throw new IllegalArgumentException("[ERROR] 폰은 처음에만 두 칸 이동할 수 있습니다.");
        }
    }

    public static void validateDiagonalMove(Board board, Piece piece, Position target, int distance) {
        if (distance >= 2 || !board.hasPieceAt(target) || board.pieceAt(target).isSameTeam(piece.getTeam())) {
            throw new IllegalArgumentException("[ERROR] 폰은 대각선에 상대팀의 말이 있는 경우 한 칸 이동할 수 있습니다.");
        }
    }

    public static void validateStrategyContainsDirection(Direction currentDirection, Strategy strategy) {
        if (!strategy.containsDirection(currentDirection)) {
            throw new IllegalArgumentException("[ERROR] 해당 좌표로 이동할 수 없습니다.");
        }
    }

    public static void validateStraightMove(int distance) {
        if (distance > 2) {
            throw new IllegalArgumentException("[ERROR] 폰은 두 칸 이상 움직일 수 없습니다.");
        }
    }

    public static void validateMoveRange(int distance, int moveRange) {
        if (distance > moveRange) {
            throw new IllegalArgumentException("[ERROR] 이동할 수 있는 거리를 벗어났습니다.");
        }
    }
}
