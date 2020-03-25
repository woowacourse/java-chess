package chess.domains.piece;

import chess.domains.position.Position;

public class Queen extends Piece {
    public Queen(PieceColor pieceColor) {
        super(pieceColor, "q");
    }

    @Override
    public boolean canMove(Position current, Position target) {
        return current.isSameX(target)
                || current.isSameY(target)
                || (current.xGapBetween(target) == current.yGapBetween(target));
    }
}
