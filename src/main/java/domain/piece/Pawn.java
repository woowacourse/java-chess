package domain.piece;

import domain.position.Position;
import domain.position.Row;

import java.util.List;

public class Pawn extends Division {
    public Pawn(Color color, Position position) {
        super(color, "p", position);
    }

    @Override
    public void move(Position to, Pieces pieces) {
        if (position.diffRow(to) == 2 * color.moveUnit() && position.diffColumn(to) == 0) {
            if (isBlack()) {
                if (position.hasRow(Row.SEVEN)) {
                    if (!pieces.hasPieceOf(position.moveBy(0, color.moveUnit()))) {
                        position = to;
                        return;
                    }
                }
            }

            if (position.hasRow(Row.TWO)) {
                if (!pieces.hasPieceOf(position.moveBy(0, color.moveUnit()))) {
                    position = to;
                    return;
                }
            }

        }
        if (position.diffRow(to) == color.moveUnit() && position.diffColumn(to) == 0) {
            position = to;
            return;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public void kill(Position to, Pieces pieces) {
        if ((position.diffColumn(to) == 1 || position.diffColumn(to) == -1) && position.diffRow(to) == color.moveUnit()) {
            position = to;
            return;
        }
        throw new IllegalArgumentException();
    }
}
