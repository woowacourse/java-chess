package chess.piece;

import chess.position.Position;

import java.util.ArrayList;
import java.util.List;

import static chess.utils.MovingBetweenPositions.*;

public class Queen extends Piece {

    public Queen(Color color) {
        super(Type.QUEEN, color);
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        return (source.gapTwoPositionRow(target) == source.gapTwoPositionColumn(target)) || isSameLine(source, target);
    }

    @Override
    public List<Position> computeBetweenTwoPosition(Position source, Position target) {
        List<Position> result = new ArrayList<>();
        if (source.isSameRow(target)) {
            result.addAll(computeBetweenPositionsOfRow(source, target));
        }
        if (source.isSameColumn(target)) {
            result.addAll(computeBetweenPositionsOfColumn(source, target));
        }
        if ((source.gapTwoPositionRow(target) == (-1) * source.gapTwoPositionColumn(target))) {
            result.addAll(computeLeftDownRightUp(source, target));
        }
        if (source.gapTwoPositionRow(target) == source.gapTwoPositionColumn(target)) {
            result.addAll(computeLeftUpRightDown(source, target));
        }
        return result;
    }

    private boolean isSameLine(Position source, Position target) {
        return source.isSameRow(target) || source.isSameColumn(target);
        //return (toInt(source.charAt(1)) == toInt(target.charAt(1)) || toInt(source.charAt(0)) == toInt(target.charAt(0)));
    }
}
