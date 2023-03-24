package chess.domain.piece;

import chess.domain.board.Square;

public class Rook extends Piece {
    private static final Rook WHITE_ROOK = new Rook(Camp.WHITE);
    private static final Rook BLACK_ROOK = new Rook(Camp.BLACK);

    public Rook(Camp camp) {
        super(camp);
    }

    public static Rook getInstanceOf(Camp camp) {
        if (camp == Camp.WHITE) {
            return WHITE_ROOK;
        }

        return BLACK_ROOK;
    }

    @Override
    public boolean canMove(Square source, Square target) {
        return source.isSameFile(target) || source.isSameRank(target);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.ROOK;
    }

}
