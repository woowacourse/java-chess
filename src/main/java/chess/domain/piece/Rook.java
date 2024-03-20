package chess.domain.piece;

import chess.domain.square.Square;

public class Rook extends Piece {

    public Rook(final Color color) {
        super(color, Type.ROOK);
    }

    @Override
    public boolean canMove(final Square from, final Square to) {
        int fileDiff = Math.abs(from.getFileIndex() - to.getFileIndex());
        int rankDiff = Math.abs(from.getRankIndex() - to.getRankIndex());

        return (fileDiff == 0 && rankDiff > 0) || (fileDiff > 0 && rankDiff == 0);
    }
}
