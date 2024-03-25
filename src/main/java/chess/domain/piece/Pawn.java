package chess.domain.piece;

import static chess.domain.piece.Type.PAWN;
import static chess.utils.Constant.ONE_SQUARE;
import static chess.utils.Constant.TWO_SQUARE;
import static chess.utils.Constant.ZERO_SQUARE;

import chess.domain.position.Position;
import chess.domain.vo.Score;
import chess.utils.UnitCalculator;
import java.util.List;

public class Pawn extends Piece {
    private static final Score PAWN_SCORE = new Score(1);

    public Pawn(Color color) {
        super(color, PAWN_SCORE);
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
        int moveCount = Math.abs(rankDiff);

        return combinePath(source, moveCount, ZERO_SQUARE, rankUnit);
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
