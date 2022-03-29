package chess2.domain2.board2.piece2;

import chess2.domain2.board2.position.Position;
import chess2.strategy.RouteChecker;
import chess2.util2.PositionUtil;

public enum PieceType {

    PAWN(PieceType::isPawnMovable),
    KNIGHT(PieceType::isKnightMovable),
    BISHOP(PositionUtil::isDiagonal),
    ROOK(PositionUtil::isHorizontalOrVertical),
    QUEEN(PositionUtil::isStraightPath),
    KING(PieceType::isKingMovable);

    private static final int KNIGHT_SUB_MOVE_DIFF = 1;
    private static final int KNIGHT_MAIN_MOVE_DIFF = 2;
    private static final int KING_MAX_MOVE_DIFF = 1;

    private final RouteChecker routeChecker;

    PieceType(RouteChecker routeChecker) {
        this.routeChecker = routeChecker;
    }

    public boolean isMovable(Position from, Position to) {
        return routeChecker.checkMovable(from, to);
    }

    private static boolean isPawnMovable(Position fromPosition, Position toPosition) {
        throw new UnsupportedOperationException("폰의 이동 로직은 별도로 구현해야 합니다.");
    }

    private static boolean isKnightMovable(Position fromPosition, Position toPosition) {
        int fileDiff = fromPosition.fileDifference(toPosition);
        int rankDiff = toPosition.rankDifference(toPosition);

        return (fileDiff == KNIGHT_SUB_MOVE_DIFF && rankDiff == KNIGHT_MAIN_MOVE_DIFF)
                || (fileDiff == KNIGHT_MAIN_MOVE_DIFF && rankDiff == KNIGHT_SUB_MOVE_DIFF);
    }

    private static boolean isKingMovable(Position fromPosition, Position toPosition) {
        int fileDiff = fromPosition.fileDifference(toPosition);
        int rankDiff = toPosition.rankDifference(toPosition);

        return fileDiff <= KING_MAX_MOVE_DIFF && rankDiff <= KING_MAX_MOVE_DIFF;
    }
}
