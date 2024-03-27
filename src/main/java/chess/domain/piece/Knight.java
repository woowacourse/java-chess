package chess.domain.piece;

import static chess.domain.piece.Type.KNIGHT;
import static chess.utils.Constant.TWO_SQUARE;

import chess.domain.position.Position;
import chess.domain.vo.Score;
import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    private static final Score KNIGHT_SCORE = new Score(2.5);

    public Knight(Color color) {
        super(color, KNIGHT_SCORE);
    }

    @Override
    public String identifyType() {
        return KNIGHT.name();
    }

    @Override
    public boolean canMove(Position source, Position target, Color color) {
        if (this.color == color) {
            return false;
        }
        int fileDiff = source.calculateFileDifference(target);
        int rankDiff = source.calculateRankDifference(target);

        return Math.abs(fileDiff) * Math.abs(rankDiff) == TWO_SQUARE;
    }

    @Override
    public List<Position> searchPath(Position source, Position target) {
        return new ArrayList<>();
    }
}
