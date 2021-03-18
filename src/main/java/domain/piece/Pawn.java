package domain.piece;

import domain.position.Position;
import domain.position.Row;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Division {
    public Pawn(Color color, Position position) {
        super(color, "p", position);
    }

    @Override
    public void move(Position to, List<Piece> pieces) {
        if (position.diffRow(to, 2 * color.moveUnit()) && position.diffColumn(to, 0)) {
            if (isBlack()) {
                if (position.hasRow(Row.SEVEN)) {
                    if (!isContaining(position.moveBy(0, color.moveUnit()), pieces)) {
                        position = to;
                        return;
                    }
                }
            }

            if (position.hasRow(Row.TWO)) {
                if (!isContaining(position.moveBy(0, color.moveUnit()), pieces)) {
                    position = to;
                    return;
                }
            }

        }
        if (position.diffRow(to, color.moveUnit()) && position.diffColumn(to, 0)) {
            position = to;
            return;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public void kill(Position to, List<Piece> pieces) {
        if ((position.diffColumn(to, 1) || position.diffColumn(to, -1)) && position.diffRow(to, color.moveUnit())) {
            position = to;
            return;
        }
        throw new IllegalArgumentException();
    }


    private boolean isContaining(Position position, List<Piece> pieces) {
        for (Piece piece : pieces) {
            if (piece.hasPosition(position))
                return true;
        }
        return false;
    }
}
