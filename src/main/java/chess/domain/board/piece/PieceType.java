package chess.domain.board.piece;

import chess.domain.board.position.Position;
import chess.util.PositionUtil;
import java.util.function.BiPredicate;

public enum PieceType {

    PAWN(PieceType::isPawnMovable, 1),
    KNIGHT(PieceType::isKnightMovable, 2.5),
    BISHOP(PositionUtil::isDiagonal, 3),
    ROOK(PositionUtil::isHorizontalOrVertical, 5),
    QUEEN(PositionUtil::isStraightPath, 9),
    KING(PieceType::isKingMovable, 0);

    private static final int KNIGHT_TOTAL_MOVE_DIFF = 3;
    private static final int KNIGHT_MAIN_MOVE_DIFF = 2;
    private static final int KING_MAX_MOVE_DIFF = 1;
    private static final int NO_DIFFERENCE = 0;

    private static final String NON_PAWN_ONLY_EXCEPTION_MESSAGE = "폰의 이동 로직은 별도로 구현해야 합니다.";

    private final BiPredicate<Position, Position> routeChecker;
    private final double score;

    PieceType(BiPredicate<Position, Position> routeChecker, double score ) {
        this.routeChecker = routeChecker;
        this.score = score;
    }

    public boolean isMovable(Position from, Position to) {
        return routeChecker.test(from, to);
    }

    private static boolean isPawnMovable(Position from, Position to) {
        throw new UnsupportedOperationException(NON_PAWN_ONLY_EXCEPTION_MESSAGE);
    }

    private static boolean isKnightMovable(Position from, Position to) {
        int x = from.fileDistance(to);
        int y = from.rankDistance(to);

        if (x + y != KNIGHT_TOTAL_MOVE_DIFF) {
            return false;
        }
        return x == KNIGHT_MAIN_MOVE_DIFF || y == KNIGHT_MAIN_MOVE_DIFF;
    }

    private static boolean isKingMovable(Position from, Position to) {
        int x = from.fileDistance(to);
        int y = from.rankDistance(to);

        if (x == NO_DIFFERENCE && y == NO_DIFFERENCE) {
            return false;
        }
        return x <= KING_MAX_MOVE_DIFF && y <= KING_MAX_MOVE_DIFF;
    }

    public double getScore() {
        return score;
    }
}
