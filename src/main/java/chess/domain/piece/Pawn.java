package chess.domain.piece;

import static chess.domain.piece.Type.PAWN;
import static chess.utils.Constant.ONE_SQUARE;
import static chess.utils.Constant.TWO_SQUARE;
import static chess.utils.Constant.ZERO_SQUARE;

import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    public Pawn(Color color) {
        super(color);
    }

    @Override
    public String identifyType() {
        return PAWN.name();
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

        if (Math.abs(rankDiff) == TWO_SQUARE) {
            source = source.move(ZERO_SQUARE, rankDiff / TWO_SQUARE);
            path.add(source);
        }
        return path;
    }

    private boolean checkBlack(Position source, Position target, Color color) {
        int rankDiff = source.calculateRankDifference(target);
        int fileDiff = source.calculateFileDifference(target);

        if (rankDiff == -ONE_SQUARE && Math.abs(fileDiff) == ONE_SQUARE) {
            return color == Color.WHITE;
        }
        if (color == Color.WHITE) {
            return false;
        }
        if (source.isPawnFirstTry(this.color)) {
            return (rankDiff == -ONE_SQUARE || rankDiff == -TWO_SQUARE) && (fileDiff == ZERO_SQUARE);
        }
        return rankDiff == -ONE_SQUARE && fileDiff == ZERO_SQUARE;
    }

    private boolean checkWhite(Position source, Position target, Color color) {
        int rankDiff = source.calculateRankDifference(target);
        int fileDiff = source.calculateFileDifference(target);

        if (rankDiff == ONE_SQUARE && Math.abs(fileDiff) == ONE_SQUARE) {
            return color == Color.BLACK;
        }
        if (color == Color.BLACK) {
            return false;
        }
        if (source.isPawnFirstTry(this.color)) {
            return (rankDiff == ONE_SQUARE || rankDiff == TWO_SQUARE) && (fileDiff == ZERO_SQUARE);
        }
        return rankDiff == ONE_SQUARE && fileDiff == ZERO_SQUARE;
    }
}
