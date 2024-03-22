package chess.domain.piece;

import static chess.domain.piece.Type.ROOK;
import static chess.utils.Constant.ONE_SQUARE;
import static chess.utils.Constant.ZERO_SQUARE;

import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    public Rook(Color color) {
        super(color);
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
        int rankDiff = source.calculateRankDifference(target);
        int fileDiff = source.calculateFileDifference(target);
        return Math.abs(rankDiff) == ZERO_SQUARE || Math.abs(fileDiff) == ZERO_SQUARE;
    }

    @Override
    public List<Position> searchPath(Position source, Position target) {
        int rankDiff = source.calculateRankDifference(target);
        int fileDiff = source.calculateFileDifference(target);

        if (Math.abs(rankDiff) > ZERO_SQUARE) {
            int rankUnit = rankDiff / Math.abs(rankDiff);

            List<Position> path = new ArrayList<>();
            for (int i = Math.abs(rankDiff); i != ONE_SQUARE; i--) {
                source = source.move(ZERO_SQUARE, rankUnit);
                path.add(source);
            }
            return path;
        }

        int fileUnit = fileDiff / Math.abs(fileDiff);

        List<Position> path = new ArrayList<>();
        for (int i = Math.abs(fileDiff); i != ONE_SQUARE; i--) {
            source = source.move(fileUnit, ZERO_SQUARE);
            path.add(source);
        }
        return path;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
