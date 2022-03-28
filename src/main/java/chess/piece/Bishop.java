package chess.piece;

import chess.position.Position;

import java.util.ArrayList;
import java.util.List;

import static chess.utils.MovingBetweenPositions.computeLeftDownRightUp;
import static chess.utils.MovingBetweenPositions.computeLeftUpRightDown;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(Type.BISHOP, color);
    }

    @Override
    public List<Position> computeBetweenTwoPosition(Position source, Position target) {
        if (source.gapTwoPositionRow(target) == (-1) * source.gapTwoPositionColumn(target)) {
            return computeLeftDownRightUp(source, target);

        }
        if (source.gapTwoPositionRow(target) == source.gapTwoPositionColumn(target)) {
            return computeLeftUpRightDown(source, target);
        }
        return new ArrayList<>();
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        return source.gapTwoPositionRow(target) == source.gapTwoPositionColumn(target);
    }
}
