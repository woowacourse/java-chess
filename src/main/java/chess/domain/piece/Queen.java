package chess.domain.piece;

import chess.domain.Board;
import chess.domain.File;
import chess.domain.PieceColor;
import chess.domain.PieceType;
import chess.domain.Position;
import chess.domain.Rank;

public class Queen extends Piece {

    public Queen(PieceColor color) {
        super(PieceType.QUEEN, color);
    }

    @Override
    public boolean canMove(Position source, Position target, Board board) {
        return (isSameRank(source, target) || isSameFile(source, target)) ||
                (getDeltaRank(source.rank(), target.rank()) ==
                        getDeltaFile(source.file(), target.file()));
    }

    private int getDeltaRank(Rank source, Rank target) {
        return Math.abs(source.get() - target.get());
    }

    private int getDeltaFile(File source, File target) {
        return Math.abs(source.get() - target.get());
    }
}
