package chess.domain.piece;

import chess.domain.position.Position;

import java.util.List;

import static chess.utils.BetweenPositionsGenerator.computeBetweenPositionNegativeDiagonal;
import static chess.utils.BetweenPositionsGenerator.computeBetweenPositionPositiveDiagonal;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(Type.BISHOP, color);
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        return source.gapTwoPositionRow(target) == source.gapTwoPositionColumn(target);
    }

    @Override
    public List<Position> computeBetweenTwoPositionByLine(Position source, Position target) {
        if (source.gapTwoPositionRow(target) == source.gapTwoPositionColumn(target)) {
            return computeDiagonalPosition(source, target);
        }
        return List.of();
    }

    private List<Position> computeDiagonalPosition(Position source, Position target) {
        if (source.isSmallColumn(target)) {
            return computeBetweenPositionNegativeDiagonal(source, target);
        }
        if (target.isSmallColumn(source)) {
            return computeBetweenPositionPositiveDiagonal(source, target);
        }
        return List.of();
    }
}
