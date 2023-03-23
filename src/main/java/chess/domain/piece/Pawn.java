package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.position.RankCoordinate;

public class Pawn extends Piece {

    private static final int MOVABLE_DISTANCE = 1;
    private static final int INITIAL_MOVABLE_DISTANCE = 2;
    private static final int VALID_STRAIGHT_GAP = 0;
    public static final int BLACK_DIRECTION = 1;
    public static final int WHITE_DIRECTION = -1;
    public static final RankCoordinate WHITE_PAWN_INIT_RANK = RankCoordinate.TWO;
    public static final RankCoordinate BLACK_PAWN_INIT_RANK = RankCoordinate.SEVEN;


    public Pawn(Color color) {
        super(color, RoleType.PAWN);
    }

    @Override
    protected boolean validMove(Position sourcePosition, Position targetPosition, Color targetColor) {
        return isStraightPawnMove(sourcePosition, targetPosition, targetColor) ||
                isDiagonalPawnMove(sourcePosition, targetPosition, targetColor);
    }

    private boolean isStraightPawnMove(Position sourcePosition, Position targetPosition, Color targetColor) {
        if (sourcePosition.calculateFileGap(targetPosition) != VALID_STRAIGHT_GAP ||
                getColor().isOpposite(targetColor)) {
            return false;
        }
        int rankGap = sourcePosition.calculateRankGap(targetPosition);
        return canMoveStraightOne(rankGap) || canMoveStraightTwo(rankGap, sourcePosition);
    }

    private boolean canMoveStraightOne(int rankGap) {
        return rankGap == MOVABLE_DISTANCE * getDirection();
    }

    private boolean canMoveStraightTwo(int rankGap, Position sourcePosition) {
        return rankGap == INITIAL_MOVABLE_DISTANCE * getDirection() && isFirstMove(sourcePosition);
    }

    private boolean isDiagonalPawnMove(Position sourcePosition, Position targetPosition, Color color) {
        int rankGap = sourcePosition.calculateRankGap(targetPosition);
        int fileGap = Math.abs(sourcePosition.calculateFileGap(targetPosition));
        boolean isOpponent = getColor().isOpposite(color);
        return rankGap == MOVABLE_DISTANCE * getDirection() && fileGap == MOVABLE_DISTANCE && isOpponent;
    }

    private int getDirection() {
        if (getColor() == Color.BLACK) {
            return BLACK_DIRECTION;
        }
        return WHITE_DIRECTION;
    }

    private boolean isFirstMove(Position sourcePosition) {
        if (getColor() == Color.WHITE) {
            return sourcePosition.isSameRank(WHITE_PAWN_INIT_RANK);
        }
        return sourcePosition.isSameRank(BLACK_PAWN_INIT_RANK);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
