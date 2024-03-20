package chess.domain.piece;

import chess.domain.square.Square;

public class Knight extends Piece {

    public Knight(final Color color) {
        super(color, Type.KNIGHT);
    }

    @Override
    public boolean canMove(final Square from, final Square to) {
        int fileDiff = Math.abs(from.getFileIndex() - to.getFileIndex());
        int rankDiff = Math.abs(from.getRankIndex() - to.getRankIndex());

        return (fileDiff == 2 && rankDiff == 1) || (fileDiff == 1 && rankDiff == 2);
    }
}
