package chess.domain.piece;

import chess.domain.square.Square;

public class King extends Piece {

    public King(final Color color) {
        super(color, Type.KING);
    }

    @Override
    public boolean canMove(final Square from, final Square to) {
        int fileDiff = Math.abs(from.getFileIndex() - to.getFileIndex());
        int rankDiff = Math.abs(from.getRankIndex() - to.getRankIndex());

        return fileDiff == 1 || rankDiff == 1;
    }
}
