package chess.domain.piece;

import static chess.domain.piece.Type.KING;

import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public class King implements Piece {
    private final Color color;

    public King(Color color) {
        this.color = color;
    }

    @Override
    public String getType() {
        return KING.name();
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

        return Math.abs(rankDiff) <= 1 && Math.abs(fileDiff) <= 1;
    }

    @Override
    public List<Position> searchPath(Position source, Position target) {
        return new ArrayList<>();
    }
}
