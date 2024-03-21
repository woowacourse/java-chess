package chess.domain.piece;

import chess.domain.square.Square;

public class Rook extends Piece {

    public Rook(Team color) {
        super(PieceType.ROOK, color);
    }

    @Override
    public boolean canMove(Square source, Square target) {
        return source.isSameRank(target) || source.isSameFile(target);
    }
}
