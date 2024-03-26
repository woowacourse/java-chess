package chess.domain.piece;

import static chess.domain.piece.Type.KNIGHT;

import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public class Knight implements Piece {
    private static final int TWO_SQUARES = 2;

    private final Color color;

    public Knight(Color color) {
        this.color = color;
    }

    @Override
    public String identifyType() {
        return KNIGHT.name();
    }

    @Override
    public boolean isBlack() {
        return color == Color.BLACK;
    }

    @Override
    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    @Override
    public boolean canMove(Position source, Position target, Piece piece) {
        if (piece.isSameColor(color)) {
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
