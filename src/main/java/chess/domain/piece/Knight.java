package chess.domain.piece;

import chess.domain.PieceColor;
import chess.domain.PieceType;
import chess.domain.Position;

public class Knight extends Piece {

    public Knight(PieceColor color) {
        super(PieceType.KNIGHT, color);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return (source.calculateFileDiff(target.file()) == 1
                && source.calculateRankDiff(target.rank()) == 2) ||
                (source.calculateFileDiff(target.file()) == 2
                        && source.calculateRankDiff(target.rank()) == 1);
    }
}
