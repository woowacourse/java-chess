package chess.domain.piece;

import static chess.domain.piece.Type.KNIGHT;

import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public class Knight implements Piece {
    private final Color color;

    public Knight(Color color) {
        this.color = color;
    }

    @Override
    public String getType() {
        return KNIGHT.name();
    }

    @Override
    public boolean isBlack() {
        return color == Color.BLACK;
    }

    @Override
    public boolean canMove(Position source, Position target, Color color) {
        if (this.color == color) {
            return false;
        }
        int rankDiff = source.calculateRankDifference(target);
        int fileDiff = source.calculateFileDifference(target);

        return Math.abs(rankDiff) * Math.abs(fileDiff) == 2;
    }

    @Override
    public List<Position> searchPath(Position source, Position target) {
        return new ArrayList<>();
    }
}
