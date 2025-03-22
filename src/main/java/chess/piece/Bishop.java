package chess.piece;

import chess.Movement;
import chess.Position;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece{

    @Override
    public boolean canMove(Position start, Position target) {
        return start.onDiagonal(target);
    }

    @Override
    public List<Position> findAllRouteToTarget(Position start, Position target) {
        List<Position> positions = new ArrayList<>();

        Position current = start;
        int rowStep = (target.rowValue() - start.rowValue()) / Math.abs(target.rowValue() - start.rowValue());
        int colStep = (target.colValue() - start.colValue()) / Math.abs(target.colValue() - start.colValue());

        while (!current.equals(target)) {
            current = current.moveVertical(rowStep).moveHorizontal(colStep);
            positions.add(current);
        }

        return positions;
    }
}
