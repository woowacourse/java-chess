package chess.domain.piece;

import chess.domain.board.Square;

public class Queen extends Piece {
    private static final Queen WHITE_QUEEN = new Queen(Camp.WHITE);
    private static final Queen BLACK_QUEEN = new Queen(Camp.BLACK);

    private Queen(Camp camp) {
        super(camp);
    }

    public static Queen getInstanceOf(Camp camp) {
        if (camp == Camp.WHITE) {
            return WHITE_QUEEN;
        }

        return BLACK_QUEEN;
    }

    @Override
    public boolean canMove(Square source, Square target) {
        return isStraight(source, target) || isDiagonal(source, target);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.QUEEN;
    }

    private boolean isStraight(Square source, Square target) {
        return source.isSameFile(target) || source.isSameRank(target);
    }

    private boolean isDiagonal(Square source, Square target) {
        return source.calculateFileDistance(target) == source.calculateRankDistance(target);
    }
}
