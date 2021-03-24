package chess.domain.utils;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Pawn;

public class MoveValidator {
    public static void isPieceExist(Board board, Position position) {
        if (board.containsPosition(position)) {
            throw new IllegalArgumentException("[ERROR] 말이 존재합니다.");
        }
    }

    public static void validatePawnLocation(Position source) {
        if (!source.isLocatedAtStartLine()) {
            throw new IllegalArgumentException("[ERROR] 폰은 처음에만 두 칸 이동할 수 있습니다.");
        }
    }

    public static void validateStraightMove(int distance) {
        if (distance > Pawn.MOVE_FIRST_RANGE) {
            throw new IllegalArgumentException("[ERROR] 폰은 두 칸 초과 움직일 수 없습니다.");
        }
    }

    public static void validateDiagonalMove(int distance, int moveRange, boolean target) {
        if (!target) {
            throw new IllegalArgumentException("[ERROR] 폰은 대각선에 상대팀의 말이 있는 경우 한 칸 이동할 수 있습니다.");
        }
        validateDistance(distance, moveRange);
    }

    public static void validateDistance(int distance, int moveRange) {
        if (distance > moveRange) {
            throw new IllegalArgumentException("[ERROR] 이동할 수 있는 거리를 벗어났습니다.");
        }
    }
}
