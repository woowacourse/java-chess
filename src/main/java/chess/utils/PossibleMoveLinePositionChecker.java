package chess.utils;

import chess.domain.piece.Piece;
import chess.domain.piece.Type;
import chess.domain.position.Position;

import java.util.List;
import java.util.Map;

public class PossibleMoveLinePositionChecker {

    private static final String ERROR_OBSTACLE_EXIST_PIECE = "이동 중간에 가로막는 기물이 있습니다.";
    private static final int SOURCE = 0;
    private static final int TARGET = 1;
    private static final int ROW = 0;
    private static final int COLUMN = 1;

    private PossibleMoveLinePositionChecker() {
    }

    public static boolean isPossibleMovePosition(Piece piece, List<Position> positions, List<List<Integer>> move,
                                                 Map<Position, Piece> board) {
        Position source = positions.get(SOURCE);
        Position target = positions.get(TARGET);

        return move.stream()
                .anyMatch(moveUnit -> findMovableByRecursion(piece, source, target,
                        List.of(moveUnit.get(ROW), moveUnit.get(COLUMN)), board));
    }

    private static boolean findMovableByRecursion(Piece piece, Position source, Position target, List<Integer> moveUnit,
                                                  Map<Position, Piece> board) {
        int row = moveUnit.get(ROW);
        int column = moveUnit.get(COLUMN);
        if (source.isOverRange()) {
            return false;
        }
        Position movePosition = source.findPossiblePosition(row, column);
        if (movePosition.isSameRow(target) && movePosition.isSameColumn(target)) {
            return isBlankDot(board.get(movePosition));
        }
        findMovableByRecursion(piece, movePosition, target, moveUnit, board);
        return false;
    }

    private static boolean isBlankDot(Piece piece) {
        if (!piece.isSameType(Type.BLANK)) {
            throw new IllegalArgumentException(ERROR_OBSTACLE_EXIST_PIECE);
        }
        return true;
    }
}
