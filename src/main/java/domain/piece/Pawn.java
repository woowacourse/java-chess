package domain.piece;

import domain.position.Position;
import domain.position.Row;

import java.util.List;

public class Pawn extends Division {
    public Pawn(Color color, Position position) {
        super(color, "p", position);
    }

    @Override
    public void move(Position to, List<Piece> pieces) {
        if (position.diffRow2(to, 2 * color.moveUnit()) && position.diffColumn2(to, 0)) {
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
        if (position.diffRow2(to, color.moveUnit()) && position.diffColumn2(to, 0)) {
            position = to;
            return;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public void kill(Position to, List<Piece> pieces) {
        if ((position.diffColumn2(to, 1) || position.diffColumn2(to, -1)) && position.diffRow2(to, color.moveUnit())) {
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
