package chess.domain.piece;

import static chess.domain.piece.Type.KNIGHT;

import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    private static final int TWO_SQUARES = 2;

    public Knight(Color color) {
        super(color);
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
        int rankDiff = source.calculateRankDifference(target);
        int fileDiff = source.calculateFileDifference(target);

        return Math.abs(rankDiff) * Math.abs(fileDiff) == TWO_SQUARES;
    }

    @Override
    public List<Position> searchPath(Position source, Position target) {
        return new ArrayList<>();
    }
}
