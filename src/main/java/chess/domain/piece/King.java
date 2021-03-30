package chess.domain.piece;

import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public class King extends Division {

    public static final int KING_SCORE = 0;

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
        return KING_SCORE;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    public List<Position> movablePosition(Position position) {
        List<Position> positions = new ArrayList<>();
        positions.add(position.moveBy(1,1));
        positions.add(position.moveBy(1,0));
        positions.add(position.moveBy(1,-1));
        positions.add(position.moveBy(0,1));
        positions.add(position.moveBy(0,-1));
        positions.add(position.moveBy(-1,1));
        positions.add(position.moveBy(-1,0));
        positions.add(position.moveBy(-1,-1));

        return positions;
    }
}
