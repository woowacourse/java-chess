package chess.domain.piece;

import static chess.domain.piece.Type.PAWN;
import static chess.utils.Constant.ONE_SQUARE;
import static chess.utils.Constant.TWO_SQUARE;
import static chess.utils.Constant.ZERO_SQUARE;

import chess.domain.position.Position;
import chess.utils.UnitCalculator;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    public Pawn(Color color) {
        super(color, 1);
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
        int rankUnit = UnitCalculator.getUnit(rankDiff);

        List<Position> path = new ArrayList<>();
        for (int i = Math.abs(rankDiff); i != ONE_SQUARE; i--) {
            source = source.move(ZERO_SQUARE, rankUnit);
            path.add(source);
        }
        return path;
    }

    private boolean checkBlack(Position source, Position target, Color color) {
        int fileDiff = source.calculateFileDifference(target);
        int rankDiff = source.calculateRankDifference(target);

        if (Math.abs(fileDiff) == ONE_SQUARE && rankDiff == -ONE_SQUARE) {
            return color == Color.WHITE;
        }
        if (color == Color.WHITE) {
            return false;
        }
        if (source.isPawnFirstTry(this.color)) {
            return (fileDiff == ZERO_SQUARE) && (rankDiff == -ONE_SQUARE || rankDiff == -TWO_SQUARE);
        }
        return fileDiff == ZERO_SQUARE && rankDiff == -ONE_SQUARE;
    }

    private boolean checkWhite(Position source, Position target, Color color) {
        int fileDiff = source.calculateFileDifference(target);
        int rankDiff = source.calculateRankDifference(target);

        if (Math.abs(fileDiff) == ONE_SQUARE && rankDiff == ONE_SQUARE) {
            return color == Color.BLACK;
        }
        if (color == Color.BLACK) {
            return false;
        }
        if (source.isPawnFirstTry(this.color)) {
            return (fileDiff == ZERO_SQUARE) && (rankDiff == ONE_SQUARE || rankDiff == TWO_SQUARE);
        }
        return fileDiff == ZERO_SQUARE && rankDiff == ONE_SQUARE;
    }
}
