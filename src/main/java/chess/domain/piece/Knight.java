package chess.domain.piece;

import chess.domain.board.Square;

public class Knight extends Piece {
    private static final Knight WHITE_KNIGHT = new Knight(Camp.WHITE);
    private static final Knight BLACK_KNIGHT = new Knight(Camp.BLACK);
    private static final int MOVABLE_SIDE_DISTANCE = 1;
    private static final int MOVABLE_STRAIGHT_DISTANCE = 2;

    private Knight(Camp camp) {
        super(camp);
    }

    public static Knight getInstanceOf(Camp camp) {
        if (camp == Camp.WHITE) {
            return WHITE_KNIGHT;
        }

        return BLACK_KNIGHT;
    }

    @Override
    public boolean canMove(Square source, Square target) {
        if (source.calculateRankDistance(target) == MOVABLE_STRAIGHT_DISTANCE
                && source.calculateFileDistance(target) == MOVABLE_SIDE_DISTANCE) {
            return true;
        }

        return source.calculateRankDistance(target) == MOVABLE_SIDE_DISTANCE
                && source.calculateFileDistance(target) == MOVABLE_STRAIGHT_DISTANCE;
    }


    @Override
    public PieceType getPieceType() {
        return PieceType.KNIGHT;
    }
}
