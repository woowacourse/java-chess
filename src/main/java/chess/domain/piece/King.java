package chess.domain.piece;

import chess.domain.*;

import java.util.Map;

public class King extends Piece {

    public King(PieceColor color) {
        super(PieceType.KING, color);
    }

    @Override
    public boolean canMove(Position source, Position target, Board board) {
        return (getDeltaRank(source.rank(), target.rank()) <= 1 &&
                getDeltaFile(source.file(), target.file()) <= 1);
    }

    private int getDeltaRank(Rank source, Rank target) {
        return Math.abs(source.get() - target.get());
    }

    private int getDeltaFile(File source, File target) {
        return Math.abs(source.get() - target.get());
    }
}
