package chess.domain.piece;

import static chess.domain.piece.Type.PAWN;

import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public class Pawn implements Piece {
    private final Color color;

    public Pawn(Color color) {
        this.color = color;
    }

    @Override
    public String getType() {
        return PAWN.name();
    }

    @Override
    public boolean isBlack() {
        return color == Color.BLACK;
    }

    @Override
    public boolean canMove(Position source, Position target, Color targetPieceColor) {
        if (this.color == targetPieceColor) {
            return false;
        }
        if (this.color == Color.BLACK) {
            return checkBlack(source, target, targetPieceColor);
        }
        return checkWhite(source, target, targetPieceColor);
    }

    @Override
    public List<Position> searchPath(Position source, Position target) {

        int rankDiff = source.calculateRankDifference(target);
        List<Position> path = new ArrayList<>();

        if (Math.abs(rankDiff) == 2) {
            source = source.move(rankDiff / 2, 0);
            path.add(source);
        }

        return path;
    }

    private boolean checkBlack(Position source, Position target, Color color) {
        int rankDiff = source.calculateRankDifference(target);
        int fileDiff = source.calculateFileDifference(target);

        if (rankDiff == -1 && Math.abs(fileDiff) == 1) {
            return color == Color.WHITE;
        }
        if (color == Color.WHITE) {
            return false;
        }
        if (source.isPawnFirstTry(this.color)) {
            return (rankDiff == -1 || rankDiff == -2) && (fileDiff == 0);
        }
        return rankDiff == -1 && fileDiff == 0;
    }

    private boolean checkWhite(Position source, Position target, Color color) {
        int rankDiff = source.calculateRankDifference(target);
        int fileDiff = source.calculateFileDifference(target);

        if (rankDiff == 1 && Math.abs(fileDiff) == 1) {
            return color == Color.BLACK;
        }
        if (color == Color.BLACK) {
            return false;
        }
        if (source.isPawnFirstTry(this.color)) {
            return (rankDiff == 1 || rankDiff == 2) && (fileDiff == 0);
        }
        return rankDiff == 1 && fileDiff == 0;
    }
}
