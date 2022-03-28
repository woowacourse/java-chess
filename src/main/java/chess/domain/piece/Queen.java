package chess.domain.piece;

import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

import static chess.utils.BetweenPositionsGenerator.*;

public class Queen extends Piece {

    public Queen(Color color) {
        super(Type.QUEEN, color);
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        return (source.gapTwoPositionRow(target) == source.gapTwoPositionColumn(target)) || isSameLine(source, target);
    }

    @Override
    public List<Position> computeBetweenTwoPositionByLine(Position source, Position target) {
        List<Position> positions = new ArrayList<>();
        if (source.isSameRow(target)) {
            positions.addAll(computeBetweenPositionBySameRow(source, target));
        }
        if (source.isSameColumn(target)) {
            positions.addAll(computeBetweenPositionBySameColumn(source, target));
        }
        if (source.gapTwoPositionRow(target) == source.gapTwoPositionColumn(target)) {
            positions.addAll(computeBetweenPositionDiagonal(source, target));
        }
        return positions;
    }

    private boolean isSameLine(Position source, Position target) {
        return source.isSameRow(target) || source.isSameColumn(target);
    }
}
