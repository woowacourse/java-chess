package chess.domain.piece;

import chess.domain.square.Square;

public class Bishop extends Piece {

    public Bishop(final Color color) {
        super(color, Type.BISHOP);
    }

    @Override
    public boolean canMove(final Square from, final Square to) {
        int fileDiff = Math.abs(from.getFileIndex() - to.getFileIndex());
        int rankDiff = Math.abs(from.getRankIndex() - to.getRankIndex());

        return fileDiff == rankDiff;
    }
}
