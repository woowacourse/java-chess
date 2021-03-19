package chess.domain.piece;

import chess.domain.position.Position;

public class King extends Division {
    public King(Color color, Position position) {
        super(color, "k", position);
    }

    @Override
    public void moveToEmpty(Position to, Pieces pieces) {
        int diffRow = Math.abs(position.diffRow(to));
        int diffColumn = Math.abs(position.diffColumn(to));

        if ((diffRow | diffColumn) == 1) {
            position = to;
            return;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public void moveForKill(Position to, Pieces pieces) {
        this.moveToEmpty(to, pieces);
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public double score() {
        return 0;
    }
}
