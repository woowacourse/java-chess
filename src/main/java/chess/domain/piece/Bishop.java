package chess.domain.piece;

import static chess.domain.piece.Type.BISHOP;
import static chess.utils.Constant.ONE_SQUARE;

import chess.domain.position.Position;
import chess.utils.UnitCalculator;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    public Bishop(Color color) {
        super(color, 3);
    }

    @Override
    public String identifyType() {
        return BISHOP.name();
    }

    @Override
    public boolean canMove(Position source, Position target, Color color) {
        if (this.color == color) {
            return false;
        }
        int fileDiff = source.calculateFileDifference(target);
        int rankDiff = source.calculateRankDifference(target);
        return Math.abs(fileDiff) == Math.abs(rankDiff);
    }

    @Override
    public List<Position> searchPath(Position source, Position target) {
        int fileDiff = source.calculateFileDifference(target);
        int rankDiff = source.calculateRankDifference(target);

        int fileUnit = UnitCalculator.getUnit(fileDiff);
        int rankUnit = UnitCalculator.getUnit(rankDiff);

        List<Position> path = new ArrayList<>();
        for (int i = Math.abs(rankDiff); i != ONE_SQUARE; i--) {
            source = source.move(fileUnit, rankUnit);
            path.add(source);
        }
        return path;
    }
}
