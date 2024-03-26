package chess.domain.piece;

import static chess.domain.piece.Type.KING;

import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public class King implements Piece {
    private static final int ONE_SQUARE = 1;

    private final Color color;

    public King(Color color) {
        this.color = color;
    }

    @Override
    public String identifyType() {
        return KING.name();
    }

    @Override
    public boolean isBlack() {
        return color == Color.BLACK;
    }

    @Override
    public boolean isSameColor(Color color) {
        return this.color == color;
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
