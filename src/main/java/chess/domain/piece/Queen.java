package chess.domain.piece;

import chess.domain.PieceColor;
import chess.domain.PieceType;
import chess.domain.Position;

public class Queen extends Piece {

    public Queen(PieceColor color) {
        super(PieceType.QUEEN, color);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return (isSameRank(source, target) || isSameFile(source, target)) ||
                (getDeltaRank(source.rank(), target.rank()) ==
                        getDeltaFile(source.file(), target.file()));
    }
}
