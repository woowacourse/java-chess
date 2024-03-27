package chess.domain.piece;

import static chess.domain.piece.Type.KING;

import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    private static final int ONE_SQUARE = 1;

    public King(Color color) {
        super(color);
    }

    @Override
    public Type identifyType() {
        return KING;
    }

    @Override
    public boolean canMove(Position source, Position target, Piece piece) {
        if (piece.isSameColor(color)) {
            return false;
        }
        int rankDiff = source.calculateRankDifference(target);
        int fileDiff = source.calculateFileDifference(target);

        return Math.abs(rankDiff) <= ONE_SQUARE && Math.abs(fileDiff) <= ONE_SQUARE;
    }

    @Override
    public List<Position> searchPath(Position source, Position target) {
        return new ArrayList<>();
    }
}
