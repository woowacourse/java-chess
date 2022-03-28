package chess.piece;

import chess.position.Position;

import java.util.ArrayList;
import java.util.List;

import static chess.utils.MovingBetweenPositions.computeBetweenPositionsOfColumn;
import static chess.utils.MovingBetweenPositions.computeBetweenPositionsOfRow;

public class Rook extends Piece {

    public Rook(Color color) {
        super(Type.ROOK, color);
    }

    @Override
    public List<Position> computeBetweenTwoPosition(Position source, Position target) {
        if (source.isSameRow(target)) {
            return computeBetweenPositionsOfRow(source, target);
        }
        if (source.isSameColumn(target)) {
            return computeBetweenPositionsOfColumn(source, target);
        }
        return new ArrayList<>();
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        return source.isSameRow(target) || source.isSameColumn(target);
    }
}


