package domain.piece;

import domain.position.Position;

import java.util.List;

public class King extends Division {
    public King(Color color, Position position) {
        super(color, "k", position);
    }

    @Override
    public void move(Position to, Pieces pieces) {
        int diffRow = Math.abs(position.diffRow(to));
        int diffColumn = Math.abs(position.diffColumn(to));

        if ((diffRow | diffColumn) == 1) {
            position = to;
            return;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public void kill(Position to, Pieces pieces) {
        move(to, pieces);
    }
}
