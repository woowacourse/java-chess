package chess.domain.piece;

import static chess.domain.piece.Type.BISHOP;

import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public class Bishop implements Piece {
    private static final int ONE_SQUARE = 1;

    private final Color color;

    public Bishop(Color color) {
        this.color = color;
    }

    @Override
    public String identifyType() {
        return BISHOP.name();
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
        return Math.abs(rankDiff) == Math.abs(fileDiff);
    }

    @Override
    public List<Position> searchPath(Position source, Position target) {
        int rankDiff = source.calculateRankDifference(target);
        int fileDiff = source.calculateFileDifference(target);

        int rankUnit = rankDiff / Math.abs(rankDiff);
        int fileUnit = fileDiff / Math.abs(fileDiff);

        List<Position> path = new ArrayList<>();
        for (int i = Math.abs(rankDiff); i != ONE_SQUARE; i--) {
            source = source.move(fileUnit, rankUnit);
            path.add(source);
        }
        return path;
    }
}
