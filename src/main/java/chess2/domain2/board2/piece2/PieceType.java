package chess2.domain2.board2.piece2;

import chess2.domain2.board2.Position;
import chess2.strategy.RouteChecker;

public enum PieceType {

    PAWN((pos1, pos2) -> true), // TODO: should be fixed
    KNIGHT(PieceType::isKnightMovable),
    BISHOP(Position::isDiagonal),
    ROOK(PieceType::isRookMovable),
    QUEEN(Position::isStraightPath),
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

    private static boolean isKnightMovable(Position fromPosition, Position toPosition) {
        int fileDiff = fromPosition.fileDifference(toPosition);
        int rankDiff = toPosition.rankDifference(toPosition);

        return (fileDiff == KNIGHT_SUB_MOVE_DIFF && rankDiff == KNIGHT_MAIN_MOVE_DIFF)
                || (fileDiff == KNIGHT_MAIN_MOVE_DIFF && rankDiff == KNIGHT_SUB_MOVE_DIFF);
    }

    private static boolean isRookMovable(Position fromPosition, Position toPosition)  {
        return fromPosition.isHorizontal(toPosition)
                || fromPosition.isVertical(toPosition);
    }

    private static boolean isKingMovable(Position fromPosition, Position toPosition) {
        int fileDiff = fromPosition.fileDifference(toPosition);
        int rankDiff = toPosition.rankDifference(toPosition);

        return fileDiff <= KING_MAX_MOVE_DIFF && rankDiff <= KING_MAX_MOVE_DIFF;
    }
}
