package chess.domain.piece;

import static chess.domain.piece.Type.ROOK;

import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public class Rook implements Piece {
    private static final int STAY = 0;
    private static final int ONE_SQUARE = 1;

    private final Color color;

    public Rook(Color color) {
        this.color = color;
    }

    @Override
    public String identifyType() {
        return ROOK.name();
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
        return Math.abs(rankDiff) == STAY || Math.abs(fileDiff) == STAY;
    }

    @Override
    public List<Position> searchPath(Position source, Position target) {
        int rankDiff = source.calculateRankDifference(target);
        int fileDiff = source.calculateFileDifference(target);
        List<Position> path = new ArrayList<>();

        if (Math.abs(rankDiff) > STAY) {
            int rankUnit = rankDiff / Math.abs(rankDiff);

            for (int i = Math.abs(rankDiff); i != ONE_SQUARE; i--) {
                source = source.move(STAY, rankUnit);
                path.add(source);
            }
            return path;
        }

        int fileUnit = fileDiff / Math.abs(fileDiff);

        for (int i = Math.abs(fileDiff); i != ONE_SQUARE; i--) {
            source = source.move(fileUnit, STAY);
            path.add(source);
        }
        return path;
    }
}
