package chess.domain.piece;

import chess.domain.File;
import chess.domain.PieceColor;
import chess.domain.PieceType;
import chess.domain.Position;
import chess.domain.Rank;

public class Bishop extends Piece {

    public Bishop(PieceColor color) {
        super(PieceType.BISHOP, color);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return (getDeltaRank(source.rank(), target.rank()) ==
                getDeltaFile(source.file(), target.file()));
    }

    private int getDeltaRank(Rank source, Rank target) {
        return Math.abs(source.get() - target.get());
    }

    private int getDeltaFile(File source, File target) {
        return Math.abs(source.get() - target.get());
    }
}
