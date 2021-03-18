package domain.piece;

import domain.position.Position;

import java.util.List;

public class Knight extends Division {
    public Knight(Color color, Position position) {
        super(color, "n", position);
    }

    @Override
    public void move(Position to, List<Piece> pieces) {
        int diffRow = Math.abs(position.diffRow(to));
        int diffColumn = Math.abs(position.diffColumn(to));

        if ((diffRow == 2 && diffColumn == 1) || (diffRow == 1 && diffColumn == 2)) {
            position = to;
            return;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public void kill(Position to, List<Piece> pieces) {
        move(to, pieces);
    }
}
