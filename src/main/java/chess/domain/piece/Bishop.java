package chess.domain.piece;

import static chess.domain.piece.Type.BISHOP;

import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public class Bishop implements Piece {
    private final Color color;

    public Bishop(Color color) {
        this.color = color;
    }

    public String getType() {
        return BISHOP.name();
    }

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
        return Math.abs(rankDiff) == Math.abs(fileDiff);
    }

    @Override
    public List<Position> searchPath(Position source, Position target) {
        int rankDiff = source.calculateRankDifference(target);
        int fileDiff = source.calculateFileDifference(target);

        int rankUnit = rankDiff / Math.abs(rankDiff);
        int fileUnit = fileDiff / Math.abs(fileDiff);

        List<Position> path = new ArrayList<>();
        for (int i = Math.abs(rankDiff); i != 1; i--) {
            source = source.move(rankUnit, fileUnit);
            path.add(source);
        }
        return path;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
