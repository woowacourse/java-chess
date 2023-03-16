package chess.domain.piece;

import chess.domain.board.Square;

public class Rook extends Piece {
    public Rook(Camp camp) {
        super(camp);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.ROOK;
    }

    @Override
    public boolean canMove(Square source, Square target) {
        return source.isSameFile(target) || source.isSameRank(target);
    }

}
