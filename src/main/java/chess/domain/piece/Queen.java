package chess.domain.piece;

import static chess.domain.piece.Type.QUEEN;
import static chess.utils.Constant.ONE_SQUARE;
import static chess.utils.Constant.ZERO_SQUARE;

import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {


    public Queen(Color color) {
        super(color);
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
        int rankDiff = source.calculateRankDifference(target);
        int fileDiff = source.calculateFileDifference(target);

        return Math.abs(rankDiff) == Math.abs(fileDiff) || rankDiff * fileDiff == ZERO_SQUARE;
    }

    @Override
    public List<Position> searchPath(Position source, Position target) {
        int rankDiff = source.calculateRankDifference(target);
        int fileDiff = source.calculateFileDifference(target);

        int rankUnit = 0;
        int fileUnit = 0;
        int count = 0;

        if (Math.abs(rankDiff) > ZERO_SQUARE && Math.abs(fileDiff) > ZERO_SQUARE) {
            rankUnit = rankDiff / Math.abs(rankDiff);
            fileUnit = fileDiff / Math.abs(fileDiff);
            count = Math.abs(rankDiff);
        }
        if (Math.abs(rankDiff) > ZERO_SQUARE && fileDiff == ZERO_SQUARE) {
            rankUnit = rankDiff / Math.abs(rankDiff);
            fileUnit = ZERO_SQUARE;
            count = Math.abs(rankDiff);
        }
        if (Math.abs(rankDiff) == ZERO_SQUARE && Math.abs(fileDiff) > ZERO_SQUARE) {
            rankUnit = ZERO_SQUARE;
            fileUnit = fileDiff / Math.abs(fileDiff);
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
