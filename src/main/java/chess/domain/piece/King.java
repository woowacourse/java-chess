package chess.domain.piece;

import chess.domain.board.Square;

public class King extends Piece {
    private static final King WIHTE_KING = new King(Camp.WHITE);
    private static final King BLACK_KING = new King(Camp.BLACK);
    private static final int MOVABLE_DISTANCE = 1;

    private King(Camp camp) {
        super(camp);
    }

    public static King getInstanceOf(Camp camp) {
        if (camp == Camp.WHITE) {
            return WIHTE_KING;
        }

        return BLACK_KING;
    }

    @Override
    public boolean canMove(Square source, Square target) {
        return (source.calculateRankDistance(target) == MOVABLE_DISTANCE) ||
                (source.calculateFileDistance(target) == MOVABLE_DISTANCE);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.KING;
    }

}
