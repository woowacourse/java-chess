package chess.domain.board.piece;

import chess.domain.board.position.Position;
import chess.strategy.RouteChecker;
import chess.util.PositionUtil;

public enum PieceType {

    PAWN(PieceType::isPawnMovable),
    KNIGHT(PieceType::isKnightMovable),
    BISHOP(PositionUtil::isDiagonal),
    ROOK(PositionUtil::isHorizontalOrVertical),
    QUEEN(PositionUtil::isStraightPath),
    KING(PieceType::isKingMovable);

    private static final int KNIGHT_TOTAL_MOVE_DIFF = 3;
    private static final int KNIGHT_MAIN_MOVE_DIFF = 2;
    private static final int KING_MAX_MOVE_DIFF = 1;

    private static final String NON_PAWN_ONLY_EXCEPTION_MESSAGE = "폰의 이동 로직은 별도로 구현해야 합니다.";
    private static final int NO_DIFFERENCE = 0;

    private final RouteChecker routeChecker;

    PieceType(RouteChecker routeChecker) {
        this.routeChecker = routeChecker;
    }

    public boolean isMovable(Position from, Position to) {
        return routeChecker.checkMovable(from, to);
    }

    private static boolean isPawnMovable(Position from, Position to) {
        throw new UnsupportedOperationException(NON_PAWN_ONLY_EXCEPTION_MESSAGE);
    }

    private static boolean isKnightMovable(Position from, Position to) {
        int x = from.fileDifference(to);
        int y = from.rankDifference(to);

        if (x + y != KNIGHT_TOTAL_MOVE_DIFF) {
            return false;
        }
        return x == KNIGHT_MAIN_MOVE_DIFF || y == KNIGHT_MAIN_MOVE_DIFF;
    }

    private static boolean isKingMovable(Position from, Position to) {
        int x = from.fileDifference(to);
        int y = from.rankDifference(to);

        if (x == NO_DIFFERENCE && y == NO_DIFFERENCE) {
            return false;
        }
        return x <= KING_MAX_MOVE_DIFF && y <= KING_MAX_MOVE_DIFF;
    }
}
