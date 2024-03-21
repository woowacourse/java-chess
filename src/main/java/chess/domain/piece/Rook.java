package chess.domain.piece;

import chess.domain.PieceColor;
import chess.domain.PieceType;
import chess.domain.Square;

public class Rook extends Piece {

    public Rook(PieceColor color) {
        super(PieceType.ROOK, color);
    }

    @Override
    public boolean canMove(Square source, Square target) {
        return source.isSameRank(target) || source.isSameFile(target);
    }
}
