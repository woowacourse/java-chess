package chess.domain.piece;

import chess.domain.PieceColor;
import chess.domain.PieceType;
import chess.domain.Position;

public class Bishop extends Piece {

    public Bishop(PieceColor color) {
        super(PieceType.BISHOP, color);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return (source.calculateRankDiff(target.rank()) ==
                source.calculateFileDiff(target.file()));
    }
}
