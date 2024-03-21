package chess.domain.piece;

import chess.domain.PieceColor;
import chess.domain.PieceType;
import chess.domain.Position;

public class King extends Piece {

    public King(PieceColor color) {
        super(PieceType.KING, color);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return (source.calculateRankDiff(target.rank()) <= 1 &&
                source.calculateFileDiff(target.file()) <= 1);
    }
}
