package chess.domain.piece;

import static chess.domain.piece.Type.ROOK;
import static chess.utils.Constant.ONE_SQUARE;
import static chess.utils.Constant.ZERO_SQUARE;

import chess.domain.position.Position;
import chess.utils.UnitCalculator;
import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    public Rook(Color color) {
        super(color, 5);
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

            List<Position> path = new ArrayList<>();
            for (int i = Math.abs(rankDiff); i != ONE_SQUARE; i--) {
                source = source.move(ZERO_SQUARE, rankUnit);
                path.add(source);
            }
            return path;
        }

        int fileUnit = UnitCalculator.getUnit(fileDiff);

        List<Position> path = new ArrayList<>();
        for (int i = Math.abs(fileDiff); i != ONE_SQUARE; i--) {
            source = source.move(fileUnit, ZERO_SQUARE);
            path.add(source);
        }
        return path;
    }
}
