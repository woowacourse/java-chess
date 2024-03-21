package chess.domain.piece;

import static chess.domain.piece.Type.QUEEN;

import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public class Queen implements Piece {
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
    public boolean canMove(Position source, Position target, Color color) {
        if (this.color == color) {
            return false;
        }
        int rankDiff = source.calculateRankDifference(target);
        int fileDiff = source.calculateFileDifference(target);

        return Math.abs(rankDiff) == Math.abs(fileDiff) || rankDiff * fileDiff == 0;
    }

    @Override
    public List<Position> searchPath(Position source, Position target) {
        int rankDiff = source.calculateRankDifference(target);
        int fileDiff = source.calculateFileDifference(target);

        int rankUnit = 0;
        int fileUnit = 0;
        int count = 0;

        if (Math.abs(rankDiff) > 0 && Math.abs(fileDiff) > 0) {
            rankUnit = rankDiff / Math.abs(rankDiff);
            fileUnit = fileDiff / Math.abs(fileDiff);
            count = Math.abs(rankDiff);
        }
        if (Math.abs(rankDiff) > 0 && fileDiff == 0) {
            rankUnit = rankDiff / Math.abs(rankDiff);
            fileUnit = 0;
            count = Math.abs(rankDiff);
        }
        if (Math.abs(rankDiff) == 0 && Math.abs(fileDiff) > 0) {
            rankUnit = 0;
            fileUnit = fileDiff / Math.abs(fileDiff);
            count = Math.abs(fileDiff);
        }

        List<Position> path = new ArrayList<>();
        for (int i = count; i != 1; i--) {
            source = source.move(fileUnit, rankUnit);
            path.add(source);
        }
        return path;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
