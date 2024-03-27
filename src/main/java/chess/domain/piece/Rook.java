package chess.domain.piece;

import static chess.domain.piece.Type.ROOK;
import static chess.utils.Constant.ZERO_SQUARE;

import chess.domain.position.Position;
import chess.domain.vo.Score;
import chess.utils.UnitCalculator;
import java.util.List;

public class Rook extends Piece {
    private static final Score ROOK_SCORE = new Score(5);

    public Rook(Color color) {
        super(color, ROOK_SCORE);
    }

    @Override
    public String identifyType() {
        return ROOK.name();
    }

    @Override
    public boolean canMove(Position source, Position target, Color color) {
        if (this.color == color) {
            return false;
        }
        int fileDiff = source.calculateFileDifference(target);
        int rankDiff = source.calculateRankDifference(target);
        return Math.abs(fileDiff) == ZERO_SQUARE || Math.abs(rankDiff) == ZERO_SQUARE;
    }

    @Override
    public List<Position> searchPath(Position source, Position target) {
        int fileDiff = source.calculateFileDifference(target);
        int rankDiff = source.calculateRankDifference(target);

        if (Math.abs(rankDiff) > ZERO_SQUARE) {
            int rankUnit = UnitCalculator.getUnit(rankDiff);
            int moveCount = Math.abs(rankDiff);
            return combinePath(source, moveCount, ZERO_SQUARE, rankUnit);
        }

        int fileUnit = UnitCalculator.getUnit(fileDiff);
        int moveCount = Math.abs(fileDiff);
        return combinePath(source, moveCount, fileUnit, ZERO_SQUARE);
    }
}
