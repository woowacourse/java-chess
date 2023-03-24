package chess.domain.piece;

import chess.domain.board.Square;

public class Bishop extends Piece {
    private static final Bishop WHITE_BISHOP = new Bishop(Camp.WHITE);
    private static final Bishop BLACK_BISHOP = new Bishop(Camp.BLACK);

    private Bishop(Camp camp) {
        super(camp);
    }

    public static Bishop getInstanceOf(Camp camp) {
        if (camp == Camp.WHITE) {
            return WHITE_BISHOP;
        }

        return BLACK_BISHOP;
    }

    @Override
    public boolean canMove(Square source, Square target) {
        return source.calculateFileDistance(target) == source.calculateRankDistance(target);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.BISHOP;
    }
}
