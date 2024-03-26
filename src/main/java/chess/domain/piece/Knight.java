package chess.domain.piece;

import chess.domain.position.FileDifference;
import chess.domain.position.Position;
import chess.domain.position.RankDifference;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color, PieceType.KNIGHT);
    }

    @Override
    public boolean canMove(Position from, Position to) {
        FileDifference fileDifference = from.calculateFileDifferenceTo(to);
        RankDifference rankDifference = from.calculateRankDifferenceTo(to);
        return (fileDifference.hasDistance(1) && rankDifference.hasDistance(2))
                || (fileDifference.hasDistance(2) && rankDifference.hasDistance(1));
    }

    @Override
    public boolean canCatch(Position from, Position to) {
        return canMove(from, to);
    }
}
