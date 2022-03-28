package chess.piece;

import chess.position.Position;

import java.util.List;

import static chess.utils.BetweenPositionsGenerator.computeBetweenPositionBySameColumn;
import static chess.utils.BetweenPositionsGenerator.computeBetweenPositionBySameRow;

public class Rook extends Piece {

    public Rook(Color color) {
        super(Type.ROOK, color);
    }

    @Override
    public List<Position> computeBetweenTwoPosition(Position source, Position target) {
        if (source.isSameRow(target)) {
            return computeBetweenPositionBySameRow(source, target);
        }
        if (source.isSameColumn(target)) {
            return computeBetweenPositionBySameColumn(source, target);
        }
        return List.of();
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        return source.isSameRow(target) || source.isSameColumn(target);
    }
}


