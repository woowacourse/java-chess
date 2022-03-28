package chess.piece;

import chess.position.Position;

import java.util.List;

import static chess.utils.BetweenPositionsGenerator.computeBetweenPositionDiagonal;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(Type.BISHOP, color);
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        return source.gapTwoPositionRow(target) == source.gapTwoPositionColumn(target);
    }

    @Override
    public List<Position> computeBetweenTwoPosition(Position source, Position target) {
        if (source.gapTwoPositionRow(target) == source.gapTwoPositionColumn(target)) {
            return computeBetweenPositionDiagonal(source, target);
        }
        return List.of();
    }
}
