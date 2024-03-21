package chess.domain.piece;

import chess.domain.PieceColor;
import chess.domain.PieceType;
import chess.domain.Position;

public class Rook extends Piece {

    public Rook(PieceColor color) {
        super(PieceType.ROOK, color);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return isSameRank(source, target) || isSameFile(source, target);
    }
}
