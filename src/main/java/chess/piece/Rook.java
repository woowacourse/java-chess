package chess.piece;

import chess.Position;
import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

    @Override
    public boolean availablePath(Position start, Position target) {
        return start.onStraight(target);
    }

    @Override
    public List<Position> findAllRouteToTarget(Position start, Position target) {
        List<Position> positions = new ArrayList<>();

        Position current = new Position(start.row(), start.column());

        while (!current.equals(target)) {
            if (start.onSameRow(target)) {
                if (current.column().intValue() < target.column().intValue()) {
                    current = current.moveRight();
                } else {
                    current = current.moveLeft();
                }
            }
            if (start.onSameColumn(target)) {
                if (current.row().intValue() < target.row().intValue()) {
                    current = current.moveUp();
                } else {
                    current = current.moveDown();
                }
            }
            positions.add(current);
        }
        return positions;
    }

}
