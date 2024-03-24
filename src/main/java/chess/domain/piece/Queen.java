package chess.domain.piece;

import static chess.domain.piece.Type.QUEEN;
import static chess.utils.Constant.ONE_SQUARE;
import static chess.utils.Constant.ZERO_SQUARE;

import chess.domain.position.Position;
import chess.utils.UnitCalculator;
import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {


    public Queen(Color color) {
        super(color, 9);
    }

    @Override
    public String identifyType() {
        return QUEEN.name();
    }

    @Override
    public boolean canMove(Position source, Position target, Color color) {
        if (this.color == color) {
            return false;
        }
        int fileDiff = source.calculateFileDifference(target);
        int rankDiff = source.calculateRankDifference(target);

        return Math.abs(fileDiff) == Math.abs(rankDiff) || fileDiff * rankDiff == ZERO_SQUARE;
    }

    @Override
    public List<Position> searchPath(Position source, Position target) {
        int fileDiff = source.calculateFileDifference(target);
        int rankDiff = source.calculateRankDifference(target);

        int fileUnit = 0;
        int rankUnit = 0;
        int count = 0;

        if (Math.abs(fileDiff) > ZERO_SQUARE && Math.abs(rankDiff) > ZERO_SQUARE) {
            fileUnit = UnitCalculator.getUnit(fileDiff);
            rankUnit = UnitCalculator.getUnit(rankDiff);
            count = Math.abs(rankDiff);
        }
        if (fileDiff == ZERO_SQUARE && Math.abs(rankDiff) > ZERO_SQUARE) {
            fileUnit = ZERO_SQUARE;
            rankUnit = UnitCalculator.getUnit(rankDiff);
            count = Math.abs(rankDiff);
        }
        if (Math.abs(fileDiff) > ZERO_SQUARE && Math.abs(rankDiff) == ZERO_SQUARE) {
            fileUnit = UnitCalculator.getUnit(fileDiff);
            rankUnit = ZERO_SQUARE;
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
