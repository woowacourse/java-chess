package chess.domain.piece;

import static chess.domain.piece.Type.QUEEN;

import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public class Queen implements Piece {
    private static final int STAY = 0;
    private static final int ONE_SQUARE = 1;

    private final Color color;

    public Queen(Color color) {
        this.color = color;
    }

    @Override
    public String identifyType() {
        return QUEEN.name();
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

        return Math.abs(rankDiff) == Math.abs(fileDiff) || rankDiff * fileDiff == STAY;
    }

    @Override
    public List<Position> searchPath(Position source, Position target) {
        int rankDiff = source.calculateRankDifference(target);
        int fileDiff = source.calculateFileDifference(target);

        int rankUnit = 0;
        int fileUnit = 0;
        int count = 0;

        if (Math.abs(rankDiff) > STAY && Math.abs(fileDiff) > STAY) {
            rankUnit = rankDiff / Math.abs(rankDiff);
            fileUnit = fileDiff / Math.abs(fileDiff);
            count = Math.abs(rankDiff);
        }
        if (Math.abs(rankDiff) > STAY && fileDiff == STAY) {
            rankUnit = rankDiff / Math.abs(rankDiff);
            fileUnit = STAY;
            count = Math.abs(rankDiff);
        }
        if (Math.abs(rankDiff) == STAY && Math.abs(fileDiff) > STAY) {
            rankUnit = STAY;
            fileUnit = fileDiff / Math.abs(fileDiff);
            count = Math.abs(fileDiff);
        }

        List<Position> path = new ArrayList<>();
        for (int i = count; i != ONE_SQUARE; i--) {
            source = source.move(fileUnit, rankUnit);
            path.add(source);
        }
        return path;
    }
}
