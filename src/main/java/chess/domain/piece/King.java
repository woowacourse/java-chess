package chess.domain.piece;

import static chess.domain.piece.Type.KING;
import static chess.utils.Constant.ONE_SQUARE;

import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class King extends Piece {
    public King(Color color) {
        super(color);
    }

    @Override
    public String identifyType() {
        return KING.name();
    }

    @Override
    public boolean canMove(Position source, Position target, Color color) {
        if (this.color == color) {
            return false;
        }
        int fileDiff = source.calculateFileDifference(target);
        int rankDiff = source.calculateRankDifference(target);

        return Math.abs(fileDiff) <= ONE_SQUARE && Math.abs(rankDiff) <= ONE_SQUARE;
    }

    @Override
    public List<Position> searchPath(Position source, Position target) {
        return new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        King king = (King) o;
        return this.color == king.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
